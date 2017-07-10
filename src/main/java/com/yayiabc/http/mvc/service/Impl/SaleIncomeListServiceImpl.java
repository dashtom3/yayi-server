package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.SaleIncomeListDao;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;
import com.yayiabc.http.mvc.service.SaleIncomeListService;

@Service
public class SaleIncomeListServiceImpl implements SaleIncomeListService {

	@Autowired
	SaleIncomeListDao saleIncomeListDao;

	@Override
	public DataWrapper<List<SaleIncomeVo>> query(String saleId,
			String saleName, String salePhone, String orderId,
			Integer signLateSeven, Integer getState, String startDate,
			String endDate, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<SaleIncomeVo>> dataWrapper = new DataWrapper<List<SaleIncomeVo>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = saleIncomeListDao
				.getCount(saleId, saleName, salePhone, orderId, signLateSeven,
						getState, startDate, endDate);
		List<SaleIncomeVo> list = saleIncomeListDao.query(saleId, saleName,
				salePhone, orderId, signLateSeven, getState, startDate,
				endDate, page);
			dataWrapper.setPage(page, totalNumber);
			dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<SaleIncomeVo> detail(String saleId, String userId,
			String orderId) {
		DataWrapper<SaleIncomeVo> dataWrapper = new DataWrapper<SaleIncomeVo>();
		SaleIncomeVo saleIncomeVo = saleIncomeListDao.detail(saleId, userId);
		List<OrderVo> list = saleIncomeListDao.orderList(userId, orderId);
		if(list==null){
			saleIncomeVo.setOrderVoList(null);
		}else{
			saleIncomeVo.setOrderVoList(list);
		}
		dataWrapper.setData(saleIncomeVo);
		return dataWrapper;
	}

}
