package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.LogisticsDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.LogisticsService;

@Service

public class LogisticsServiceImpl implements LogisticsService{
	@Autowired
	private LogisticsDao logisticsDao;
	@Override
	public DataWrapper<String> queryLog(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<String> dataWrapper=new DataWrapper<String>();
		Ordera order=logisticsDao.queryLog(orderId);
		if(order!=null){
			String s=null;
			try {                                                 //快递公司编码                                 //快递单号
				s = new LogisticsMain().getOrderTracesByJson(order.getShippingName(), order.getShippingCode());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(s==null){
				dataWrapper.setMsg("暂无物流信息");
			}else{
				dataWrapper.setData(s);
				dataWrapper.setMsg(order.getShippingName()+"-"+order.getShippingCode());
			}
			//	return s;
			return dataWrapper;
		}
		return dataWrapper;
		}
	//自动签收
	@Override
	public int updateState(String orderId) {
		// TODO Auto-generated method stub
		return logisticsDao.updateState(orderId);  
	}
}
