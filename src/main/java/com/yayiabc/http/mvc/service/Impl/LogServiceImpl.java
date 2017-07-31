package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.LogDao;
import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
import com.yayiabc.http.mvc.pojo.log.UserLog;
import com.yayiabc.http.mvc.service.LogService;

@Service
public class LogServiceImpl implements LogService{
	
	@Autowired
	private LogDao logDao;

	@Override
	public DataWrapper<List<UserLog>> showUserLogList(String operate,String phone,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<UserLog>> dataWrapper =new DataWrapper<List<UserLog>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		Integer totalNumber=logDao.getUserLogTotalNumber(operate,phone);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber=page.getCurrentNumber();
		List<UserLog> userLogList=logDao.showUserLogList(operate,phone,currentNumber,numberPerPage);
		dataWrapper.setData(userLogList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleLog>> showSaleLogList(String operate,
			String phone, Integer currentPage, Integer numberPerPage) {
		
		DataWrapper<List<SaleLog>> dataWrapper =new DataWrapper<List<SaleLog>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		Integer totalNumber=logDao.getSaleLogTotalNumber(operate,phone);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber=page.getCurrentNumber();
		List<SaleLog> saleLogList=logDao.showSaleLogList(operate,phone,currentNumber,numberPerPage);
		dataWrapper.setData(saleLogList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<AdminstratorLog>> showAdminstratorLogList(String operate,
			String phone, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<AdminstratorLog>> dataWrapper =new DataWrapper<List<AdminstratorLog>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		Integer totalNumber=logDao.getAdminstratorLogTotalNumber(operate,phone);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber=page.getCurrentNumber();
		List<AdminstratorLog> adminstratorLogList=logDao.showAdminstratorLogList(operate,phone,currentNumber,numberPerPage);
		dataWrapper.setData(adminstratorLogList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

}
