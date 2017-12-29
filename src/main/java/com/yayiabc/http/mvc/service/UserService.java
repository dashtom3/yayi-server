package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;

import java.util.List;

public interface UserService {

	DataWrapper<Void> getVerifyCode(String phone,Integer type);

	DataWrapper<User> register(String phone, String password,String trueName,String companyName,
			String part,String workAddress, String code,String openid,String id,Integer userType);

	DataWrapper<User> noteLogin(String phone, String code);

	DataWrapper<User> pwdLogin(String phone, String password);

	DataWrapper<Void> reLogin(String token);

	DataWrapper<Void> forgetPwd(String phone, String code, String password);

	DataWrapper<Void> bindSale(String token, String salePhone);

	DataWrapper<Void> deleteInGrainUser(Integer userId);

	List<String> getAllPhoneList();
	
	DataWrapper<List<User>> inviterChart(String token,Integer userType,Integer currentPage,Integer numberPerPage);
}
