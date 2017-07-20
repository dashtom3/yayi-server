package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.MyWalletDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.pojo.jpa.SaleIncome;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.SaleMyWalletDetail;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.MyWalletService;
@Service
@RequestMapping("api/a")
public class MyWalletServiceImpl implements MyWalletService{
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private UtilsDao utilsDao;
	/*//-------
	//容器
	 TreeMap<String, Object> treeMap=new TreeMap<String,Object>();
	
	ArrayList al=new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
     
	
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
	@Override
	public DataWrapper<Void> getBalance(String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String saleId=myWalletDao.getSaleIdByToken(token);
		Double balance =myWalletDao.getBalanceBySaleId(saleId);
		dataWrapper.setMsg(balance.toString());
		return dataWrapper;
	}
	@Override
	public DataWrapper<Void> getAllIn(String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String saleId=myWalletDao.getSaleIdByToken(token);
		Double allIn =myWalletDao.getAllIn(saleId);
		dataWrapper.setMsg(allIn.toString());
		return dataWrapper;
	}
	@Override
	public DataWrapper<Void> getAllOut(String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String saleId=myWalletDao.getSaleIdByToken(token);
		Double allOut =myWalletDao.getAllOut(saleId);
		dataWrapper.setMsg(allOut.toString());
		return dataWrapper;
	}
	@Override
	public DataWrapper<List<Balance>> myWalletDetails(String token,
			Integer state, String starTime, String endTime) {
		DataWrapper<List<Balance>> dataWrapper =new DataWrapper<List<Balance>>();
		String saleId=myWalletDao.getSaleIdByToken(token);
		List<Balance> balanceList=myWalletDao.myWalletDetails(saleId,state,starTime,endTime);
		dataWrapper.setData(balanceList);
		return dataWrapper;
	}
	@Override
	public DataWrapper<List<SaleMyWalletDetail>> viewDetail(Integer balanceId) {
		DataWrapper<List<SaleMyWalletDetail>> dataWrapper=new DataWrapper<List<SaleMyWalletDetail>>();
		Integer countNum=myWalletDao.getCount(balanceId);
		List<SaleMyWalletDetail> saleMyWalletDetails=null;
		if(countNum==0){
			//耗材类
			SaleMyWalletDetail saleMyWalletDetail =new SaleMyWalletDetail();
			saleMyWalletDetail.setItemClassify("耗材类");
			//查询哪一个月的时间
			Date date=myWalletDao.getTime(balanceId);
			Calendar calendar =Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			Date startDate=calendar.getTime();
			
			//工具设备类
			SaleMyWalletDetail saleMyWalletDetailTwo =new SaleMyWalletDetail();
			saleMyWalletDetail.setItemClassify("工具设备类");
			//合计
			SaleMyWalletDetail saleMyWalletDetailThree =new SaleMyWalletDetail();
			saleMyWalletDetail.setItemClassify("合计");
			myWalletDao.getViewDetailByIn(balanceId);//收入
			
		}else{
			Balance balance=myWalletDao.getViewDetailByOut(balanceId);//支出
			SaleMyWalletDetail saleMyWalletDetail =new SaleMyWalletDetail();
			saleMyWalletDetail.setChangeTime(balance.getCreated());
			saleMyWalletDetail.setDescribe(balance.getDescribe());
			saleMyWalletDetail.setMoney(balance.getBalanceOut());
			saleMyWalletDetails.add(saleMyWalletDetail);
		}
		dataWrapper.setData(saleMyWalletDetails);
		return dataWrapper;
	}*/
	@Override
	@RequestMapping("detail")
	@ResponseBody
	public DataWrapper<List<Balance>> detail(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		DataWrapper<List<Balance>> dataWrapper=new DataWrapper<List<Balance>>();
		String saleId=utilsDao.getSaleId(hm.get("token"));
		hm.put("saleId", saleId);
		List<Balance> blist= myWalletDao.detail(hm);
	  if(!blist.isEmpty()){
			System.out.println(blist);
			Double jzze=0.0;
			Double czze=0.0;
			for(int i=0;i<blist.size();i++){
				jzze+=blist.get(i).getBalanceIn();
				czze+=blist.get(i).getBalanceOut();
				if(i==blist.size()-1){
					blist.get(0).setJZZE(jzze);
					blist.get(0).setCZZE(czze);
				}
			}
	  }
		dataWrapper.setData(blist);
		return dataWrapper;
	}
}
