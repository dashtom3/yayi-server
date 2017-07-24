package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.MyOrderVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataStatistics;
import com.yayiabc.http.mvc.pojo.model.SaleDataVo;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

public interface SaleMyOrderService {
	DataWrapper<List<SaleDataVo>> chart(String token,String year,String month);
	
	DataWrapper<SaleDataVo> myOrderData(String token);
	
	DataWrapper<List<MyOrderVo>> myOrderList(String token,Integer currentPage, Integer numberPerPage);
	
	DataWrapper<OrderVo> detail(String token,String orderId);
}
