package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
@Service
public class OrderHelpUtilsImpl{
	@Autowired
    private UtilsDao utilsDao;
	//结账时放入到SaleIncome表里的数据 并把 到账到该销售员
	public boolean SetSaleInCome(String orderId){
		 //销售员id
		String saleId=utilsDao.querySaleIdByOrderId(orderId);
		
		//查出此单的  supplies_sumprice    tooldevices_sumprice
		Ordera order=utilsDao.queryCalssSumPrice(orderId);
		//保存到 sale_income 表里
	int sign=utilsDao.insert(saleId, orderId, 0.0,0.0,order.getSupplies_sumprice(), order.getTooldevices_sumprice());
	    //获取该订单的赠送钱币数
	Ordera o=utilsDao.queryGiveQBNumByOrderId(orderId);
	System.out.println(o);
	   //查询该用户钱包余额
	int qbNum=utilsDao.queryUserQbNum(o.getUserId());
	   //把钱币放入该用户的余额中
	 int signs=utilsDao.saveQbToUser(o.getUserId(),String.valueOf(qbNum+o.getGiveQb()));
	if(sign>0&&signs>0){
		return true;
	}
	   return false;
	} 
	
}
