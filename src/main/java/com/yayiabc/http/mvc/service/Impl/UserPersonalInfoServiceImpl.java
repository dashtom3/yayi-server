package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserPersonalInfoDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
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
	@Autowired
	UtilsDao utilsDao;

	@Override
	public DataWrapper<User> updateUser(User user,String token) {
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		String userIda = userDao.getUserId(user.getPhone());// 根据手机号码查询userId
		if (userIda == null || userIda.equals(utilsDao.getUserID(token))== false) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
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
			Certification certification, String phone, String token) {
		DataWrapper<Certification> dataWrapper = new DataWrapper<Certification>();
		String userId = userDao.getUserId(phone);
		if (userId == null || userId.equals(utilsDao.getUserID(token)) == false) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
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
	public DataWrapper<UserPersonalInfo> detail(String phone, String token) {
		DataWrapper<UserPersonalInfo> dataWrapper = new DataWrapper<UserPersonalInfo>();
		String userId = userDao.getUserId(phone);
		if (userId == null || userId.equals(utilsDao.getUserID(token)) == false) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			UserPersonalInfo userPersonalInfo = userPersonalInfoDao
					.detail(userId);
			dataWrapper.setData(userPersonalInfo);
		}

		return dataWrapper;
	}

}
