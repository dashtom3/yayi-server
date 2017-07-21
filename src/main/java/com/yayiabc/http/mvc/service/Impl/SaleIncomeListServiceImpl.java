package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.getTimeUtil;
import com.yayiabc.http.mvc.dao.SaleIncomeListDao;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;
import com.yayiabc.http.mvc.service.SaleIncomeListService;

@Service
public class SaleIncomeListServiceImpl implements SaleIncomeListService {

	@Autowired
	SaleIncomeListDao saleIncomeListDao;

	@Override
	public DataWrapper<SaleIncomeVo> detail(String saleId,String beYearMonth,String getState,Integer currentPage, Integer numberPerPage) {
		DataWrapper<SaleIncomeVo> dataWrapper = new DataWrapper<SaleIncomeVo>();
		SaleIncomeVo saleIncomeVo = saleIncomeListDao.detail(saleId, beYearMonth);
		Map<String, String> map =new HashMap<String, String>();
		String startDate="";
		String endDate="";
		if("已结算".equals(getState)){
			map =getTimeUtil.getLastTime();
			startDate=map.get("startDate");
			endDate=map.get("endDate");
		}else if("未结算".equals(getState)){
			map =getTimeUtil.getTime();
			startDate=map.get("startDate");
			endDate=map.get("endDate");
		}
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleIncomeListDao.getCountOrderList(startDate, endDate, saleId);
		dataWrapper.setPage(page, totalNumber);
		List<OrderVo> list = saleIncomeListDao.orderList(startDate, endDate, saleId, page);
		if(list==null){
			saleIncomeVo.setOrderVoList(null);
		}else{
			saleIncomeVo.setOrderVoList(list);
		}
		dataWrapper.setData(saleIncomeVo);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleIncomeVo>> queryDone(String saleName,
			String salePhone, String beYearMonth, String startDate,String endDate,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleIncomeVo>> dataWrapper = new DataWrapper<List<SaleIncomeVo>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber =saleIncomeListDao.getCountDone(saleName, salePhone, beYearMonth, startDate, endDate);
		List<SaleIncomeVo> list =saleIncomeListDao.queryDone(saleName, salePhone, beYearMonth, startDate, endDate, page);
		dataWrapper.setPage(page, totalNumber);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleIncomeVo>> queryNot(String saleName, String salePhone,
			String beYearMonth, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleIncomeVo>> dataWrapper = new DataWrapper<List<SaleIncomeVo>>();
		Map<String, String> map =new HashMap<String, String>();
		map =getTimeUtil.getTime();
		String startDate=map.get("startDate");
		String endDate=map.get("endDate");
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleIncomeListDao.getCountNot(startDate,endDate, saleName, salePhone, beYearMonth);
		List<SaleIncomeVo> list =saleIncomeListDao.queryNot(startDate, endDate, saleName, salePhone, beYearMonth, page);
		dataWrapper.setPage(page, totalNumber);
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
