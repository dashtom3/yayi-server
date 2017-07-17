package com.yayiabc.http.mvc.service.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.SessionManager;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserDao userDao;
	@Autowired
	private UtilsDao utilsDao;
	public DataWrapper<Void> getVerifyCode(String phone) {
		//浜斿垎閽熶箣鍐呬笉鑳藉啀鍙戠煭淇�
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String code = VerifyCodeManager.newPhoneCode(phone);
		if (code == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			return dataWrapper;
		}
		boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
		if (result) {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<User> register(String phone, String password, String code) {
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		User neUser=new User();
		neUser.setPhone(phone);
		if (userDao.getUserByUser(neUser) == null) {
			//楠岃瘉鐮佹湇鍔�
			String serverCode = VerifyCodeManager.getPhoneCode(phone);
			if (serverCode.equals("noCode")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals("overdue")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals(code)) {
				User newUser = new User();
				newUser.setUserId(UUID.randomUUID().toString());
				newUser.setPhone(phone);
				newUser.setPwd(MD5Util.getMD5String(password));
				if (1 == userDao.register(newUser)) {
					//绉婚櫎楠岃瘉鐮�
					VerifyCodeManager.removePhoneCodeByPhoneNum(phone);
					dataWrapper.setData(newUser);
					String token=UUID.randomUUID().toString();
					UserToken userToken=new UserToken();
					userToken.setUserId(newUser.getUserId());
					userToken.setToken(token);
					userDao.addToken(userToken);
					dataWrapper.setToken(token);
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
					dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
					dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				}
			} else {
				System.out.println("code:" + code);
				System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_Already_Exist);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<User> noteLogin(String phone, String code) {
		DataWrapper<User> dataWrapper =new DataWrapper<User>();
		User neUser=new User();
		neUser.setPhone(phone);
		User user=userDao.getUserByUser(neUser);
		if(user!=null){
			String serverCode = VerifyCodeManager.getPhoneCode(phone);
			if (serverCode.equals("noCode")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals("overdue")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals(code)) {
				dataWrapper.setData(user);
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				int num=userDao.getCartNum(user);
				dataWrapper.setNum(num);
				String token=userDao.getTokenByUserId(user.getUserId());
				if(token==null||"".equals(token)){
					token=UUID.randomUUID().toString();
					UserToken userToken=new UserToken();
					userToken.setUserId(user.getUserId());
					userToken.setToken(token);
					userDao.addToken(userToken);
				}
				//--


				if(SessionManager.getSessionByUserID(user.getUserId())==null){
					String sessionKey=SessionManager.newSession(user);
					dataWrapper.setToken(token);
					return dataWrapper;
				}else{
					dataWrapper.setMsg("该账户已经登录");
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					return dataWrapper;
				}

				//--

			} else {
				System.out.println("code:" + code);
				System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			}
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<User> pwdLogin(String phone, String password) {
		DataWrapper<User> dataWrapper =new DataWrapper<User>();
		User user=new User();
		user.setPhone(phone);
		User seUser=userDao.getUserByUser(user);
		String token=null;
		if(seUser!=null){
			if(MD5Util.getMD5String(password).equals(seUser.getPwd())){
				dataWrapper.setData(seUser);
				int num=userDao.getCartNum(seUser);
				dataWrapper.setNum(num);
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				String userId=seUser.getUserId();
				token=userDao.getTokenByUserId(userId);
				System.out.println(token);
				if(token==null||"".equals(token)){
					token=UUID.randomUUID().toString();
					UserToken userToken=new UserToken();
					userToken.setUserId(seUser.getUserId());
					userToken.setToken(token);
					userDao.addToken(userToken);
				}

			}else{
				dataWrapper.setErrorCode(ErrorCodeEnum.Password_error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			}
			//--


			if(SessionManager.getSessionByUserID(seUser.getUserId())==null){
				String sessionKey=SessionManager.newSession(seUser);
				dataWrapper.setToken(token);
				return dataWrapper;
			}else{
				dataWrapper.setMsg("该账户已经登录");
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				return dataWrapper;
			}

			//--
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		dataWrapper.setToken(token);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> reLogin(String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		//--
		String userId=utilsDao.getUserID(token);
		SessionManager.removeSessionByUserId(userId);
		//---
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> forgetPwd(String phone, String code,String password) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		User neUser=new User();
		neUser.setPhone(phone);
		if(userDao.getUserByUser(neUser)!=null){
			String serverCode = VerifyCodeManager.getPhoneCode(phone);
			if (serverCode.equals("noCode")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals("overdue")) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			} else if (serverCode.equals(code)) {
				neUser.setPwd(MD5Util.getMD5String(password));
				try {
					userDao.updatePwd(neUser);
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
					dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				} catch (Exception e) {
					e.printStackTrace();
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
				}
			} else {
				System.out.println("code:" + code);
				System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
				dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
				dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
			}
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
			dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		}
		return dataWrapper;
	}

}
