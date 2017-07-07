package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;

public interface SaleMyClientService {
	DataWrapper<List<UserStatistics>> myClient(String value, String token,
			Integer currentPage, Integer numberPerPage);
}
