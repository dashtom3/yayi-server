package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleLogService;

public class SaleLogServiceImpl implements SaleLogService {

	@Override
	public DataWrapper<Void> getVerifyCode(String phone) {
		
		return null;
	}

	@Override
	public DataWrapper<SaleInfo> register(String phone, String password,
			String code) {
		
		return null;
	}

	@Override
	public DataWrapper<SaleInfo> noteLogin(String phone, String code) {
		
		return null;
	}

	@Override
	public DataWrapper<SaleInfo> pwdLogin(String phone, String password) {
		
		return null;
	}

	@Override
	public DataWrapper<Void> reLogin(String token) {
		
		return null;
	}

	@Override
	public DataWrapper<Void> forgetPwd(String phone, String code,
			String password) {
		
		return null;
	}

}
