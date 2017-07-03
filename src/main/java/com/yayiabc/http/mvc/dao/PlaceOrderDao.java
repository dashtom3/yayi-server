package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;


public interface PlaceOrderDao {
	//购物车
	List<Cart> buyNows(String userId);
     
	//查询订货地址
	Receiver  queryReceiver(Integer receiverId);
   //查询包邮表数据
	List<FreeShipping> queryPostFree();
    //查询自定义运费表的数据
	List<PostFee> query();
     //用户钱币剩余
	int  ded(@Param("userId")String userId);
    //把购物车的商品同步到订单商品表
	void synchronization(@Param("cart")Cart cart,@Param("orderId")String orderId);
     //更新单个商品到订单商品表
	void synchronizationOne(@Param("orderItem")OrderItem orderItem);

	  //订单数据保存到订单表
	int  saveMessage(
			Ordera order
			/*@Param("orderId")String orderId, @Param("inHead")String inHead, @Param("registerNum")String registerNum,
			@Param("orderMessage")String orderMessage,@Param("phone") String phone*/);
  
	//伪清空购物车
	int  emptyCart(@Param("userId")String userId);
	
    //创建一个空order
	void createOrder(@Param("orderId")String orderId);	  
	
	 
	//用户不用默认  使用其他收货地址时
	public Receiver  upateAddress(@Param("receiverId")Integer receiverId);
}
