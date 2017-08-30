package com.yayiabc.http.mvc.service.Impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yayiabc.common.utils.PageModel;
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
	public DataWrapper<List<UserStatistics>> myClient(String value, final Integer state, String token, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<UserStatistics>> dataWrapper = new DataWrapper<List<UserStatistics>>();
		String saleId=saleLogDao.getSaleIdByToken(token);
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleMyClientDao.getCount(value, saleId);
		List<UserStatistics> list=saleMyClientDao.myClient(value,saleId,state, page);
		dataWrapper.setPage(page, totalNumber);
		for (UserStatistics userStatistics : list) {
			UserStatistics us=saleMyClientDao.queryCount(saleId,userStatistics.getUserId());
			if(us==null){
				userStatistics.setOrderaCount(0);
				userStatistics.setOrderaMoneyCount(0.0);
				userStatistics.setLatelyOrderDate(null);
			}else{
				userStatistics.setOrderaCount(us.getOrderaCount());
				userStatistics.setOrderaMoneyCount(us.getOrderaMoneyCount());
				userStatistics.setLatelyOrderDate(saleMyClientDao.getLatelyOrderDate(userStatistics.getUserId()));
			}
		}
		if(state == 3 || state ==4){
			Collections.sort(list, new Comparator<UserStatistics>() {
				public int compare(UserStatistics o1, UserStatistics o2) {
					if(state == 3){
						//按照订单总数进行降序排列
						if(o1.getOrderaCount() < o2.getOrderaCount()){
							return 1;
						}
						if(o1.getOrderaCount() == o2.getOrderaCount()){
							return 0;
						}
					}else if(state == 4){
						//按照累计订单金额进行降序排列
						if(o1.getOrderaMoneyCount() < o2.getOrderaMoneyCount()){
							return 1;
						}
						if(o1.getOrderaMoneyCount() == o2.getOrderaMoneyCount()){
							return 0;
						}
					}
					return -1;
				}
			});
		}
		PageModel pm = new PageModel(list,numberPerPage);
		List sublist=pm.getObjects(currentPage);
		dataWrapper.setData(sublist);
		return dataWrapper;
	}

	@Override
	public DataWrapper<String> getInvitation(String token) {
		DataWrapper<String> dataWrapper = new DataWrapper<String>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		String phone = saleLogDao.getPhoneById(saleId);
		dataWrapper.setData(phone);
		return dataWrapper;
	}

}
