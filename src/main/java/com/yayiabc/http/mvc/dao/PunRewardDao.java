package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PunRewardDao {
	
  List<Object> show(@Param("saleId")String saleId);
  
  
  int addMoney(@Param("saleId")String saleId,
		  @Param("balanceIn") Double balance_in,
		  @Param("balance")Double balance);


   int  delMoney(@Param("saleId")String saleId,
		  @Param("balanceOut") Double balance_out,
		  @Param("balance")Double balance);


    List<String>  pd(@Param("saleId")String saleId);


	Integer shows(String saleId);
}
