package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.LogisticsDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.LogisticsService;

@Service
public class LogisticsServiceImpl implements LogisticsService{
   @Autowired
   private LogisticsDao logisticsDao;
   @Autowired
   private UtilsDao utilsDao;
@Override
public DataWrapper<String> queryLog(String token, String orderId) {
	// TODO Auto-generated method stub
	DataWrapper<String> dataWrapper=new DataWrapper<String>();
	Ordera order=logisticsDao.queryLog(orderId);
	String s=null;
	try {
		s = new LogisticsMain().getOrderTracesByJson(order.getShippingName(), order.getShippingCode());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(s==null){
		dataWrapper.setMsg("暂无物流信息");
	}else{
		dataWrapper.setData(s);
	}
//	return s;
	return dataWrapper;
}
}
