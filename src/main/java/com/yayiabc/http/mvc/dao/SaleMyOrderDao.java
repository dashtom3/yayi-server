package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.MyOrderVo;
import com.yayiabc.http.mvc.pojo.model.OrderInfoVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataVo;
@Repository
public interface SaleMyOrderDao {
	
	//折线图
	List<SaleDataVo> chart(
			@Param("saleId")String saleId,
			@Param("year")String year,
			@Param("month")String month);

	//统计数据查询
	SaleDataVo	queryData(
			@Param("saleId")String saleId);
	
	//累计收入
	String allCommission(
			@Param("saleId")String saleId);
	
	//订单列表信息查询
	List<MyOrderVo> queryOrderList(
			@Param("saleId")String saleId,
			@Param("page") Page page);

	int getCountOrderList(
			@Param("saleId")String saleId);
	
	//我的业绩详情
	OrderVo detail(
			@Param("saleId")String saleId,
			@Param("orderId")String orderId); 
	
	List<OrderInfoVo> detailOrderList(
			@Param("saleId")String saleId,
			@Param("orderId")String orderId);
}
