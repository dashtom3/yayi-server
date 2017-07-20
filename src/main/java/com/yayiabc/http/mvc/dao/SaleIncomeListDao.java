package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

@Repository
public interface SaleIncomeListDao {
	
	// 销售员业绩列表,已结算
	List<SaleIncomeVo> queryDone(
			@Param("saleName") String saleName,
			@Param("salePhone") String salePhone,
			@Param("beYearMonth") String beYearMonth,
			@Param("settlementTime") String settlementTime,
			@Param("page") Page page);

	int getCountDone(
			@Param("saleName") String saleName,
			@Param("salePhone") String salePhone,
			@Param("beYearMonth") String beYearMonth,
			@Param("settlementTime") String settlementTime);

	// 销售员业绩列表,未结算
	List<SaleIncomeVo> queryNot(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("saleName") String saleName,
			@Param("salePhone") String salePhone,
			@Param("beYearMonth") String beYearMonth, 
			@Param("page") Page page);
	
	int getCountNot(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("saleName") String saleName,
			@Param("salePhone") String salePhone,
			@Param("beYearMonth") String beYearMonth);
	
	//详情
	SaleIncomeVo detail(
			@Param("saleId")String saleId,
			@Param("beYearMonth") String beYearMonth);
	
	List<OrderVo> orderList(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("saleId")String saleId,
			@Param("page") Page page);
	
	int getCountOrderList(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("saleId")String saleId);
}
