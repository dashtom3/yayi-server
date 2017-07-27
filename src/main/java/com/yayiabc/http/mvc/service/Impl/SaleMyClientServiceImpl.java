package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.SaleMyClientDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;
import com.yayiabc.http.mvc.service.SaleMyClientService;
@Service 
public class SaleMyClientServiceImpl implements SaleMyClientService {

	@Autowired
	SaleMyClientDao saleMyClientDao;
	@Autowired
	SaleLogDao saleLogDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public DataWrapper<List<UserStatistics>> myClient(String value,
			String token, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<UserStatistics>> dataWrapper = new DataWrapper<List<UserStatistics>>();
		String saleId=saleLogDao.getSaleIdByToken(token);
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleMyClientDao.getCount(value, saleId);
		List<UserStatistics> list=saleMyClientDao.myClient(value, saleId, page);
		dataWrapper.setPage(page, totalNumber);
		for (UserStatistics userStatistics : list) {
			UserStatistics us=saleMyClientDao.queryCount(userStatistics.getUserId());
			if(us==null){
				userStatistics.setOrderaCount("");
				userStatistics.setOrderaMoneyCount("");
				userStatistics.setLatelyOrderDate("");
			}else{
				userStatistics.setOrderaCount(us.getOrderaCount());
				userStatistics.setOrderaMoneyCount(us.getOrderaMoneyCount());
				userStatistics.setLatelyOrderDate(saleMyClientDao.getLatelyOrderDate(userStatistics.getUserId()));
			}
		}
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
