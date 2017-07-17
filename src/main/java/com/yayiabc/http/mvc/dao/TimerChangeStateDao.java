package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
public interface TimerChangeStateDao {
/*
 * <update id="timerChangeState">
   update  ordera  set state =0 where order_id=#{orderId}
 </update>       
       <select id="timerQueryState"  resultType="int">
        select state from ordera where order_id=#{orderId}
       </select>
 */
	public int timerQueryState(@Param("orderId")String orderId);
	public int timerChangeState(@Param("orderId")String orderId);
	public List<OrderItem> queryOrderItems(@Param("orderId")String orderId);
	
	public int stillItemValueNum(@Param("itemSKU")String itemSKU, @Param("NUMM")String num);
}
