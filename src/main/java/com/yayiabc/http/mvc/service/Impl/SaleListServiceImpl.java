package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleListDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleListService;
@Service
public class SaleListServiceImpl implements SaleListService {

	@Autowired
	SaleListDao saleListDao;
	
	@Override
	public DataWrapper<List<SaleInfo>> query(String saleId, String phone,
			String trueName,Integer isBindUser, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleInfo>> dataWrapper =new DataWrapper<List<SaleInfo>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleListDao.getCount(saleId, phone, trueName,isBindUser);
		List<SaleInfo> list=saleListDao.query(saleId, phone, trueName,isBindUser, page);
		dataWrapper.setPage(page, totalNumber);
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
