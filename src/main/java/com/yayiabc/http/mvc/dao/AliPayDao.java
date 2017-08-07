package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface AliPayDao {
     //更改 订单状态
	int updateStateAndPayTime(@Param("orderId")String orderId);
    //查询  订单的 实际付款
	Double queryYorderIdAndActualMonry(@Param("orderId")String orderId);
	//商品名称序列
	List<String> queryYitemNames(@Param("orderId")String orderId);
	
	//订单留言
	 //查看订单状态码
	String queryYorderMessage(@Param("orderId")String orderId);
	int querySatetIsTwo(String out_trade_no);
	
	//获取该订单
	Ordera queryOrder(@Param("orderId")String out_trade_no);
	//更改用户钱币
	int updateQb(@Param("qbDed")Integer qbDed,@Param("userId")String userId);
	//增加销量
	int addSales(@Param("itemId")String itemId,@Param("num")Integer num);
	Charge queryUserId(@Param("out_trade_no")String out_trade_no);
	int  updateState(@Param("out_trade_no")String out_trade_no);
}
