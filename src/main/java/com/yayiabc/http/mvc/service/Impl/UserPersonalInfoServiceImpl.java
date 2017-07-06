package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserPersonalInfoDao;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;
import com.yayiabc.http.mvc.service.UserPersonalInfoService;

@Service
public class UserPersonalInfoServiceImpl implements UserPersonalInfoService {

	@Autowired
	UserPersonalInfoDao userPersonalInfoDao;
	@Autowired
	UserDao userDao;

	@Override
	public DataWrapper<User> updateUser(User user) {
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		String userIda = userDao.getUserId(user.getPhone());// 根据手机号码查询userId
		if (userIda == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			user.setUserId(userIda);
			String userIdb = userPersonalInfoDao.getUserIdOnC(userIda);// 查询资质认证表userId
			if (userIdb == null) {
				userPersonalInfoDao.add(userIda);
				int i = userPersonalInfoDao.updateUser(user);
				if (i > 0) {
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else if (userIdb.equals(userIda) == true) {
				int i = userPersonalInfoDao.updateUser(user);
				if (i > 0) {
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Certification> updateCertification(
			Certification certification, String phone) {
		DataWrapper<Certification> dataWrapper = new DataWrapper<Certification>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			certification.setUserId(userId);
			int i = userPersonalInfoDao.updateCertification(certification);
			if (i > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}
	
	@Override
	public DataWrapper<UserPersonalInfo> detail(String phone) {
		DataWrapper<UserPersonalInfo> dataWrapper = new DataWrapper<UserPersonalInfo>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			UserPersonalInfo userPersonalInfo = userPersonalInfoDao.detail(userId);
			dataWrapper.setData(userPersonalInfo);
		}

		return dataWrapper;
	}



	
}
