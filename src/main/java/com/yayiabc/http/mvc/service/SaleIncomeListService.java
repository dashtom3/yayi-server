package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

public interface SaleIncomeListService {
	DataWrapper<List<SaleIncomeVo>> queryDone(String saleName,String salePhone,String beYearMonth,String startDate,String endDate,Integer currentPage, Integer numberPerPage);
	
	DataWrapper<List<SaleIncomeVo>> queryNot(String saleName,String salePhone,String beYearMonth,Integer currentPage, Integer numberPerPage);
	
	DataWrapper<SaleIncomeVo> detail(String saleId,String beYearMonth,String getState,Integer currentPage, Integer numberPerPage);
}
