package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
import com.yayiabc.http.mvc.controller.unionpay.sdk.LogUtil;
import com.yayiabc.http.mvc.dao.*;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.Invite;
import com.yayiabc.http.mvc.service.TokenService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private WxAppDao wxAppDao;
    @Autowired
    SaleLogDao saleLogDao;
    @Autowired
    private UserMyQbService userMyQbService;
    @Autowired
    private UserManageListDao userManageListDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SaleInfoDao saleInfoDao;


    @Override
    public DataWrapper<Void> getVerifyCode(String phone, Integer type) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        ErrorCodeEnum errorCodeEnum=checkState(phone,type);
        if(errorCodeEnum!=null){
            dataWrapper.setErrorCode(errorCodeEnum);
            return dataWrapper;
        }
        String code = VerifyCodeManager.newPhoneCode(phone);
        if (code == null) {
            dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            return dataWrapper;
        }
        boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
        if (!result) {
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    //判断用户是否已经注册
    public boolean checkIfRegistered(String phone,Integer type){
        boolean flag=true;
        if(type<=2){
            if (userDao.getUserByPhone(phone) == null) {
                flag=false;
            }
        }else{
            if(saleInfoDao.getSaleIdBySalePhone(phone)==null){
                flag=false;
            }
        }
        return flag;
    }

    //判断两种情况下,是否发送消息
    public ErrorCodeEnum checkState(String phone,Integer type) {
        if(type==null){
            return null;
        }
        boolean flag = checkIfRegistered(phone, type);
        if (type % 2 == 1) {//注册时发送验证码
            if (flag) {
                return ErrorCodeEnum.Username_Already_Exist;
            }
        } else if (type % 2 == 0) {//登录时发送的验证码
            if (!flag) {
                return ErrorCodeEnum.Username_NOT_Exist;
            }
        }
        return null;
    }


    @Override
    public DataWrapper<User> register(String phone, String password, String code,String openid,Integer id) {
        DataWrapper<User> dataWrapper = new DataWrapper<User>();
        if (userDao.getUserByPhone(phone) == null) {
            //验证码判断
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
            dataWrapper.setErrorCode(codeEnum);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                return dataWrapper;
            }
            User newUser = new User();
            newUser.setUserId(UUID.randomUUID().toString());
            newUser.setPhone(phone);
            newUser.setPwd(MD5Util.getMD5String(password));
            if (1 == userDao.register(newUser)) {
                //绉婚櫎楠岃瘉鐮�
                VerifyCodeManager.removePhoneCodeByPhoneNum(phone);
                String token = tokenService.getToken(newUser.getUserId());
                QbRecord qbRecord=new QbRecord();
                qbRecord.setQbRget(60+"");
                qbRecord.setRemark("注册送60乾币");
                qbRecord.setQbType("qb_balance");
                userMyQbService.add(qbRecord, token);
                dataWrapper.setToken(token);
                newUser.setCreated(new Date());
                dataWrapper.setData(newUser);
                String byid=newUser.getUserId();
                //赠送邀请人乾币
                userDao.presented(id);
                Invite invite =new Invite();
                //记录邀请人和被邀请人数据
                userDao.addUser(id,byid);
                if (openid != null) {
                    wxAppDao.addUser(newUser.getUserId(), openid, newUser.getPhone());
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
            }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_Already_Exist);
        }
        return dataWrapper;
    }



    @Override
    public DataWrapper<User> noteLogin(String phone, String code) {
        DataWrapper<User> dataWrapper = new DataWrapper<User>();
        User user = userDao.getUserByPhone(phone);
        if (user != null) {
                ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
                if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                    dataWrapper.setErrorCode(codeEnum);
                    return dataWrapper;
                }
                dataWrapper.setData(user);
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                int num = userDao.getCartNum(user);
                dataWrapper.setNum(num);
                String token =tokenService.getToken(user.getUserId());
                dataWrapper.setToken(token);
                //返回资质审核状态
                int cercount=userDao.getCertificationCount(user.getUserId());
                dataWrapper.setMsg(cercount+"");
                return dataWrapper;
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<User> pwdLogin(String phone, String password) {
        DataWrapper<User> dataWrapper = new DataWrapper<User>();
        User seUser = userDao.getUserByPhone(phone);
        String token = null;
        if (seUser != null) {
            if (MD5Util.getMD5String(password).equals(seUser.getPwd())) {
                dataWrapper.setData(seUser);
                int num = userDao.getCartNum(seUser);
                dataWrapper.setNum(num);
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                String userId = seUser.getUserId();
                token = tokenService.getToken(userId);
                //返回资质审核状态
                int cercount=userDao.getCertificationCount(userId);
                dataWrapper.setMsg(cercount+"");
                dataWrapper.setToken(token);
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Password_error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> reLogin(String token) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        userDao.deleteToken(token);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> forgetPwd(String phone, String code, String password) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        User user=userDao.getUserByPhone(phone);
        if (user != null) {
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                dataWrapper.setErrorCode(codeEnum);
                return dataWrapper;
            }
                try {
                    userDao.updatePwd(phone,MD5Util.getMD5String(password));
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    //返回资质审核状态
                    int cercount=userDao.getCertificationCount(user.getUserId());
                    dataWrapper.setMsg(cercount+"");
                } catch (Exception e) {
                    e.printStackTrace();
                    dataWrapper.setErrorCode(ErrorCodeEnum.Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> bindSale(String token, String salePhone) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        String userId = userDao.getUserIdByToken(token);
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(salePhone);
        if(saleInfo!=null) {
            int i = userDao.bindSale(userId,saleInfo.getSaleId());
            userManageListDao.bindUpdateNum(saleInfo.getSaleId());
            boolean result=HttpUtil.sendUserBind(userDao.getUserPhoneByToken(token),salePhone);
            boolean resulta=HttpUtil.sendSaleBind(salePhone,userDao.getUserPhoneByToken(token));
            if(result && resulta){
                LogUtil.writeLog("绑定短信发送成功！");
            }
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> deleteInGrainUser(Integer userId) {
        DataWrapper<Void> dataWrapper= new DataWrapper<Void>();


        int sign =userDao.deleteInGrainUser(userId);
        return dataWrapper;
    }

    @Override
    public List<String> getAllPhoneList() {
        List<String> phoneList=userDao.getPhoneList();
        return phoneList;
    }


}
