package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
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
                User user = userDao.getUserByUserId(userId.get("userId"));
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
        if ("1".equals(type)){
            User seUser = userDao.getUserByPhone(phone);
            /*String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (verifyCode.equals(serverCode)) {*/
            	if (seUser != null) {
	                dataWrapper.setData(seUser);
	                int num = userDao.getCartNum(seUser);
	                dataWrapper.setNum(num);
	                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
	                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
	                String userId = seUser.getUserId();
	                String token =tokenService.getToken(userId);
	                dataWrapper.setData(seUser);
	                dataWrapper.setToken(token);
            	}else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
           /* } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }*/
        }else if ("2".equals(type)){
            SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
            String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (verifyCode.equals(serverCode)) {
            	if (saleInfo != null) {
                    dataWrapper.setData(saleInfo);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    String saleId = saleInfo.getSaleId();
                    if (saleId != null) {
                        String token =tokenService.getSaleToken(saleId);
                        dataWrapper.setToken(token);
                        dataWrapper.setData(saleInfo);

                    }
            	}else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
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
            wxAppDao.addUser(userId,openid);
            dataWrapper.setData(seUser);
			dataWrapper.setToken(token);
		}else{
			 User newUser = new User();
             newUser.setUserId(UUID.randomUUID().toString());
             newUser.setPhone(user.getPhone());
             newUser.setPwd(MD5Util.getMD5String("123456"));
             if (1 == userDao.register(newUser)) {
                    String token = tokenService.getToken(newUser.getUserId());
                    QbRecord qbRecord=new QbRecord();
                    qbRecord.setQbRget(60);
                    qbRecord.setRemark("注册送60乾币");
                    qbRecord.setQbType("qb_balance");
                    userMyQbService.add(qbRecord, token);
                    user.setUserId(newUser.getUserId());
                    userDao.registerUserInfo(user);
                    userDao.registerUserCertification(user);
                    wxAppDao.addUser(newUser.getUserId(),openid);
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
	public DataWrapper<Void> updateSaleInfo(SaleInfo saleInfo, Integer number,String openid) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		if(number==1){//已注册
			saleInfoDao.updateSaleInfo(saleInfo);
			String saleId=saleInfoDao.getSaleIdBySalePhone(saleInfo.getPhone());
			String token=tokenService.getSaleToken(saleId);
            wxAppDao.addSaleUser(saleId,openid);
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
                    wxAppDao.addSaleUser(saleInfoTwo.getSaleId(),openid);
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
    public DataWrapper<Void> judgeOpenid(String openid, String state) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        String type=userDao.getTypeByOpenid(openid);
        if(type == null){
            dataWrapper.setErrorCode(ErrorCodeEnum.OPENID_NOT_EXIST);
        }else if(type.equals(state)){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            if("ds".equals(type)){
                dataWrapper.setMsg("该微信号已绑定牙医账号！");
            }else if("ck".equals(type)){
                dataWrapper.setMsg("该微信号已绑定创客账号！");
            }
        }
        return dataWrapper;
    }


}
