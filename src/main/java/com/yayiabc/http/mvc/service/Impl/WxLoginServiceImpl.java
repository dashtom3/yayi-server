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
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.WxLoginService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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
                String token = getUserToken(userId.get("userId"));
                dataWrapper.setToken(token);
                dataWrapper.setData(user);
            }else if ("2".equals(type)){
                SaleInfo saleInfo = saleLogDao.getSaleInfoById(userId.get("userId"));
                String token = getSaleToken(userId.get("userId"));
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
    public DataWrapper<Object> bindUser(String phone, String verifyCode, String openid,String type) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        if ("1".equals(type)){
            User user = new User();
            user.setPhone(phone);
            User seUser = userDao.getUserByUser(user);
            String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (verifyCode.equals(serverCode)) {
            	if (seUser != null) {
	                dataWrapper.setData(seUser);
	                int num = userDao.getCartNum(seUser);
	                dataWrapper.setNum(num);
	                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
	                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
	                String userId = seUser.getUserId();
	                String token = getUserToken(userId);
	                dataWrapper.setData(seUser);
	                dataWrapper.setToken(token);
	                wxAppDao.addUser(userId,openid);
	                //把微信
            	}else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
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
                        String token = getSaleToken(saleId);
                        dataWrapper.setToken(token);
                        dataWrapper.setData(saleInfo);
                        wxAppDao.addSaleUser(saleId,openid);
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

    private String getUserToken(String userId) {
        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        String oldToken = userDao.getTokenByUserId(userId);
        if (oldToken == null) {
            userDao.addToken(userToken);
        } else {
            userDao.updateToken(userToken);
        }
        new Timer().schedule(new TokenTask(token), 2 * 3600 * 1000);
        return token;
    }
    private String getSaleToken(String id) {
        String token = UUID.randomUUID().toString();
        String oldToken = saleLogDao.getTokenBySaleId(id);
        if (oldToken == null) {
            saleLogDao.addSaleToken(id, token);
        } else {
            saleLogDao.updateSaleToken(id, token);
        }
        new Timer().schedule(new TokenTask(token), 2 * 3600 * 1000);
        return token;
    }


    private class TokenTask extends TimerTask {
        private String token;

        TokenTask(String token) {
            this.token = token;
        }

        @Override
        public void run() {
            saleLogDao.deleteSaleToken(token);
        }
    }


	@Override
	public DataWrapper<User> updateUserInfo(User user,Integer number) {
		DataWrapper<User> dataWrapper =new DataWrapper<User>();
		if(number==1){//已注册
			userDao.updateUserInfo(user);
			String userId=userDao.getUserIdByPhone(user.getPhone());
			user.setUserId(userId);
			userDao.updateCertification(user);
			String token=userDao.getTokenByUserId(userId);
			User userTwo = new User();
			userTwo.setPhone(user.getPhone());
            User seUser = userDao.getUserByUser(userTwo);
            dataWrapper.setData(seUser);
			dataWrapper.setToken(token);
		}else{
			 User newUser = new User();
             newUser.setUserId(UUID.randomUUID().toString());
             newUser.setPhone(user.getPhone());
             newUser.setPwd(MD5Util.getMD5String(user.getPwd()));
             if (1 == userDao.register(newUser)) {
                    String token = getToken(newUser.getUserId());
                    QbRecord qbRecord=new QbRecord();
                    qbRecord.setQbRget(60);
                    qbRecord.setRemark("注册送60乾币");
                    userMyQbService.add(qbRecord, token);
                    user.setUserId(newUser.getUserId());
                    userDao.registerUserInfo(user);
                    userDao.registerUserCertification(user);
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
	
	 private String getToken(String userId) {
	        String token = UUID.randomUUID().toString();
	        UserToken userToken = new UserToken();
	        userToken.setUserId(userId);
	        userToken.setToken(token);
	        String oldToken = userDao.getTokenByUserId(userId);
	        if (oldToken == null) {
	            userDao.addToken(userToken);
	        } else {
	            userDao.updateToken(userToken);
	        }
	        new Timer().schedule(new TokenTaskTwo(token), 2 * 3600 * 1000);
	        return token;
	    }

	  private class TokenTaskTwo extends TimerTask {
	       private String token;

	       public TokenTaskTwo(String token) {
	            this.token = token;
	       }

	        @Override
	        public void run() {
	            userDao.deleteToken(token);
	        }
	    }


	@Override
	public DataWrapper<Void> updateSaleInfo(SaleInfo saleInfo, Integer number) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		if(number==1){//已注册
			saleInfoDao.updateSaleInfo(saleInfo);
			String saleId=saleInfoDao.getSaleIdBySalePhone(saleInfo.getPhone());
			String token=getSaleTokenUtil(saleId);
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setToken(token);
		}else{//未注册
			SaleInfo saleInfoTwo = new SaleInfo();
            saleInfoTwo.setSaleId(UUID.randomUUID().toString());
            saleInfoTwo.setPhone(saleInfo.getPhone());
            saleInfoTwo.setSalePwd(MD5Util.getMD5String(saleInfo.getSalePwd()));
            saleInfoTwo.setUpdated(new Date());
            saleInfoTwo.setCreated(new Date());
            	if (1 == saleLogDao.register(saleInfoTwo)) {
                    VerifyCodeManager.removePhoneCodeByPhoneNum(saleInfo.getPhone());
                    String token = getSaleTokenUtil(saleInfoTwo.getSaleId());
                    saleInfoDao.updateSaleInfo(saleInfo);
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
	
	private String getSaleTokenUtil(String id) {
        String token = UUID.randomUUID().toString();
        String oldToken = saleLogDao.getTokenBySaleId(id);
        if (oldToken == null) {
            saleLogDao.addSaleToken(id, token);
        } else {
            saleLogDao.updateSaleToken(id, token);
        }
        new Timer().schedule(new TokenTaskThree(token), 2 * 3600 * 1000);
        return token;
    }

    private class TokenTaskThree extends TimerTask {
        private String token;

        public TokenTaskThree(String token) {
            this.token = token;
        }
        @Override
        public void run() {
            saleLogDao.deleteSaleToken(token);
        }
    }
}
