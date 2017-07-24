package com.yayiabc.http.mvc.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.pojo.jpa.SaleIncome;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.SaleMyWalletDetail;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface MyWalletDao {
     //查询 进账
	List<SaleIncome> saleInCome(@Param("saleId")String token,
			@Param("starTime")String starTime,@Param("endTime")String endTime);
	//查询提现
	List<With> with(@Param("saleId")String token,
			@Param("starTime")String starTime,@Param("endTime")String endTime);
	
	  
	//查询 订单详细
	SaleInfo queryOrder(@Param("orderId")String orderId,@Param("userId")String userId);
	
	//得到userID
	String queryUserID(@Param("orderId")String orderId);
	

	SaleInfo queryTMD(@Param("saleId")String saleId);
	

	String getSaleIdByToken(String token);
	
	Double getBalanceBySaleId(String saleId);
	
	Double getAllIn(String saleId);
	
	Double getAllOut(String saleId);
	
	List<Balance> myWalletDetails(@Param("saleId")String saleId,@Param("state") Integer state,
			@Param("starTime")String starTime,@Param("endTime") String endTime);
	
	Integer getCount(Integer balanceId);
	
	Balance getViewDetailByOut(Integer balanceId);
	
	Date getTime(Integer balanceId);
	
	List<Balance> detail(HashMap<String, String> hm);
	
	//查询出一条一个月的详情记录
	Balance details(@Param("balanceId")String balanceId);

}
