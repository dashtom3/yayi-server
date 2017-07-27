package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comments;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;


public interface CommentManageService {
	DataWrapper<List<Comments>>  commentM(String orderId,Integer recoveryState,
			Integer currentPage,Integer numberPerpage);

	DataWrapper<Void> reply(Integer commentId,
			String data);
}
