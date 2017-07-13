package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface AliPayDao {
     //更改 订单状态
	int updateStateAndPayTime(@Param("orderId")String orderId);
    //查询  订单的 实际付款
	double queryYorderIdAndActualMonry(@Param("orderId")String orderId);
	//商品名称序列
	List<String> queryYitemNames(@Param("orderId")String orderId);
	
	//订单留言
	String queryYorderMessage(@Param("orderId")String orderId);
	 //查看订单状态码
	int querySatetIsTwo(String out_trade_no);
}
