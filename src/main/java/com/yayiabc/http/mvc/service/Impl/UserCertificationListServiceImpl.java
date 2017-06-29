package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UserCertificationListDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserCertificationListService;

@Service
public class UserCertificationListServiceImpl implements
		UserCertificationListService {

	@Autowired
	UserCertificationListDao userCertificationListDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public DataWrapper<List<User>> list(String phone, String trueName,
			String companyName, Integer type, Integer state) {
		DataWrapper<List<User>> dataWrapper = new DataWrapper<List<User>>();
		List<User> list = userCertificationListDao.list(phone, trueName,
				companyName, type, state);
		dataWrapper.setData(list);
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
			int i = userCertificationListDao.verify(userId, state, failReason);
			if (i > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

}
