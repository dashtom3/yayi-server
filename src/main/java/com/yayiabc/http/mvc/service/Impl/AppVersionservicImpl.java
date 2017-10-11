package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.AppVersionDao;
import com.yayiabc.http.mvc.service.AppVersionservic;
@Service
public class AppVersionservicImpl implements AppVersionservic{
	@Autowired
	private AppVersionDao appVersiondao;
	@Override
	public DataWrapper<Void> ver() {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		dataWrapper.setMsg(appVersiondao.ver());
		return dataWrapper;
		
	}

}
