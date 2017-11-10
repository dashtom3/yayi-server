package com.yayiabc.http.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.model.ExpireOrder;
public interface TimerChangeStateDao {
/*
 * <update id="timerChangeState">
   update  ordera  set state =0 where order_id=#{orderId}
 </update>       
       <select id="timerQueryState"  resultType="int">
        select state from ordera where order_id=#{orderId}
       </select>
 */
	public Integer timerQueryState(@Param("orderId")String orderId);
	public Integer timerChangeState(@Param("orderId")String orderId);
	public List<OrderItem> queryOrderItems(@Param("orderId")String orderId);
	
	public Integer stillItemValueNum(List<OrderItem> orderItemList);
	
	public int closeOrder(List<String> temporaryList);
	
	public List<OrderItem> queryOrderItemNums(List<String> temporaryList);
	
	public int stillItemsListValueNum(List<OrderItem> orderItemNums);
	
	/*2.0*/
	public int closeOrder2(String orderId);
	public int returnStackItemNum(ArrayList<ExpireOrder> expireOrderItemList);
}
