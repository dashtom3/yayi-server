package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.http.mvc.dao.LogisticsDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.LogisticsService;
@Service
public class LogisticsServiceImpl implements LogisticsService{
  @Autowired 
  private LogisticsDao logisticsDao; 
  @Autowired 
  private UserDao userDao; 
	@Override
	public HashMap<String,Ordera> queryLog(String phone, String itemId) {
		// TODO Auto-generated method stub
		String userId=userDao.getUserId(phone);
		return  logisticsDao.queryLog(userId, itemId);
	}
 
}
