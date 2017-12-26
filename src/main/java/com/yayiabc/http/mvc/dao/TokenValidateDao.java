package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.SaleToken;
import com.yayiabc.http.mvc.pojo.model.UserToken;

public interface TokenValidateDao {

	Integer getUserCountByLoginToken(String loginToken);

	Integer getUserStateByLoginToken(String loginToken);

	void updateUserLoginState(String loginToken);

	Integer getSaleCountByLoginToken(String loginToken);

	Integer getSaleStateByLoginToken(String loginToken);

	void updateSaleState(String loginToken);

	Integer getAdminCountByLoginToken(String loginToken);

    UserToken getUserTokenByLoginToken(String loginToken);

    SaleToken getSaleTokenByLoginToken(String loginToken);
    
    String getSalesaleIdByLoginToken(String loginToken);
}
