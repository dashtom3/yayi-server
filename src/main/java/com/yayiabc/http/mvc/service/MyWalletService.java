package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.TreeMap;

import com.yayiabc.common.utils.DataWrapper;

public interface MyWalletService {

	DataWrapper<TreeMap<String, Object>> myWalletDetails(String token,int date,int state);
   
	
	//查看订单详情
	void queryOrder(String orderId, String token);

}
