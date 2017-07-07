package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleDataStatistics;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

public interface SaleMyOrderService {
	DataWrapper<SaleDataStatistics> myOrder(String token,Integer currentPage, Integer numberPerPage);
	
	DataWrapper<List<SaleDataStatistics>> chart(String token,String year,String month);
	
	DataWrapper<SaleIncomeVo> detail(String userPhone,String orderId,String token);
}
