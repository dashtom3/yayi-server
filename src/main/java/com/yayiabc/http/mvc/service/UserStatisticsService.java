package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;

public interface UserStatisticsService {
	DataWrapper<List<UserStatistics>> query(String phone,String trueName,String startDate,String endDate,Integer currentPage, Integer numberPerPage);
}
