package com.yayiabc.http.mvc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface CommentManageDao{
	 List<String> showClassifyOne();

	List<Ordera> commentM(
			@Param("orderId")String  orderId,
			@Param("recoveryState")String  recoveryState,
			@Param("phone")String  phone,
			@Param("currentPage")Integer currentPage,
			@Param("numberPerPage")Integer numberPerpage
			 
			);

	int reply(@Param("orderId")String orderId,
			@Param("itemId")String itemId,
			@Param("data")String data);
}
