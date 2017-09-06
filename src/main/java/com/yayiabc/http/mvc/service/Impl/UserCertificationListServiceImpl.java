package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.Certification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserCertificationListDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserCertificationListService;

import javax.xml.crypto.Data;

@Service
public class UserCertificationListServiceImpl implements
		UserCertificationListService {

	@Autowired
	UserCertificationListDao userCertificationListDao;
	@Autowired
	UserDao userDao;

	@Override
	public DataWrapper<List<User>> list(String phone, String trueName,
			String companyName, Integer type, Integer state,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<User>> dataWrapper = new DataWrapper<List<User>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = userCertificationListDao.getCount(phone, trueName,
				companyName, type, state);
		dataWrapper.setPage(page, totalNumber);
		List<User> list = userCertificationListDao.list(phone, trueName,
				companyName, type, state, page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Certification> detail(String userId) {
		DataWrapper<Certification> dataWrapper=new DataWrapper<Certification>();
		Certification certification=userCertificationListDao.detail(userId);
		dataWrapper.setData(certification);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> verify(String phone, Integer state,
			String failReason) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			if(state == 3){
				userCertificationListDao.verifyFail(userId);
				userCertificationListDao.verify(userId, state, failReason);
			}else{
				userCertificationListDao.verify(userId, state, failReason);
			}
		}
		return dataWrapper;
	}

}
