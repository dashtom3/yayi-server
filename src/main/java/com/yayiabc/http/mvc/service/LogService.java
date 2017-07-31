package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
import com.yayiabc.http.mvc.pojo.log.UserLog;

public interface LogService {

	DataWrapper<List<UserLog>> showUserLogList(String operate,String phone,Integer currentPage,Integer numberPerPage);

	DataWrapper<List<SaleLog>> showSaleLogList(String operate, String phone,
			Integer currentPage, Integer numberPerPage);

	DataWrapper<List<AdminstratorLog>> showAdminstratorLogList(String operate,
			String phone, Integer currentPage, Integer numberPerPage);

}
