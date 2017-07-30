package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
import com.yayiabc.http.mvc.pojo.log.UserLog;
@Repository
public interface SystemControllerLogDao {

	void addLog(UserLog userLog);

	String getUserIdByToken(String token);

	String getSaleIdBySaleToken(String saleToken);

	void addSaleLog(SaleLog saleLog);

	String getAdminstratorIdByAdminstratorToken(String adminstratorToken);

	void addAdminstratorLog(AdminstratorLog adminstratorLog);

}
