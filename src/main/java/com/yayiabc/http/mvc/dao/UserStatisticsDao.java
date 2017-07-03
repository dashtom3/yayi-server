package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;

@Repository
public interface UserStatisticsDao {
	List<UserStatistics> query(@Param("phone")String phone,@Param("trueName")String trueName,@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("page")Page page);

	UserStatistics queryCount(@Param("userId")String userId,@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	int getCount(@Param("phone")String phone,@Param("trueName")String trueName,@Param("startDate")String startDate,@Param("endDate")String endDate);
}
