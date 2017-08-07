package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.OrderNums;

public interface OrderDetailsDao {
	List<Ordera> orderDetailsShow(
			HashMap<String,String> map
			);

	int queryCount(@Param("userId")String userId,@Param("state")String state);
	
    //取消订单
	int  cancel(@Param("orderId")String orderId);
   //确定收货
	int  confirmReceipt(String orderId);
   //显示评论1
	Ordera showComItem(@Param("orderId")String orderId);
     //确定评论#{userId},#{itemId} ,#{orderId},#{commentGrade} ,#{commentContent}
	/**
	 * 
	 * @param userPhone
	 * @param userId
	 * @param itemId
	 * @param orderId
	 * @param commentGrade
	 * @param commentContent
	 * @param userName
	 * @return
	 * userId,itemIdListy.get(i).getItemId(),orderId,itemIdListy.get(i).getScore(),
					itemIdListy.get(i).getData(),itemIdListy.get(i).getItemSKU(),sb.toString()
	 */
	int makeSureCom(@Param("userId")String userId, @Param("itemId")String itemId,@Param("orderId")String orderId,
			
			@Param("commentGrade")String commentGrade,@Param("commentContent")String commentContent,
			@Param("itemSku")String itemSku,@Param("sb")String sb);
    //查询出 定下的商品明细
	Ordera queryItemDetails(Integer orderId);
    //
	int updateState(@Param("orderId")String orderId);
List<OrderNums> queryOrderNums(@Param("userId")String userId);

}
