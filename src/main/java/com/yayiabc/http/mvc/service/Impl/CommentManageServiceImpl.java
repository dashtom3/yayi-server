package com.yayiabc.http.mvc.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.CommentManageDao;
import com.yayiabc.http.mvc.dao.UserCenterStarDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.CommentManageService;
@Service
public class CommentManageServiceImpl implements CommentManageService {
	@Autowired
	private CommentManageDao commentManageDao;
   @Autowired
   private UserCenterStarDao userCenterStarDao;
	@Override
	public DataWrapper<List<Ordera>> commentM(
			String orderId,Integer recoveryState,String phone,
			Integer currentPage,Integer numberPerpage
			) {
		Page page=new Page();
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		//总条数
		Integer count=commentManageDao.queryCount(orderId,recoveryState,phone);
		
		
		DataWrapper<List<Ordera>> dataWrapper=new DataWrapper<List<Ordera>>();
		
		dataWrapper.setPage(page,count);
		return dataWrapper;
	}
	//回复评论
	@Override
	public DataWrapper<Void> reply(String orderId, String itemId, String data) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=commentManageDao.reply(orderId,itemId,data);
		if(state>0){
			dataWrapper.setMsg("回复成功");
		}else{
			dataWrapper.setMsg("回复失败");
		}
		return dataWrapper;
	}
}
