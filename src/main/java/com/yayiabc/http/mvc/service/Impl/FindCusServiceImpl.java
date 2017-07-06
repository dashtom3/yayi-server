package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.FindCusDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.FindCusService;
@Service
public class FindCusServiceImpl implements FindCusService {
	@Autowired
	private FindCusDao findDao;
	@Override
	public DataWrapper<List<SaleInfo>> show() {
		DataWrapper<List<SaleInfo>> dataWrapper=new DataWrapper<List<SaleInfo>>();
		dataWrapper.setData(findDao.show());
		return dataWrapper;
	}
}
