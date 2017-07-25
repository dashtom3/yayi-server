package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;


import com.yayiabc.http.mvc.pojo.jpa.Charge;

public interface WXPayDao {

	void addCharge(Charge charge);

	Integer getMoneyByChargeId(String chargeId);

	void addMoney(@Param("userId")String userId,@Param("addMoney") Integer addMoney);

	void updateChargeState(String chargeId);



	String getTokenByChargeId(String chargeId);

	void deleteOrderRecord(String orderId);

	void addOrderRecord(@Param("orderId")String orderId,@Param("outTradeNo") String outTradeNo);

	String getOrderIdByOutTradeNo(@Param("outTradeNo") String outTradeNo);

	void deleteChargeByToken(String token);

	Integer getStateByToken(String token);



}
