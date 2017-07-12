package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.SaleMyOrderDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.model.MyOrderVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataStatistics;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;
import com.yayiabc.http.mvc.service.SaleMyOrderService;

@Service
public class SaleMyOrderServiceImpl implements SaleMyOrderService {

	@Autowired
	SaleMyOrderDao saleMyOrderDao;
	@Autowired
	SaleLogDao saleLogDao;
	@Autowired
	UserDao userDao;

	@Override
	public DataWrapper<SaleDataStatistics> myOrder(String token,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<SaleDataStatistics> dataWrapper = new DataWrapper<SaleDataStatistics>();
		SaleDataStatistics saleDataStatistics = new SaleDataStatistics();
		String saleId = saleLogDao.getSaleIdByToken(token);
		if (saleId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			saleDataStatistics = saleMyOrderDao.myOrderData(saleId);
			saleDataStatistics = saleMyOrderDao.myOrderOrder(saleId);
			Page page = new Page();
			page.setNumberPerPage(numberPerPage);
			page.setCurrentPage(currentPage);
			int totalNumber = saleMyOrderDao.getCount(saleId);
			List<MyOrderVo> list = saleMyOrderDao.myOrder(saleId, page);
			dataWrapper.setPage(page, totalNumber);
			saleDataStatistics.setMyOrderVoList(list);
			dataWrapper.setData(saleDataStatistics);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<SaleDataStatistics>> chart(String token,
			String year, String month) {
		DataWrapper<List<SaleDataStatistics>> dataWrapper = new DataWrapper<List<SaleDataStatistics>>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		if (saleId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			List<SaleDataStatistics> list = saleMyOrderDao.chart(saleId, year,
					month);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<SaleIncomeVo> detail(String userPhone, String orderId,
			String token) {
		DataWrapper<SaleIncomeVo> dataWrapper = new DataWrapper<SaleIncomeVo>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		String userId = userDao.getUserId(userPhone);
		if (saleId == null || userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			SaleIncomeVo saleIncomeVo = saleMyOrderDao.detailS(userId, orderId,saleId);
			if (saleIncomeVo == null) {
				dataWrapper.setData(null);
			} else {
				List<OrderVo> list = saleMyOrderDao.detailO(userId, orderId,
						saleId);
				if (list == null) {
					saleIncomeVo.setOrderVoList(null);
				} else {
					saleIncomeVo.setOrderVoList(list);
				}
				dataWrapper.setData(saleIncomeVo);
			}
		}
		return dataWrapper;
	}

}
