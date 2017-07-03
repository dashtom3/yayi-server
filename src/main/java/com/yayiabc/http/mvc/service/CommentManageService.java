package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import com.yayiabc.common.utils.DataWrapper;


public interface CommentManageService {
	DataWrapper<List<Map<String,String>>>  commentM(String orderId,String recoveryState,
			Integer currentPage,Integer numberPerpage);

	DataWrapper<Void> reply(String orderId, String itemId,
			String data);
}
