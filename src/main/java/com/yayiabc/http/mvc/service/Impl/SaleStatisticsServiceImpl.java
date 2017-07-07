package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleStatisticsDao;
import com.yayiabc.http.mvc.pojo.model.SaleStatistics;
import com.yayiabc.http.mvc.service.SaleStatisticsService;

@Service
public class SaleStatisticsServiceImpl implements SaleStatisticsService {

	@Autowired
	SaleStatisticsDao saleStatisticsDao;

	@Override
	public DataWrapper<List<SaleStatistics>> query(String phone,
			String trueName, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleStatistics>> dataWrapper = new DataWrapper<List<SaleStatistics>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleStatisticsDao.getCount(phone, trueName);
		List<SaleStatistics> list = saleStatisticsDao.query(phone, trueName,
				page);
		if (list.size() == 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} else if (list.size() != 0) {
			dataWrapper.setPage(page, totalNumber);
			dataWrapper.setData(list);
		}

		return dataWrapper;
	}

}
