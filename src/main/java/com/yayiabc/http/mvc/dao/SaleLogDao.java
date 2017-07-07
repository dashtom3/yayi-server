package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

@Repository
public interface SaleLogDao {
	int register(SaleInfo saleInfo);

	SaleInfo getSaleInfoByPhone(String phone);

	void addSaleToken(@Param("saleId") String saleId,@Param("saleToken") String saleToken);

	String getTokenBySaleId(String saleId);

	void updatePwd(@Param("password")String password,@Param("saleId") String saleId);
	
	
}
