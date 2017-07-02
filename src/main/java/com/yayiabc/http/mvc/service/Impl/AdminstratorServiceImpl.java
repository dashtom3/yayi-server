package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.AdminstratorDao;
import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
import com.yayiabc.http.mvc.service.AdminstratorService;

@Service
public class AdminstratorServiceImpl implements AdminstratorService{
	@Autowired
	private AdminstratorDao adminstratorDao;
	
	@Override
	public DataWrapper<Void> addAdminstrator(String phone,
			String adminstratorPwd, String trueName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Adminstrator adminstrator =new Adminstrator();
		adminstrator.setPhone(phone);
		adminstrator.setTrueName(trueName);
		adminstratorPwd=MD5Util.getMD5String(adminstratorPwd);
		adminstrator.setAdminstratorPwd(adminstratorPwd);
		adminstratorDao.addAdminstrator(adminstrator);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteAdminstrator(Integer adminstratorId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		adminstratorDao.deleteAdminstrator(adminstratorId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateAdminstrator(Integer adminstratorId,
			String phone, String adminstratorPwd, String trueName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Adminstrator adminstrator =new Adminstrator();
		adminstrator.setAdminstratorId(adminstratorId);
		adminstrator.setPhone(phone);
		adminstrator.setTrueName(trueName);
		adminstratorPwd=MD5Util.getMD5String(adminstratorPwd);
		adminstrator.setAdminstratorPwd(adminstratorPwd);
		adminstratorDao.updateAdminstrator(adminstrator);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Adminstrator>> queryAdminstrator(String phone,
			String trueName) {
		DataWrapper<List<Adminstrator>> dataWrapper =new DataWrapper<List<Adminstrator>>();
		if("".equals(phone)){
			phone=null;
		}
		if("".equals(trueName)){
			trueName=null;
		}
		List<Adminstrator> adminstratorList=adminstratorDao.queryAdminstrator(phone,trueName);
		dataWrapper.setData(adminstratorList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> loginAdminstrator(String phone,
			String adminstratorPwd) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		adminstratorPwd=MD5Util.getMD5String(adminstratorPwd);
		Adminstrator adminstrator =adminstratorDao.loginAdminstrator(phone,adminstratorPwd);
		if (adminstrator!=null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			String msg=dataWrapper.getErrorCode().getLabel();
			dataWrapper.setMsg(msg);
		}else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Login_Error);
			String msg=dataWrapper.getErrorCode().getLabel();
			dataWrapper.setMsg(msg);
		}
		return dataWrapper;
	}

}
