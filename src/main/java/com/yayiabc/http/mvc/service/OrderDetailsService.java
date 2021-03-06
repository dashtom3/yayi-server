package com.yayiabc.http.mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.OrderNums;
import com.yayiabc.http.mvc.pojo.model.itemIdList;

public interface OrderDetailsService {
	DataWrapper<List<Ordera>>  orderDetailsShow(HashMap<String,String> map,String newPhone
			,Integer currentPage,Integer numberPerpage
			);
	
    //取消订单
	DataWrapper<Void> cancel(String orderId, String token);
    //确定收货
	DataWrapper<Void> confirmReceipt(String orderId);
    //显示评论相关内容
	DataWrapper<Ordera> showComItem(String orderId);
    //提交评论
	DataWrapper<Void> makeSureCom(String token,String orderId,ArrayList<itemIdList> itemIdListy);

	//void queryItemDetails(Integer orderId);
     //结账
	void payment(Integer orderId, Integer receiverId);

	//DataWrapper<HashMap<String, Object>> payment(String token, String orderItemNum);
	
	DataWrapper<List<OrderNums>> queryOrderNums(String userId);
}
