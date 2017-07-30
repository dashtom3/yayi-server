package com.yayiabc.http.mvc.service;

import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
import com.yayiabc.http.mvc.pojo.log.UserLog;

public interface SystemControllerLogService {

	void addLog(UserLog userLog);

	String getUserIdByToken(String token);

	String getSaleIdBySaleToken(String saleToken);

	void addSaleLog(SaleLog saleLog);

	String getAdminstratorIdByAdminstratorToken(String adminstratorToken);

	void addAdminstratorLog(AdminstratorLog adminstratorLog);

}
