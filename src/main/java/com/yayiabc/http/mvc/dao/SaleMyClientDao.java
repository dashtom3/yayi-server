package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;

@Repository
public interface SaleMyClientDao {
	List<UserStatistics> myClient(@Param("value")String value,@Param("saleId")String saleId,@Param("state")Integer state,@Param("page")Page page);
	
	int getCount(@Param("value")String value,@Param("saleId")String saleId);

	UserStatistics queryCount(@Param("saleId")String saleId,@Param("userId")String userId);
	
	String getLatelyOrderDate(@Param("userId")String userId);
}
