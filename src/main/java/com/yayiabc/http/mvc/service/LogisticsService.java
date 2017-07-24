package com.yayiabc.http.mvc.service;


import com.yayiabc.common.utils.DataWrapper;

public interface LogisticsService {    //token, orderId
     
	DataWrapper<String> queryLog(String orderId);

	void updateState(String orderId);
}
