package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.FinalList;


public interface PlaceOrderService {
   //点击购买 购物车

	DataWrapper<Integer> ded(String phone, int num, String sumItemsPrice);


    //下面的更改地址
	public DataWrapper<HashMap<String, Object>>  upateAddress(Integer receiverId,Double sumPrice,Integer itemSum);
	//将订单数据保存到订单表
	
     //伪清空购物车
	DataWrapper<Void> emptyCart(String phone);

	
	
	//1234
	DataWrapper<HashMap<String, Object>> generaOrder(String token, List<OrderItem> orderItem, Ordera order,
			 Invoice  invoice
			);


	DataWrapper<Invoice> queryLastInvoice(String token);
	
	
	//更改库存
	 boolean changeStockNum(List<OrderItem> orderItemList,List<FinalList> finalList);
}
