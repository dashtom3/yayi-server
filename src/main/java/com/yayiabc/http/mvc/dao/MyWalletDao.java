package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.SaleIncome;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface MyWalletDao {
     //查询 进账
	List<SaleIncome> saleInCome(@Param("saleId")String token);
	//查询提现
	List<With> with(@Param("saleId")String token);
	  
	//查询 订单详细
	SaleInfo queryOrder(@Param("orderId")String orderId,@Param("userId")String userId);
	
	//得到userID
	String queryUserID(@Param("orderId")String orderId);
}
