package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.FindCusDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.FindCusService;
@Service
public class FindCusServiceImpl implements FindCusService {
	@Autowired
	private FindCusDao findDao;
	@Autowired
	private UtilsDao utilsDao;

	
	@Override
	//查询  已注册  未绑定的
	public DataWrapper<List<User>> show(String state,Integer currentPage,Integer numberPerPage,String cityName) {
		
		DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		//总条数
		Integer count=findDao.queryCounts(state,cityName);
		Integer numberPerpage=page.getNumberPerPage();
		Integer currentNum=page.getCurrentNumber();
		dataWrapper.setPage(page,count);
		dataWrapper.setData(findDao.showsss(state,currentNum,numberPerpage,cityName));
		return dataWrapper;
	}
	@Override
	public DataWrapper<List<User>> showMyCus(String saleToken) {
		// TODO Auto-generated method stub
	    DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		String saleId=utilsDao.getSaleId(saleToken);
		List<User> list=findDao.showMyCus(saleId);
		dataWrapper.setData(list);
		return dataWrapper;
	}
	//查询 未注册
	@Override
	public DataWrapper<List<CusResources>> shows(String state,Integer currentPage,Integer numberPerPage){
		DataWrapper<List<CusResources>> dataWrapper=new DataWrapper<List<CusResources>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		//总条数
		Integer count=findDao.queryCount(state);
		Integer numberPerpage=page.getNumberPerPage();
		Integer currentNum=page.getCurrentNumber();
		dataWrapper.setPage(page,count);
		
		dataWrapper.setData(findDao.shows(state,currentNum,numberPerpage));
		return dataWrapper;
	}
}
