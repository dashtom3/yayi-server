package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
@Service
public class OrderHelpUtilsImpl{
	@Autowired
    private UtilsDao utilsDao;
	public void getSaleInCome(String orderId){
		 //销售员id
		String saleId=utilsDao.querySaleIdByOrderId(orderId);
		//销售手机号
		String salePhone=utilsDao.querySalePhoneBySaleId(saleId);
		//查出此单的  supplies_sumprice    tooldevices_sumprice
		Ordera order=utilsDao.queryCalssSumPrice(orderId);
		//保存到 sale_income 表里
		HashMap<String, Object> hm=new HashMap<String,Object>();
		hm.put("Tooldevices_sumprice", order.getSupplies_sumprice());
		hm.put("Tooldevices_sumprice", order.getTooldevices_sumprice());
		int i=1;
		for(String key:hm.keySet()){
			utilsDao.insert(saleId,orderId,String.valueOf(hm.get(key)),"1",String.valueOf(i));
			i++;
		}
		
	}
}
