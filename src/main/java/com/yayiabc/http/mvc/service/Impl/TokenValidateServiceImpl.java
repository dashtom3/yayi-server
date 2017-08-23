package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.http.mvc.pojo.jpa.SaleToken;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.TokenValidateDao;
import com.yayiabc.http.mvc.service.TokenValidateService;
@Service
public class TokenValidateServiceImpl implements TokenValidateService{
	@Autowired
	private TokenValidateDao tokenValidateDao;
	

	@Override
	public Integer getUserCountByLoginToken(String loginToken) {
		Integer userLoginCount=tokenValidateDao.getUserCountByLoginToken(loginToken);
		return userLoginCount;
	}


	@Override
	public Integer getUserStateByLoginToken(String loginToken) {
		Integer userState=tokenValidateDao.getUserStateByLoginToken(loginToken);
		return userState;
	}


	@Override
	public void updateUserLoginState(String loginToken) {
		tokenValidateDao.updateUserLoginState(loginToken);
		
	}


	@Override
	public Integer getSaleCountByLoginToken(String loginToken) {
		Integer saleLoginCount=tokenValidateDao.getSaleCountByLoginToken(loginToken);
		return saleLoginCount;
	}


	@Override
	public Integer getSaleStateByLoginToken(String loginToken) {
		Integer saleState=tokenValidateDao.getSaleStateByLoginToken(loginToken);
		return saleState;
	}


	@Override
	public void updateSaleState(String loginToken) {
		tokenValidateDao.updateSaleState(loginToken);
		
	}


	@Override
	public Integer getAdminCountByLoginToken(String loginToken) {
		Integer adminCount=tokenValidateDao.getAdminCountByLoginToken(loginToken);
		return adminCount;
	}

	@Override
	public UserToken getUserTokenByLoginToken(String loginToken) {
		return tokenValidateDao.getUserTokenByLoginToken(loginToken);
	}

	@Override
	public SaleToken getSaleTokenByLoginToken(String loginToken) {
		return tokenValidateDao.getSaleTokenByLoginToken(loginToken);
	}


}
