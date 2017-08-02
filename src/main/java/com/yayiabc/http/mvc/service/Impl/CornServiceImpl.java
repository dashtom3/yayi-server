package com.yayiabc.http.mvc.service.Impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.http.mvc.dao.CornDao;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.service.CornService;





@Service
public class CornServiceImpl implements CornService{
	
	@Autowired
	private CornDao cornDao;

	
	

	@Override
	public void addBalance() {
		List<String> saleIdList=cornDao.getSaleIdList();
		System.out.println(saleIdList);
		for (String saleId : saleIdList) {
			Double totalHaoCaiMoney=cornDao.getTotalHaoCaiMoneyBySaleId(saleId);
			if(totalHaoCaiMoney==null){
				totalHaoCaiMoney=0.0;
			}
			Double totalGongJuMoney=cornDao.getTotalGongJuMoneyBySaleId(saleId);
			if(totalGongJuMoney==null){
				totalGongJuMoney=0.0;
			}
			Double totalHaoCaiRefund=cornDao.getTotalHaoCaiRefundBySaleId(saleId);
			if(totalHaoCaiRefund==null){
				totalHaoCaiRefund=0.0;
			}
			Double totalGongJuRefund=cornDao.getTotalGongJuRefundBySaleId(saleId);
			if(totalGongJuRefund==null){
				totalGongJuRefund=0.0;
			}
			Double getMoneyByHaoCai=0.0;
			if(totalHaoCaiMoney<1000){
				getMoneyByHaoCai=(totalHaoCaiMoney-totalHaoCaiRefund)*0.05;
			}else if(totalHaoCaiMoney<2000){
				getMoneyByHaoCai=(totalHaoCaiMoney-totalHaoCaiRefund)*0.08;
			}else if(totalHaoCaiMoney<3000){
				getMoneyByHaoCai=(totalHaoCaiMoney-totalHaoCaiRefund)*0.12;
			}else{
				getMoneyByHaoCai=(totalHaoCaiMoney-totalHaoCaiRefund)*0.16;
			}
			Double getMoneyByGongJu=0.0;
			if(totalGongJuMoney<10000){
				getMoneyByGongJu=(totalGongJuMoney-totalGongJuRefund)*0.08;
			}else {
				getMoneyByGongJu=(totalGongJuMoney-totalGongJuRefund)*0.15;
			}
			Double balanceIn=getMoneyByHaoCai+getMoneyByGongJu;
			/*Double balanceIn=this.getMoneyByHaoCai(totalHaoCaiMoney, totalHaoCaiMoney-totalHaoCaiRefund)
					+this.getMoneyByGongJu(totalGongJuMoney,totalGongJuMoney-totalGongJuRefund);*/
			if(balanceIn!=0){
			Double balance=cornDao.getLatestBalanceBySaleId(saleId);
			System.out.println(balance);
			if(balance==null){
				balance=0.0;
			}
			balance+=balanceIn;
			Balance balanceMonthCash=new Balance();
			balanceMonthCash.setSaleId(saleId);
			balanceMonthCash.setHaocaiMoney(totalHaoCaiMoney.intValue()+0.0);
			balanceMonthCash.setGongjuMoney(totalGongJuMoney.intValue()+0.0);
			balanceMonthCash.setHaocaiRefund(totalHaoCaiRefund.intValue()+0.0);
			balanceMonthCash.setGongjuRefund(totalGongJuRefund.intValue()+0.0);
			balanceMonthCash.setBalanceIn(balanceIn.intValue()+0.0);
			balanceMonthCash.setBalance(balance.intValue()+0.0);
			balanceMonthCash.setDescribey("进账每月结算");
			balanceMonthCash.setCreated(new Date());
			System.out.println(balanceMonthCash);
			cornDao.addBalancePerMonth(balanceMonthCash);
			Integer money=balance.intValue();
			/*cornDao.updateSaleInfo(saleId,money);*/
			}
		}
		
		
	}
}
