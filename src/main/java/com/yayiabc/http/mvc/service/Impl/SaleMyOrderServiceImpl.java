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
import com.yayiabc.http.mvc.pojo.model.OrderInfoVo;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import com.yayiabc.http.mvc.pojo.model.SaleDataVo;
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
	public DataWrapper<List<SaleDataVo>> chart(String token,
			String year, String month) {
		DataWrapper<List<SaleDataVo>> dataWrapper = new DataWrapper<List<SaleDataVo>>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		if (saleId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			List<SaleDataVo> list = saleMyOrderDao.chart(saleId, year,
					month);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}


	@Override
	public DataWrapper<SaleDataVo> myOrderData(String token,String year, String month) {
		DataWrapper<SaleDataVo> dataWrapper=new DataWrapper<SaleDataVo>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		SaleDataVo saleDataVo=new SaleDataVo();
		String allcommission=saleMyOrderDao.allCommission(saleId);
		saleDataVo=saleMyOrderDao.queryData(saleId, year, month);
		if(allcommission==null){
			saleDataVo.setAllCommission(0);
		}else{
			saleDataVo.setAllCommission(Double.parseDouble(saleMyOrderDao.allCommission(saleId)));
		}
		dataWrapper.setData(saleDataVo);
		return dataWrapper;
	}


	@Override
	public DataWrapper<List<MyOrderVo>> myOrderList(String token,String year,String month,Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<MyOrderVo>> dataWrapper=new DataWrapper<List<MyOrderVo>>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleMyOrderDao.getCountOrderList(saleId, year, month);
		dataWrapper.setPage(page, totalNumber);
		List<MyOrderVo> list=saleMyOrderDao.queryOrderList(saleId, page, year, month);
		dataWrapper.setData(list);
		return dataWrapper;
	}


	@Override
	public DataWrapper<OrderVo> detail(String token, String orderId,Integer currentPage, Integer numberPerPage) {
		DataWrapper<OrderVo> dataWrapper=new DataWrapper<OrderVo>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=saleMyOrderDao.getCountDetailOrderList(saleId, orderId);
		List<OrderInfoVo> list=saleMyOrderDao.detailOrderList(saleId, orderId, page);
		dataWrapper.setPage(page, totalNumber);
		OrderVo orderVo=new OrderVo();
		orderVo=saleMyOrderDao.detail(saleId, orderId);
		if(list==null){
			orderVo.setOrderInfoVoList(null);
		}else {
			orderVo.setOrderInfoVoList(list);
		}		
		dataWrapper.setData(orderVo);
		return dataWrapper;
	}



}
