package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;


public interface PlaceOrderService {
   //点击购买 购物车
	DataWrapper<HashMap<String, Object>> buyNows(String phone,Integer receiverId);

	DataWrapper<Void> ded(String phone, int num);

	DataWrapper<HashMap<String, Object>> buyNow(OrderItem orderItem, String phone,Integer receiverId);	

    //下面的更改地址
	public DataWrapper<Receiver>  upateAddress(Integer receiverId);
    
	//将订单数据保存到订单表
	DataWrapper<Void> saveMessage(
			Ordera ordera,String phone
			/*String orderId,  String inHead2,
			String registerNum, String orderMessage,String phone*/);
	
     //伪清空购物车
	DataWrapper<Void> emptyCart(String phone);
}
