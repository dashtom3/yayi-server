package com.yayiabc.http.mvc.service;

import com.yayiabc.http.mvc.pojo.jpa.SaleToken;
import com.yayiabc.http.mvc.pojo.model.UserToken;

public interface TokenValidateService {

	Integer getUserCountByLoginToken(String loginToken);

	Integer getUserStateByLoginToken(String loginToken);

	void updateUserLoginState(String loginToken);

	Integer getSaleCountByLoginToken(String loginToken);

	Integer getSaleStateByLoginToken(String loginToken);

	void updateSaleState(String loginToken);

	Integer getAdminCountByLoginToken(String loginToken);

    UserToken getUserTokenByLoginToken(String loginToken);

    SaleToken getSaleTokenByLoginToken(String loginToken);
}
