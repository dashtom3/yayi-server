package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleStatisticsDao;
import com.yayiabc.http.mvc.pojo.model.saleStatistics;
import com.yayiabc.http.mvc.service.SaleStatisticsService;

@Service
public class SaleStatisticsServiceImpl implements SaleStatisticsService {

	@Autowired
	SaleStatisticsDao saleStatisticsDao;
	
	@Override
	public DataWrapper<List<saleStatistics>> query(String phone,
			String trueName, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<saleStatistics>> dataWrapper=new DataWrapper<List<saleStatistics>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleStatisticsDao.getCount(phone, trueName);
		List<saleStatistics> list=saleStatisticsDao.query(phone, trueName, page);
		dataWrapper.setPage(page, totalNumber);
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
