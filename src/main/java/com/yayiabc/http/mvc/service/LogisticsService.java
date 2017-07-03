package com.yayiabc.http.mvc.service;

import java.util.HashMap;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface LogisticsService {
     
	HashMap<String,Ordera> queryLog(String phone,String itemId);
}
