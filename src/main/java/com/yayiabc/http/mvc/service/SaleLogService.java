package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

public interface SaleLogService {

	
	DataWrapper<SaleInfo> register(String phone, String password, String code,String openid);

	DataWrapper<SaleInfo> noteLogin(String phone, String code);

	DataWrapper<SaleInfo> pwdLogin(String phone, String password);

	DataWrapper<Void> reLogin(String token);

	DataWrapper<Void> forgetPwd(String phone, String code, String password);
}
