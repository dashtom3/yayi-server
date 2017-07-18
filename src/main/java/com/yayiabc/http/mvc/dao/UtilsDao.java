package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface UtilsDao {

	String getUserID(@Param("token")String token);
	
	 String getSaleId(@Param("SaleToken")String saleToken);
	 
	 String getToken(@Param("userId")String userId);
   
	String getSaleToken(@Param("saleId")String saleId);
     
	String queryPhone(@Param("userId")String userId);
}
