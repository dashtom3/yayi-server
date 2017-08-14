package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemStatisticsDao;
import com.yayiabc.http.mvc.pojo.model.ItemStatistics;
import com.yayiabc.http.mvc.service.ItemStatisticsService;

@Service
public class ItemStatisticsServiceImpl implements ItemStatisticsService {

	@Autowired
	ItemStatisticsDao itemStatisticsDao;

	@Override
	public DataWrapper<List<ItemStatistics>> query(String itemName,
			String itemId, String itemSKU, String itemBrandName,String startDate,String endDate,
			Integer currentPage, Integer numberPerPage,Integer state) {
		DataWrapper<List<ItemStatistics>> dataWrapper = new DataWrapper<List<ItemStatistics>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber =itemStatisticsDao.getCount(itemName, itemId, itemSKU, itemBrandName, startDate, endDate, state);
		dataWrapper.setPage(page, totalNumber);
		List<ItemStatistics> list = itemStatisticsDao.query(itemName, itemId, itemSKU, itemBrandName, startDate, endDate, page, state);
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
