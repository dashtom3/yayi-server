package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface CommentManageService {
	DataWrapper<List<Ordera>>  commentM(String orderId,String recoveryState,String phone,
			Integer currentPage,Integer numberPerpage);

	DataWrapper<Void> reply(String orderId, String itemId,
			String data);
}
