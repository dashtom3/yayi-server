package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface AliPayDao {
     //更改 订单状态
	int updateStateAndPayTime(@Param("orderId")String orderId);
}
