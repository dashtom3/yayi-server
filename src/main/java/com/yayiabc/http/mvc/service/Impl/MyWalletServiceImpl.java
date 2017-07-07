package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.MyWalletDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleIncome;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.MyWalletService;
@Service
public class MyWalletServiceImpl implements MyWalletService{
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private UtilsDao utilsDao;
	//-------
	//容器
	TreeMap<String, Object> treeMap=new TreeMap<String,Object>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		

	@Override
	public DataWrapper<TreeMap<String, Object>> myWalletDetails(String token,int date
			,int state
			) {
		DataWrapper<TreeMap<String, Object>> dataWrapper=new DataWrapper<TreeMap<String, Object>>();
	     if(state==0){
	    	 houston( token, date);
	    	 withdrawals(token, date);
	     }else if(state==1){
	    	 houston( token, date);
	     }else{
	    	 withdrawals(token, date);
	     }
        //测试
		for(String key:treeMap.keySet()){
			System.out.println(key+" :"+treeMap.get(key));
		}
		dataWrapper.setData(treeMap);
		return dataWrapper;
	}
	//进账
	private void houston(String token,int date){
		List<SaleIncome> list=myWalletDao.saleInCome(token,date);

		System.out.println(list);
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				treeMap.put(sdf.format(list.get(i).getUpdated()),list.get(i));
			}
		}
	}
	//提现
	private void withdrawals(String token,int date){
		
		List<With> lists=myWalletDao.with(token,date);
		System.out.println(lists);
		if(!lists.isEmpty()){
			for(int x=0;x<lists.size();x++){
				treeMap.put(sdf.format(lists.get(x).getCashSuTime()),lists.get(x));
			}
		}
	}
	//查看订单详情
	@Override
	public void queryOrder(String orderId, String sale_token){
		//根据 token获取     一系列获取 用户id
		String saleId=utilsDao.getSaleId(sale_token);
		//根据saleId 查 userId
		String userId=myWalletDao.queryUserID(orderId);
		// TODO Auto-generated method stub
		myWalletDao.queryOrder(orderId,userId);
		
	}
}
