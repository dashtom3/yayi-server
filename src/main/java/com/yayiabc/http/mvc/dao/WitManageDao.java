package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageDao {
	
	With showWit(@Param("phone")String phone);
	
	String getPhone(@Param("userId")String userId);
	//操作提现数据
	int  submitWit(@Param("saleId")String saleId, @Param("balanceOut")Double balanceOut
			,@Param("balance")Double balance,@Param("describey")String describey
			);
	//操作   ,
	int oper(@Param("balanceId")int balacceId,@Param("dPrice")Double dPrice,
			@Param("describey")String describey
			);

     //查询+显示
	List<With> query(@Param("message")String message, @Param("state")String state);

	//查提现的金额 与saleId
	With queryMessage(@Param("cashId")int cashId);

	int  insertMoney(With w);
     
	List<Double> queryMoney(String saleId);
   //deleteMoney
	int deleteMoney(Integer cashId, Integer cashMoney);
    //set
	int  setTime(String saleId);
     //查询
	Balance queryBalance(@Param("balanceId")int balanceId);
}
