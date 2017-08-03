package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.IncomUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.MyWalletDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.service.MyWalletService;
@Service
@RequestMapping("api/a")
public class MyWalletServiceImpl implements MyWalletService{
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private UtilsDao utilsDao;
	
	public DataWrapper<List<Balance>> detail(HashMap<String, Object> hm,
			Integer currentPage,
			Integer numberPerpage,
			String token
			) {
		// TODO Auto-generated method stub
		Page page=new Page();

		if(currentPage!=null&numberPerpage!=null){
			page.setNumberPerPage(numberPerpage);
			page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		String saleId=utilsDao.getSaleId(token);
		System.out.println(saleId);
		hm.put("saleId", saleId);
		//总条数
				int count=myWalletDao.queryCount(hm);
				
				hm.put("numberPerpage", page.getNumberPerPage());
				Integer currentNum=page.getCurrentNumber();
				hm.put("currentNum",currentNum);
				
		DataWrapper<List<Balance>> dataWrapper=new DataWrapper<List<Balance>>();
		  
		List<Balance> blist= myWalletDao.detail(hm);
		for(int i=0;i<blist.size();i++){
			if(blist.get(i).getDescribey().contains("中")){
				blist.remove(i);
				i--;
			}
		}

	  if(!blist.isEmpty()){
			System.out.println(blist);
			Double jzze=0.0;
			Double czze=0.0;
		    //计算出该销售员的收入支出总额
			
			List<Balance> totalList=myWalletDao.getTotal(hm);
			 for(String key :hm.keySet()){
				 System.out.println(key+"  " +hm.get(key));
			 }
			 System.err.println("totalList   "+totalList);
			for(int x=0;x<totalList.size();x++){
				if(!totalList.get(x).getDescribey().contains("中")){
					if(totalList.get(x).getBalanceIn()!=null){
						jzze+=totalList.get(x).getBalanceIn();
					}
					if(totalList.get(x).getBalanceOut()!=null){
						czze+=totalList.get(x).getBalanceOut();
					}
				}
			}
			blist.get(0).setJZZE(jzze);
			blist.get(0).setCZZE(czze);
	  }
	    dataWrapper.setPage(page, count);
		dataWrapper.setData(blist);
		return dataWrapper;
	}
	//详情
	@Override
	public DataWrapper<Balance> details(String balanceId) {
		// TODO Auto-generated method stub
		DataWrapper<Balance> dataWrapper=new DataWrapper<Balance>();
		Balance balance=myWalletDao.details(balanceId);
		System.err.println(balance);
		Double haoCaiIncome=0.0;
		Double gongJuIncome=0.0;
		if(balance!=null){
			if(balance.getHaocaiMoney()!=null&&balance.getHaocaiRefund()!=null){
				 haoCaiIncome= IncomUtil.getMoneyByHaoCai(balance.getHaocaiMoney(),balance.getHaocaiMoney()-balance.getHaocaiRefund());
				 haoCaiIncome=(double)Math.round(haoCaiIncome*100)/100;
			}
			 gongJuIncome=0.0;
			if(balance.balance()!=null&&balance.getGongjuRefund()!=null){
				 gongJuIncome=IncomUtil.getMoneyByGongJu(balance.balance(), balance.balance()-balance.getGongjuRefund());
				 gongJuIncome=(double)Math.round(gongJuIncome*100)/100;
			}
			balance.setHaoCaiIncome(haoCaiIncome);
			balance.setGongJuIncome(gongJuIncome);
			dataWrapper.setData(balance);
			return dataWrapper;
		}
		else
			dataWrapper.setMsg("该销售员不存在的");
		return dataWrapper;
	}
	@Override
	public DataWrapper<List<Balance>> detailsss(HashMap<String, Object> hm,
			Integer currentPage, Integer numberPerpage
			) {
		// TODO Auto-generated method stub
		Page page=new Page();

		if(currentPage!=null&numberPerpage!=null){
			page.setNumberPerPage(numberPerpage);
			page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		//总条数
				int count=myWalletDao.queryCount(hm);
				
				hm.put("numberPerpage", page.getNumberPerPage());
				Integer currentNum=page.getCurrentNumber();
				hm.put("currentNum",currentNum);
				
		DataWrapper<List<Balance>> dataWrapper=new DataWrapper<List<Balance>>();
		List<Balance> blist= myWalletDao.detail(hm);
		for(int i=0;i<blist.size();i++){
			if(blist.get(i).getDescribey().contains("中")){
				blist.remove(i);
				i--;
			}
		}
		dataWrapper.setData(blist);
		dataWrapper.setPage(page, count);
		return  dataWrapper;
	}
}
