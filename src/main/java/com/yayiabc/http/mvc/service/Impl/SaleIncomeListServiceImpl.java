package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.IncomUtil;
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
	public DataWrapper<SaleIncomeVo> detail(String saleId, String beYearMonth,String getState,
			 Integer currentPage, Integer numberPerPage) {
		DataWrapper<SaleIncomeVo> dataWrapper = new DataWrapper<SaleIncomeVo>();
		String startDate = "";
		String endDate = "";
		Map<String, String> map = new HashMap<String, String>();		
		SaleIncomeVo saleIncomeVo = new SaleIncomeVo();
		if ("已结算".equals(getState)) {
		saleIncomeVo = saleIncomeListDao.detailDone(saleId, beYearMonth);	//收入已结算的业绩统计信息	
		Integer year=Integer.parseInt(beYearMonth.substring(0, 4));
		Integer month=Integer.parseInt(beYearMonth.substring(5, 7));
		map = getTimeUtil.getYearMonthTime(year, month);
		startDate = map.get("startDate");
		endDate = map.get("endDate");
		saleIncomeVo.setHaocaiGetMoney(IncomUtil.getMoneyByHaoCai(			//耗材类收入
				saleIncomeVo.getSaleDataStatistics().getHaocaiMoney(), 
				saleIncomeVo.getSaleDataStatistics().getHaocaiActual()));
		saleIncomeVo.setGongjuGetMoney(IncomUtil.getMoneyByGongJu(
				saleIncomeVo.getSaleDataStatistics().getGongjuMoney(), 		//工具类收入
				saleIncomeVo.getSaleDataStatistics().getGongjuActual()));
		} else if ("待结算".equals(getState)) {
			saleIncomeVo =saleIncomeListDao.detailNot(saleId,beYearMonth);
			map = getTimeUtil.getTime();
			startDate = map.get("startDate");
			endDate = map.get("endDate");
		}
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleIncomeListDao.getCountOrderList(startDate,endDate, saleId);
		dataWrapper.setPage(page, totalNumber);
		List<OrderVo> list = saleIncomeListDao.orderList(startDate, endDate,saleId, page);
		if (list == null) {
			saleIncomeVo.setOrderVoList(null);
		} else {
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
			 Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleIncomeVo>> dataWrapper = new DataWrapper<List<SaleIncomeVo>>();
		Map<String, String> map =new HashMap<String, String>();
		map =getTimeUtil.getTime();
		String startDate=map.get("startDate");
		String endDate=map.get("endDate");
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleIncomeListDao.getCountNot(startDate,endDate, saleName, salePhone);
		List<SaleIncomeVo> list =saleIncomeListDao.queryNot(startDate, endDate, saleName, salePhone, page);
		dataWrapper.setPage(page, totalNumber);
		dataWrapper.setData(list);
		return dataWrapper;
	}

}
