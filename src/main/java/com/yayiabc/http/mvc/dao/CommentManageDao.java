package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Comments;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface CommentManageDao{
	 List<String> showClassifyOne();

	List<Comments> commentM(
			@Param("orderId")String  orderId,
			@Param("recoveryState")Integer  recoveryState,
			@Param("currentNumber")Integer currentNumber,
			@Param("numberPerPage")Integer numberPerpage
			 
			);

	int reply(@Param("commentId")Integer commentId,
			@Param("data")String data);

	Integer queryCount(@Param("orderId")String orderId,@Param("recoveryState") Integer recoveryState);
}
