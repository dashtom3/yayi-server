package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;

public interface SaleMyClientDao {
	List<UserStatistics> myClient(@Param("value")String value,@Param("saleId")String saleId,@Param("page")Page page);

	UserStatistics queryCount(@Param("userId")String userId);
	
	int getCount(@Param("value")String value,@Param("saleId")String saleId);
}
