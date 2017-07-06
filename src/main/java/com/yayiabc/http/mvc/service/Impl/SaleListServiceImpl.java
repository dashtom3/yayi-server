package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleListDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.SaleListService;

@Service
public class SaleListServiceImpl implements SaleListService {

	@Autowired
	SaleListDao saleListDao;

	@Override
	public DataWrapper<List<SaleInfo>> query(String saleId, String phone,
			String trueName, Integer isBindUser, Integer currentPage,
			Integer numberPerPage) {
		DataWrapper<List<SaleInfo>> dataWrapper = new DataWrapper<List<SaleInfo>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleListDao.getCount(saleId, phone, trueName,
				isBindUser);
		List<SaleInfo> list = saleListDao.query(saleId, phone, trueName,
				isBindUser, page);
		if (list.size() == 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} else if (list.size() != 0) {
			dataWrapper.setPage(page, totalNumber);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<User>> userlist(String phone, String trueName,
			String companyName, Integer isBind, Integer currentPage,
			Integer numberPerPage) {
		DataWrapper<List<User>> dataWrapper = new DataWrapper<List<User>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleListDao.userlistCount(phone, trueName,
				companyName, isBind);
		List<User> list = saleListDao.userlist(phone, trueName, companyName,
				isBind, page);
		if (list.size() == 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} else if (list.size() != 0) {
			dataWrapper.setPage(page, totalNumber);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<SaleInfo> detail(String phone, Integer currentPage,
			Integer numberPerPage) {
		DataWrapper<SaleInfo> dataWrapper = new DataWrapper<SaleInfo>();
		SaleInfo saleInfo = saleListDao.detail(phone);
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleListDao.bindUserCount(saleInfo.getSaleId());
		List<User> list = saleListDao.bindUserList(saleInfo.getSaleId(), page);
		if (list.size() == 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} else if (list.size() != 0) {
			dataWrapper.setPage(page, totalNumber);
			saleInfo.setUser(list);
			dataWrapper.setData(saleInfo);
		}
		return dataWrapper;
	}

}
