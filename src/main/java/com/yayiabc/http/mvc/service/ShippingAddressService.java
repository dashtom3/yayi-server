package com.yayiabc.http.mvc.service;

import java.util.List;


import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;


public interface ShippingAddressService {
	   //新增收货地址	
	DataWrapper<Void>  addUserAdress(Receiver receiver,String token);
	   //修改收货地址
	DataWrapper<Void>  updateUserAddress(Receiver receiver,String token);
	
	//验证收货地址id的唯一性
	Integer addConditions(String token);
		int  updateIsdefault(Integer receiverId);
		
		//显示收货地址
		DataWrapper<List<Receiver>> showShoppingAddress(String token); 
		//删除
		DataWrapper<Integer> deleteShoppingAddress(String receiverId);
}
