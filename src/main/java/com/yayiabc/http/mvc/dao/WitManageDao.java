package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageDao {
	
	With showWit(@Param("phone")String phone);
	
	String getPhone(@Param("userId")String userId);
	//操作提现数据
	int  submitWit(With with);
	//操作
	int oper(@Param("cashId")int cashId);

     //查询+显示
	List<With> query(@Param("message")String message, @Param("state")String state);

	
	//查提现的金额 与saleId
	With queryMessage(@Param("cashId")int cashId);

	int  insertMoney(With w);
     
	int queryMoney(String saleId);
   //deleteMoney
	int deleteMoney(Integer cashId, Integer cashMoney);
    //set
	int  setTime(String saleId);
}
