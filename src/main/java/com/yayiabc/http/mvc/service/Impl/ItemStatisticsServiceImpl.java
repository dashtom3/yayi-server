package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.ItemStatisticsDao;
import com.yayiabc.http.mvc.pojo.model.ItemStatistics;
import com.yayiabc.http.mvc.service.ItemStatisticsService;

@Service
public class ItemStatisticsServiceImpl implements ItemStatisticsService {

	@Autowired
	ItemStatisticsDao itemStatisticsDao;

	@Override
	public DataWrapper<List<ItemStatistics>> query(String itemName,
			String itemId, String itemSKU, String itemBrandName,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<ItemStatistics>> dataWrapper = new DataWrapper<List<ItemStatistics>>();
		Page page=new Page();
		page.setCurrentPage(currentPage);
		page.setNumberPerPage(numberPerPage);
		int totalNumber =itemStatisticsDao.getCount(itemName, itemId, itemSKU, itemBrandName);
		dataWrapper.setPage(page, totalNumber);
		List<ItemStatistics> list = itemStatisticsDao.query(itemName, itemId,
				itemSKU, itemBrandName,page);
		for (ItemStatistics itemStatistics : list) {
			Integer refundNum = (Integer) itemStatisticsDao
					.queryRefund(itemStatistics.getItemId());
			itemStatistics.setRefundNum(refundNum);
		}
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
