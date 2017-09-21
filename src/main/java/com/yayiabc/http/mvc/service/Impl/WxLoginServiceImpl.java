package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
import com.yayiabc.http.mvc.dao.SaleInfoDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.WxAppDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.TokenService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 *
 * Created by Jo on 2017/8/2.
 */
@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    WxAppDao wxAppDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SaleLogDao saleLogDao;
    @Autowired
    UserMyQbService userMyQbService;
    @Autowired
    SaleInfoDao saleInfoDao;
    @Autowired
    TokenService tokenService;



    @Override
    public DataWrapper<Object> login(String code) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        Map<String, String> appCode = wxAppDao.getAppCode();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + appCode.get("appid") + "&secret=" + appCode.get("secret")
                + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> response = HttpUtil.sendGet(url);
        assert response != null;
        String openid = (String) response.get("openid");
        assert openid != null;
        Map<String,String> userId = wxAppDao.getUser(openid);
        if (userId != null){
            String type = userId.get("type");
            if ("1".equals(type)){
                User user = userDao.getUserByUserId(Integer.parseInt(userId.get("userId")));
                String token = tokenService.getToken(userId.get("userId"));
                dataWrapper.setToken(token);
                dataWrapper.setData(user);
            }else if ("2".equals(type)){
                SaleInfo saleInfo = saleLogDao.getSaleInfoById(userId.get("userId"));
                String token = tokenService.getSaleToken(userId.get("userId"));
                dataWrapper.setData(saleInfo);
                dataWrapper.setToken(token);
            }
        }else {
            dataWrapper.setData(openid);
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Object> bindUser(String phone, String verifyCode,String type) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        if(wxAppDao.getPhoneCount(phone)!=0){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            dataWrapper.setMsg("该账号已经绑定过其他微信了");
            return dataWrapper;
        }
        ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,verifyCode);
        dataWrapper.setErrorCode(codeEnum);
        if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
            return dataWrapper;
        }
        if ("1".equals(type)){
            User seUser = userDao.getUserByPhone(phone);
            if (seUser != null) {
                VerifyCodeManager.removePhoneCodeByPhoneNum(phone);
                dataWrapper.setData(seUser);
                int num = userDao.getCartNum(seUser);
                dataWrapper.setNum(num);
                String token =tokenService.getToken(seUser.getUserId());
                dataWrapper.setData(seUser);
                dataWrapper.setToken(token);
            }else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            }
        }else if ("2".equals(type)){
            SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
            if (saleInfo != null) {
                dataWrapper.setData(saleInfo);
                String saleId = saleInfo.getSaleId();
                if (saleId != null) {
                    String token =tokenService.getSaleToken(saleId);
                    dataWrapper.setToken(token);
                    dataWrapper.setData(saleInfo);
                }
            }else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            }
        }
        return dataWrapper;
    }








	@Override
	public DataWrapper<User> updateUserInfo(User user,Integer number,String openid) {
		DataWrapper<User> dataWrapper =new DataWrapper<User>();
		if(number==1){//已注册
			userDao.updateUserInfo(user);
			String userId=userDao.getUserIdByPhone(user.getPhone());
			user.setUserId(userId);
			Integer count=userDao.getCountByUserId(userId);
			if(count!=0){
                userDao.updateCertification(user);
            }else{
                userDao.registerUserCertification(user);
            }
			String token=tokenService.getToken(userId);
            User seUser = userDao.getUserByPhone(user.getPhone());
            wxAppDao.addUser(userId,openid,user.getPhone());
            dataWrapper.setData(seUser);
			dataWrapper.setToken(token);
		}else{
			 User newUser = new User();
             newUser.setPhone(user.getPhone());
             newUser.setPwd(MD5Util.getMD5String("123456"));
             if (1 == userDao.register(newUser)) {
                    String token = tokenService.getToken(newUser.getUserId());
                    QbRecord qbRecord=new QbRecord();
                    qbRecord.setQbRget(60+"");
                    qbRecord.setRemark("注册送60乾币");
                    qbRecord.setQbType("qb_balance");
                    userMyQbService.add(qbRecord, token);
                    user.setUserId(newUser.getUserId());
                    userDao.registerUserInfo(user);
                    userDao.registerUserCertification(user);
                    wxAppDao.addUser(newUser.getUserId(),openid,user.getPhone());
                    dataWrapper.setData(user);
                    dataWrapper.setToken(token);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
            }
		}
		return dataWrapper;
	}






	@Override
	public DataWrapper<Void> updateSaleInfo(SaleInfo saleInfo, Integer number,String openid) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		if(number==1){//已注册
			saleInfoDao.updateSaleInfo(saleInfo);
			String saleId=saleInfoDao.getSaleIdBySalePhone(saleInfo.getPhone());
			String token=tokenService.getSaleToken(saleId);
            wxAppDao.addSaleUser(saleId,openid,saleInfo.getPhone());
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setToken(token);
		}else{//未注册
			SaleInfo saleInfoTwo = new SaleInfo();
            saleInfoTwo.setSaleId(UUID.randomUUID().toString());
            saleInfoTwo.setPhone(saleInfo.getPhone());
            saleInfoTwo.setSalePwd(MD5Util.getMD5String("123456"));
            saleInfoTwo.setUpdated(new Date());
            saleInfoTwo.setCreated(new Date());
            	if (1 == saleLogDao.register(saleInfoTwo)) {
                    VerifyCodeManager.removePhoneCodeByPhoneNum(saleInfo.getPhone());
                    String token = tokenService.getSaleToken(saleInfoTwo.getSaleId());
                    saleInfoDao.updateSaleInfo(saleInfo);
                    wxAppDao.addSaleUser(saleInfoTwo.getSaleId(),openid,saleInfo.getPhone());
                    dataWrapper.setToken(token);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            	} else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            	}
		}
		return dataWrapper;
	}

    @Override
    public DataWrapper<Object> judgeOpenid(String openid, String state) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if("ds".equals(state)){
            Map<String, String> map=userDao.getTypeByOpenid(openid,"1");
            if(map == null || map.isEmpty()){
                dataWrapper.setErrorCode(ErrorCodeEnum.OPENID_NOT_EXIST);
            }else{
                if("ds".equals(map.get("getType"))){
                    User user=userDao.getUserByUserId(Integer.parseInt(map.get("uid")));
                    if (user != null) {
                        dataWrapper.setData(user);
                        int num = userDao.getCartNum(user);
                        dataWrapper.setNum(num);
                        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                        String token =tokenService.getToken(user.getUserId());
                        dataWrapper.setData(user);
                        dataWrapper.setToken(token);
                        return dataWrapper;
                    }
                }
            }
        }else if("ck".equals(state)){
            Map<String, String> map=userDao.getTypeByOpenid(openid,"2");
            if(map == null || map.isEmpty()){
                dataWrapper.setErrorCode(ErrorCodeEnum.OPENID_NOT_EXIST);
            }else{
                if("ck".equals(map.get("getType"))){
                    SaleInfo saleInfo = saleLogDao.getSaleInfoById(map.get("uid"));
                    if (saleInfo != null) {
                        dataWrapper.setData(saleInfo);
                        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                        String token =tokenService.getSaleToken(saleInfo.getSaleId());
                        dataWrapper.setToken(token);
                        dataWrapper.setData(saleInfo);
                        return dataWrapper;
                    }
                }
            }
        }
        dataWrapper.setErrorCode(ErrorCodeEnum.OPENID_NOT_EXIST);
        return dataWrapper;
    }


}
