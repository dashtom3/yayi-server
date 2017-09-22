package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

@Repository
public interface SaleInfoDao {
	int updateSale(SaleInfo saleInfo);

	int updatePostal(@Param("postalType") String postalType,
			@Param("bankName") String bankName,
			@Param("openName") String openName,
			@Param("accountNumber") String accountNumber,
			@Param("saleId") String saleId);
	
	SaleInfo query(@Param("saleId") String saleId);

	void updateSaleInfo(SaleInfo saleInfo);

	String getSaleIdBySalePhone(@Param("phone")String phone);

	void testProcedure();

    int getCount(String openid);
}
