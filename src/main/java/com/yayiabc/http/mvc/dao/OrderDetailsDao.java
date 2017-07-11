package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface OrderDetailsDao {
	List<User> orderDetailsShow(
			HashMap<String,String> map
			);

	int queryCount(HashMap<String, String> map);
	
    //取消订单
	int  cancel(@Param("orderId")String orderId);
   //确定收货
	int  confirmReceipt(String orderId);
   //显示评论1
	Ordera showComItem(@Param("orderId")String orderId);
     //确定评论
	int makeSureCom(HashMap<String, String> hashMap);
    //查询出 定下的商品明细
	Ordera queryItemDetails(Integer orderId);
	

}
