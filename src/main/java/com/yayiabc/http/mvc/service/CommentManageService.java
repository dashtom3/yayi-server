package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface CommentManageService {
	DataWrapper<List<Map<String,String>>>  commentM(String orderId,String userId,String recoveryState,String phone);

	DataWrapper<Void> reply(String orderId, String itemId,
			String data);
}
