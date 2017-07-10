package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.MyOrderVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataStatistics;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;

@Repository
public interface SaleMyOrderDao {
	//查询我的订单
	List<MyOrderVo> myOrder(@Param("saleId")String saleId,@Param("page")Page page);
	
	int getCount(@Param("saleId")String saleId);
	
	//销售员收入等数据
	SaleDataStatistics myOrderData(@Param("saleId")String saleId);
	
	//销售员订单等数据
	SaleDataStatistics myOrderOrder(@Param("saleId")String saleId);
	
	//折线图
	List<SaleDataStatistics> chart(@Param("saleId")String saleId,@Param("year")String year,@Param("month")String month);

	//查看详情客户
	SaleIncomeVo detailS(@Param("userId")String userId,@Param("orderId")String orderId,@Param("saleId")String saleId);
	
	//查看详情订单
	List<OrderVo> detailO(@Param("userId")String userId,@Param("orderId")String orderId,@Param("saleId")String saleId);
}
