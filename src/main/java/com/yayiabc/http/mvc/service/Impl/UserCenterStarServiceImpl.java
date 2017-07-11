package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserCenterStarDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.MyStar;
import com.yayiabc.http.mvc.service.UserCenterStarService;

@Service
public class UserCenterStarServiceImpl  implements UserCenterStarService{
	@Autowired
	private  UserCenterStarDao  usercenterstardao;	
	@Autowired
	private UtilsDao utilsDao;
	//显示收藏数据
	@Override
	public DataWrapper<List<MyStar>>shows(
			String token,
			Integer currentPage,Integer numberPerPage
			){
		DataWrapper<List<MyStar>> dataWrapper=new DataWrapper<List<MyStar>>();
		
		Page page=new Page();
		if(currentPage!=null&numberPerPage!=null){
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		}else{
			
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		//总条数
		int count=usercenterstardao.queryCount("item_star");
		System.out.println("总条数:"+count);
		System.out.println(page);
		dataWrapper.setPage(page,count);
	
		//String userId=userDao.getUserId(phone);
		String userId=utilsDao.getUserID(token);
		System.out.println(userId);
		List<MyStar> itemStarList=usercenterstardao.shows(userId,page);
		dataWrapper.setData(itemStarList);
		if(itemStarList.isEmpty()){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("暂无数据信息");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			
			return dataWrapper;
		}
	}
	//取消收藏
	@Override
	public DataWrapper<Void> deleteStarOne(String token,String deleteOneId){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//String userId=userDao.getUserId(phone);
		String userId=utilsDao.getUserID(token);
		int state= usercenterstardao.deleteStarOne(deleteOneId,userId);
		if(state>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
			return dataWrapper;
		}

	}
	@Override
	public DataWrapper<Void> deleteStarAll(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		int st=usercenterstardao.deleteStarAll(userId);
		if(st>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
			return dataWrapper;
		}
	}
	//新增收藏
	@Override
	public DataWrapper<Void> addMyStar(String token, String itemId) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		int st=0;
		//根据当前商品id  查询是否已经收藏1
		List<Integer> list=usercenterstardao.queryOne(itemId,userId);
		if(list.isEmpty()){
			st=usercenterstardao.addMyStar(userId, itemId);
		}
		if(st>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
			return dataWrapper;
		 }else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
			return dataWrapper;
		}
	}
}
