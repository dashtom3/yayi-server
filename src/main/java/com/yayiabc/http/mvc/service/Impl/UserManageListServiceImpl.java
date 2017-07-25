package com.yayiabc.http.mvc.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleListDao;
import com.yayiabc.http.mvc.dao.ShippingAddressDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserManageListDao;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.model.UserAllInfo;
import com.yayiabc.http.mvc.service.UserManageListService;

@Service
public class UserManageListServiceImpl implements UserManageListService {

	@Autowired
	UserManageListDao userManageListDao;
	@Autowired
	UserDao userDao;
	@Autowired
	SaleListDao saleListDao;
	@Autowired
	ShippingAddressDao shippingAddressDao;

	@Override
	public DataWrapper<List<Map<String,String>>> userlist(String phone,
			String trueName, String companyName, Integer isBindSale,
			Integer type, String saleName,String salePhone, Integer currentPage,
			Integer numberPerPage) {
		DataWrapper<List<Map<String,String>>> dataWrapper = new DataWrapper<List<Map<String,String>>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
	    page.setCurrentPage(currentPage);
		int totalNumber = userManageListDao.getCount(phone, trueName, companyName, isBindSale, type, saleName);
		dataWrapper.setPage(page, totalNumber);
		List<Map<String,String>> list = userManageListDao.userlist(phone, trueName, companyName, isBindSale, type, saleName, salePhone, page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleInfo>> salelist(String salePhone,
			String saleName,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<SaleInfo>> dataWrapper = new DataWrapper<List<SaleInfo>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
	    page.setCurrentPage(currentPage);
	    int totalNumber = userManageListDao.getSalelistCount(salePhone, saleName);
	    dataWrapper.setPage(page, totalNumber);
		List<SaleInfo> list = userManageListDao.salelist(salePhone, saleName,page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> bind(String salePhone, String userPhone) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId=userDao.getUserId(userPhone);
		String saleId=saleListDao.getSaleId(salePhone);
		if(userId == null || saleId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			int i = userManageListDao.bind(salePhone, userPhone);
			if (i > 0) {
				userManageListDao.bindUpdateNum(saleId);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<UserAllInfo> detail(String phone) {
		DataWrapper<UserAllInfo> dataWrapper = new DataWrapper<UserAllInfo>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			UserAllInfo userAllInfo = userManageListDao.detail(userId);
			List<Receiver> receiverList = shippingAddressDao
					.showShoppingAddress(userId);
			userAllInfo.setReceiverList(receiverList);
			dataWrapper.setData(userAllInfo);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> disBind(String salePhone, String userPhone) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(userPhone);
		String saleId = saleListDao.getSaleId(salePhone);
		if (userId == null || saleId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			int i = userManageListDao.disBind(salePhone, userPhone);
			if (i > 0) {
				userManageListDao.bindUpdateNum(saleId);
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

}
