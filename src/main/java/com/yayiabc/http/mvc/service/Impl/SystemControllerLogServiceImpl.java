package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.SystemControllerLogDao;
import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
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
	
	@Override
	public String getUserIdByToken(String token) {
		
		return systemControllerLogDao.getUserIdByToken(token);
	}

	@Override
	public String getSaleIdBySaleToken(String saleToken) {
		return systemControllerLogDao.getSaleIdBySaleToken(saleToken);
	}

	@Override
	public void addSaleLog(SaleLog saleLog) {
		systemControllerLogDao.addSaleLog(saleLog);
		
	}

	@Override
	public String getAdminstratorIdByAdminstratorToken(String adminstratorToken) {
		
		return systemControllerLogDao.getAdminstratorIdByAdminstratorToken(adminstratorToken);
	}

	@Override
	public void addAdminstratorLog(AdminstratorLog adminstratorLog) {
		systemControllerLogDao.addAdminstratorLog(adminstratorLog);
		
	}

}
