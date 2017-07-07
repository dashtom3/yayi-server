package com.yayiabc.http.mvc.service;

import java.util.TreeMap;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

public interface MyWalletService {

	DataWrapper<TreeMap<String, Object>> myWalletDetails(String token,Integer state);
   
	
	//查看订单详情
	DataWrapper<SaleInfo> queryOrder(String orderId, String token);

}
