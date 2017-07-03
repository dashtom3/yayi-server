package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
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
	ShippingAddressDao shippingAddressDao;

	@Override
	public DataWrapper<List<UserAllInfo>> userlist(String phone,
			String trueName, String companyName, Integer isBindSale,
			Integer type, String saleName, Integer currentPage,
			Integer numberPerPage) {
		DataWrapper<List<UserAllInfo>> dataWrapper = new DataWrapper<List<UserAllInfo>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
	    page.setCurrentPage(currentPage);
		int totalNumber = userManageListDao.getCount(phone, trueName, companyName, isBindSale, type, saleName);
		dataWrapper.setPage(page, totalNumber);
		List<UserAllInfo> list = userManageListDao.userlist(phone, trueName,
				companyName, isBindSale, type, saleName, page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleInfo>> salelist(String salePhone,
			String saleName) {
		DataWrapper<List<SaleInfo>> dataWrapper = new DataWrapper<List<SaleInfo>>();
		List<SaleInfo> list = userManageListDao.salelist(salePhone, saleName);
		System.out.println("sale" + list.toString());
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> bind(String salePhone, String userPhone) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		int i = userManageListDao.bind(salePhone, userPhone);
		if (i > 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
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

}
