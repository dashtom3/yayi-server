package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.CrawlerYellowPagesDao;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentistYa;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.service.CrawlerYellowPagesService;
@Service
public class CrawlerYellowPagesServiceImpl implements CrawlerYellowPagesService{
 
	@Autowired CrawlerYellowPagesDao crawlerYellowPagesDao;
	@Override
	public DataWrapper<List<Sheet1>> getYellowPage(Integer currentPage, Integer numberPerpage) {
		// TODO Auto-generated method stub
		DataWrapper<List<Sheet1>> dataWrapper=new DataWrapper<List<Sheet1>>();
		HashMap<Object,Object> hm=new HashMap<Object,Object>();
		Page page=new Page();
		
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		hm.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		hm.put("currentNum", String.valueOf(currentNum));
		
		List<Sheet1> listhm=crawlerYellowPagesDao.getYellowPage(hm);
		int count=crawlerYellowPagesDao.queryCountTOSheet1(hm);
		dataWrapper.setPage(page, count);
		dataWrapper.setData(listhm);
		return dataWrapper;
	}
	
	@Override
	public DataWrapper<List<DaForDentistYa>> getList(Integer currentPage, Integer numberPerpage) {
		// TODO Auto-generated method stub
		DataWrapper<List<DaForDentistYa>> dataWrapper=new DataWrapper<List<DaForDentistYa>>();
		HashMap<Object,Object> hm=new HashMap<Object,Object>();
		Page page=new Page();
		
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		hm.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		hm.put("currentNum", String.valueOf(currentNum));
		
		List<DaForDentistYa> listhm=crawlerYellowPagesDao.getList(hm);
		int count=crawlerYellowPagesDao.queryCountTOX(hm);
		dataWrapper.setPage(page, count);
		dataWrapper.setData(listhm);
		return dataWrapper;
	}

}
