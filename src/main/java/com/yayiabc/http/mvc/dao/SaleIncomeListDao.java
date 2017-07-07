package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

@Repository
public interface SaleIncomeListDao {
	List<SaleIncomeVo> query(@Param("saleId")String saleId,@Param("saleName")String saleName,@Param("salePhone")String salePhone,
			@Param("orderId")String orderId,@Param("signLateSeven")Integer signLateSeven,@Param("getState")Integer getState,
			@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("page")Page page);
	
	int getCount(@Param("saleId")String saleId,@Param("saleName")String saleName,@Param("salePhone")String salePhone,
			@Param("orderId")String orderId,@Param("signLateSeven")Integer signLateSeven,@Param("getState")Integer getState,
			@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	SaleIncomeVo detail(@Param("saleId")String saleId,@Param("userId")String userId);
	
	List<OrderVo> orderList(@Param("userId")String userId,@Param("orderId")String orderId);
}
