package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

public interface SaleIncomeListService {
	DataWrapper<List<SaleIncomeVo>> query(String saleId,String saleName,String salePhone,String orderId,Integer signLateSeven,Integer getState,String startDate,String endDate,Integer currentPage, Integer numberPerPage);
	
	DataWrapper<SaleIncomeVo> detail(String saleId,String userId,String orderId);
}
