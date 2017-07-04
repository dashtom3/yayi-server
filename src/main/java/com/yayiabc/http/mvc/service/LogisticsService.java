package com.yayiabc.http.mvc.service;

import java.util.HashMap;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface LogisticsService {    //token, orderId
     
	DataWrapper<String> queryLog(String token,String orderId);
}
