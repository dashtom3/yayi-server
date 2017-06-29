package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.User;


public interface CommentManageDao{
	 List<String> showClassifyOne();

	List<Ordera> commentM(
			@Param("userId")String  userId,
			@Param("orderId")String  orderId,
			@Param("recoveryState")String  recoveryState,
			@Param("phone")String  phone
			);

	int reply(@Param("orderId")String orderId,
			@Param("itemId")String itemId,
			@Param("data")String data);
}
