package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;

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
	public DataWrapper<List> ver() {
		DataWrapper<List> dataWrapper =new DataWrapper<List>();
		dataWrapper.setData(appVersiondao.ver());
		return dataWrapper;

	}
}
