package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface PlaceOrderService {
   //点击购买 购物车
	DataWrapper<HashMap<String, Object>> buyNows(String token,String[] itemSKUs);

	DataWrapper<Void> ded(String phone, int num);

	DataWrapper<HashMap<String, Object>> buyNow(OrderItem orderItem, String token);	

    //下面的更改地址
	public DataWrapper<HashMap<String, Object>>  upateAddress(Integer receiverId,Integer sumPrice,Integer itemSum);
    
	//将订单数据保存到订单表
	DataWrapper<Void> saveMessage(
			Ordera ordera,String phone
			/*String orderId,  String inHead2,
			String registerNum, String orderMessage,String phone*/);
	
     //伪清空购物车
	DataWrapper<Void> emptyCart(String phone);
}
