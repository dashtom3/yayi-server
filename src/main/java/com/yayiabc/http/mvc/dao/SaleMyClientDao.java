package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleMyClientDao {
	List<UserStatistics> myClient(@Param("value")String value,@Param("saleId")String saleId,@Param("page")Page page);
	
	int getCount(@Param("value")String value,@Param("saleId")String saleId);

	UserStatistics queryCount(@Param("saleId")String saleId,@Param("userId")String userId);
	
	String getLatelyOrderDate(@Param("userId")String userId,@Param("state")Integer state);
}
