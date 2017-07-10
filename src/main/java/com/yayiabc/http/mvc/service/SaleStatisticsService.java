package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleStatistics;

public interface SaleStatisticsService {
	DataWrapper<List<SaleStatistics>> query(String phone,String trueName,Integer currentPage, Integer numberPerPage);
}
