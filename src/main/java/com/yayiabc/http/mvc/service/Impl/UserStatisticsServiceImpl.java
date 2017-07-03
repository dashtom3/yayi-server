package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserStatisticsDao;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;
import com.yayiabc.http.mvc.service.UserStatisticsService;

@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

	@Autowired
	UserStatisticsDao userStatisticsDao;

	@Override
	public DataWrapper<List<UserStatistics>> query(String phone,
			String trueName, String startDate, String endDate,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<UserStatistics>> dataWrapper = new DataWrapper<List<UserStatistics>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = userStatisticsDao.getCount(phone, trueName,
				startDate, endDate);
		List<UserStatistics> list = userStatisticsDao.query(phone, trueName,
				startDate, endDate, page);
		dataWrapper.setPage(page, totalNumber);
		for (UserStatistics userStatistics : list) {
			UserStatistics us = userStatisticsDao.queryCount(
					userStatistics.getUserId(), startDate, endDate);
			userStatistics.setOrderaCount(us.getOrderaCount());
			userStatistics.setOrderaMoneyCount(us.getOrderaMoneyCount());
			userStatistics.setLatelyOrderDate(us.getLatelyOrderDate());
		}
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
