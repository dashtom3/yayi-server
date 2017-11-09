package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.model.qbRecordModel;

public interface UserQbListService {
	DataWrapper<List<QbRecord>> list(String phone, String startDate,
			String endDate, Integer currentPage, Integer numberPerPage);

	DataWrapper<Void> update(Integer qbBalance, String phone, String qbType,String sign);
	
	DataWrapper<Map<String, Integer>> queryQb(String userPhone,String qbType);

	DataWrapper<List<qbRecordModel>> queryQbRecord(String userMessage, String qbType, String payType,
			String orderCTime, String orderETime, Integer currentPage, Integer numberPerpage,HttpServletResponse response);
}
