package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface UserQbListService {
	DataWrapper<List<QbRecord>> list(String phone, String startDate, String endDate);

	DataWrapper<Void> update(Integer qbBalance, String phone);
}
