package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.FindCusDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.FindCusService;
@Service
public class FindCusServiceImpl implements FindCusService {
	@Autowired
	private FindCusDao findDao;
	@Autowired
	private UtilsDao utilsDao;
	@Override
	public DataWrapper<List<SaleInfo>> show(HashMap<String, String> hashMap) {
		DataWrapper<List<SaleInfo>> dataWrapper=new DataWrapper<List<SaleInfo>>();
		dataWrapper.setData(findDao.show(hashMap));
		return dataWrapper;
	}
	@Override
	public DataWrapper<List<User>> showMyCus(String saleToken) {
		// TODO Auto-generated method stub
	    DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		String saleId=utilsDao.getSaleId(saleToken);
		List<User> list=findDao.showMyCus(saleId);
		dataWrapper.setData(list);
		return dataWrapper;
	}
}
