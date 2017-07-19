package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.SystemControllerLogDao;
import com.yayiabc.http.mvc.pojo.log.UserLog;
import com.yayiabc.http.mvc.service.SystemControllerLogService;
@Service
public class SystemControllerLogServiceImpl implements SystemControllerLogService{
	
	@Autowired
	private SystemControllerLogDao systemControllerLogDao;
	@Override
	public void addLog(UserLog userLog) {
		systemControllerLogDao.addLog(userLog);
	}

}
