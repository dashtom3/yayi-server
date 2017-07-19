package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.MyWalletDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleIncome;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
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
	
	ArrayList al=new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
     
	@Override
	public DataWrapper<TreeMap<String, Object>> myWalletDetails(String token
			,Integer state,String starTime,String endTime
			) {
		
		DataWrapper<TreeMap<String, Object>> dataWrapper=new DataWrapper<TreeMap<String, Object>>();
	     if(state==0){
	    	 houston( token,starTime,endTime);
	    	 withdrawals(token,starTime,endTime);
	     }else if(state==1){
	    	 houston(token,starTime,endTime);
	     }else if(state==2){
	    	 withdrawals(token,starTime,endTime);
	     }
       /* //测试
		for(String key:treeMap.keySet()){
			System.out.println(key+" :"+treeMap.get(key));
		}*/
	     
		dataWrapper.setData(treeMap);
		return dataWrapper;
	}
	//进账
	private void houston(String token,String starTime, String endTime){
		String saleToken=utilsDao.getSaleId(token);
		List<SaleIncome> list=myWalletDao.saleInCome(saleToken,starTime,endTime);
    int houstonJZ=0;
		System.out.println(list);
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				treeMap.put(sdf.format(list.get(i).getUpdated()),list.get(i));
				houstonJZ+=list.get(i).getGetMoney();
			}
			treeMap.put("houstonJZ", houstonJZ);
		}else{
			treeMap.put("houstonJZ", 0);
		}
	}
	//提现
	private void withdrawals(String token,String starTime,String endTime){
		String saleToken=utilsDao.getSaleId(token);
		int withdrawalsTX=0;
		List<With> lists=myWalletDao.with(saleToken,starTime,endTime);
		System.out.println(lists);
		if(!lists.isEmpty()){
			for(int x=0;x<lists.size();x++){
				treeMap.put(sdf.format(lists.get(x).getCashSuTime()),lists.get(x));
				withdrawalsTX+=lists.get(x).getCashMoney();
			}
			treeMap.put("withdrawalsTX", withdrawalsTX);
		}else{
			treeMap.put("withdrawalsTX", 0);
		}
	}
	//查看订单详情
	@Override
	public DataWrapper<SaleInfo> queryOrder(String orderId, String sale_token){
		//根据 token获取     一系列获取 用户id
		DataWrapper<SaleInfo> dataWrapper=new DataWrapper<SaleInfo>();
		//String saleId=utilsDao.getSaleId(sale_token);
		//根据saleId 查 userId
		String userId=myWalletDao.queryUserID(orderId);
		// TODO Auto-generated method stub
		dataWrapper.setData(myWalletDao.queryOrder(orderId,userId));
		return dataWrapper;
	}
	@Override
	public DataWrapper<SaleInfo> queryTMD(String saleToken){
		  String saleId=utilsDao.getSaleId(saleToken);
		 DataWrapper<SaleInfo> dataWrapper=new DataWrapper<SaleInfo>();
		 dataWrapper.setData(myWalletDao.queryTMD(saleId));
		return dataWrapper;
	}
}
