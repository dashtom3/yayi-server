package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;

public interface UserQbListService {
	DataWrapper<List<QbRecord>> list(String phone, String startDate,
			String endDate, Integer currentPage, Integer numberPerPage);

	DataWrapper<Void> update(Integer qbBalance, String phone);
	
	DataWrapper<Map<String, Integer>> queryQb(String userPhone);
}
