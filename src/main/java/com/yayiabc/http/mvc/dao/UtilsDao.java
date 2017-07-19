package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface UtilsDao {

	String getUserID(@Param("token")String token);
	
	 String getSaleId(@Param("SaleToken")String saleToken);
	 
	 String getToken(@Param("userId")String userId);
   
	String getSaleToken(@Param("saleId")String saleId);
     
	String queryPhone(@Param("userId")String userId);

	String querySaleIdByOrderId(@Param("orderId")String orderId);
     
	String getSaleIdByOrderId(String orderId);

	String querySalePhoneBySaleId(@Param("saleId")String saleId);
   
	Ordera queryCalssSumPrice(@Param("orderId")String orderId);
    
	int insert(
			@Param("saleId")String saleId,
			@Param("orderId")String orderId,
			@Param("d")String d,
			@Param("x")String x,
			@Param("i")String i);
}
