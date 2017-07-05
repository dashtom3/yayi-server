package com.yayiabc.http.mvc.dao;


import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface LogisticsDao {
	//获取   快递单号  公司编码
	Ordera queryLog(@Param("orderId")String orderId);
}
