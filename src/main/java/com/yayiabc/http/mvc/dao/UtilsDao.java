package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface UtilsDao {

	String getUserID(@Param("token")String token);
	
	 String getSaleId(@Param("SaleToken")String saleToken);
	 
	 String getToken(@Param("userId")String userId);
   
	String getSaleToken(@Param("saleId")String saleId);
     
	String queryPhone(@Param("userId")String userId);

     
	String getSaleIdByOrderId(@Param("orderId")String orderId);

	String querySalePhoneBySaleId(@Param("saleId")String saleId);
   
	Ordera queryCalssSumPrice(@Param("orderId")String orderId);
    //saleId, orderId, 0,0,order.getSupplies_sumprice(), order.getTooldevices_sumprice()
	int insert(
			@Param("saleId")String saleId,
			@Param("orderId")String orderId,
			@Param("refund_money_haocai")Double refund_money_haocai,
			@Param("refund_money_gongju")Double refund_money_gongju,
			@Param("Supplies_sumprice")Double Supplies_sumprice,
			@Param("Tooldevices_sumprice")Double Tooldevices_sumprice
			);

	SaleInfo getSaleBySaleId(String saleId);
     //  //获取该订单的赠送钱币数  与userId
	Ordera queryGiveQBNumByOrderId(@Param("orderId")String orderId);
	
	//查询该用户钱币余额
	int queryUserQbNum(@Param("userId")String userId);
	 //把钱币放入该用户的余额中
	int saveQbToUser(@Param("userId")String userId,@Param("qb")String qb);
       //根据orderId  查 user_token
	String queryTokenByOrderId(@Param("orderId")String orderId);

	String queryNameByUserId(@Param("userId")String userId);

	OrderItem queryIt(@Param("itemSKU")String itemSKU, @Param("orderId")String orderId);

	int saveRechargeMessage(@Param("codeId")String codeId,
			@Param("userID")String userID,
			@Param("money")String money);

	String queryUserByToken(@Param("token")String token);

	User queryUserByUserId(@Param("uid")String uid);

	
	User queryUserByPhone(@Param("phone")String phone);

	SaleInfo getSaleByPhone(@Param("phone")String phone);
	

}
