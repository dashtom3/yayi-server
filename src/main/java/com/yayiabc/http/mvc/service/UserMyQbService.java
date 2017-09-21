package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;

public interface UserMyQbService {
	DataWrapper<Void> add(QbRecord qbRecord, String token);

	DataWrapper<List<QbRecord>> query(String token, Integer currentPage, Integer numberPerPage);

	int updateDataToUser(List<Integer> listData,String userId);

	int addMessageQbQ(String dedNums, String userId, String s,int Mi);
	
	 boolean adds(QbRecord qbRecord);

	int addMessageQbQRget(String string, String userId, String string2, int mI);
}
