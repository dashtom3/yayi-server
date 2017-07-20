package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PunRewardDao {
	
  List<Double> show(@Param("saleId")String saleId);
  
  
  int addMoney(@Param("saleId")String saleId,
		  @Param("balanceIn") Double balance_in,
		  @Param("balance")Double balance);


   int  delMoney(@Param("saleId")String saleId,
		  @Param("balanceIn") Double balance_in,
		  @Param("balance")Double balance);
}
