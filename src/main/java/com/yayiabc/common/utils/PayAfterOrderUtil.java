package com.yayiabc.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserMyQbService;
@Component
public class PayAfterOrderUtil {
	@Autowired
	private AliPayDao aliPayDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private UserMyQbService userMyQbService;
	@Autowired
	private OrderManagementDao orderManagementDao;
	@Autowired
	private PlaceOrderDao placeOrderDao;

	public  boolean universal(String orderId,String type){
		//更改支付类型
		int tt=0;
		if(type!=null){
			tt=aliPayDao.updatePayType(orderId,type);
		}
		//更改订单状态与付款时间
		/*int ax=aliPayDao.updateStateAndPayTime(orderId);*/
		if(tt>0){
			//清楚缓存中的
			CacheUtils cache=	CacheUtils.getInstance();
			Map<String,Date> map=cache.getCacheMap();
			map.remove(orderId);
		}
		Ordera o=aliPayDao.queryOrder(orderId);
		//QbRecord q=new QbRecord();
		if(o.getQbDed()!=0){
			//

			//更新到订单表最后一列
			int a=aliPayDao.saveLast(newQbDed(o.getUserId(),o.getQbDed(),o.getOrderId()),o.getOrderId());
			if(a<=0){
				throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
			}
		}
		//统计销量
		List<OrderItem> orderItemList=orderManagementDao.queryOrderItemList(orderId);
		//双休优化增加销量
		int t=aliPayDao.addSalesList(orderItemList);
		//增加 itemvalue表里面的销量信息

		int c=aliPayDao.addSalesListTOitemValue(orderItemList);
         if(t<=0&&c<=0){
        	 throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
         }
		if(SetSaleInCome(orderId)){
			System.out.println("一切执行完毕");
			return true;
		}
		return false;
	}
	//结账时放入到SaleIncome表里的数据 并把 到账到该销售员
	private boolean SetSaleInCome(String orderId){
		//销售员id
		String saleId=utilsDao.getSaleIdByOrderId(orderId);

		//查出此单的  supplies_sumprice    tooldevices_sumprice
		Ordera order=utilsDao.queryCalssSumPrice(orderId);
		//保存到 sale_income 表里

		int sign=utilsDao.insert(saleId, orderId, 0.0,0.0,order.getSupplies_sumprice(), order.getTooldevices_sumprice());
		//获取该订单的赠送钱币数
		Ordera o=utilsDao.queryGiveQBNumByOrderId(orderId);
		//查询该用户钱包余额
		//int qbNum=utilsDao.queryUserQbNum(o.getUserId());

		Ordera oo=aliPayDao.queryOrder(orderId);
		if(oo.getGiveQb()!=0){
			QbRecord q=new QbRecord();
			//----
			q.setQbRget(oo.getGiveQb());
			q.setQbType("qb_balance");
			q.setRemark("下单获得"+oo.getGiveQb()+"个乾币，（0类型"+oo.getGiveQb()+"个）。（订单编号:"+orderId+"）");
			String token=utilsDao.queryTokenByOrderId(orderId);
			userMyQbService.add(q, token);
		}

		return true;
	} 
	//下单钱币扣除规则 先。。。后。。。。。。。
	String  newQbDed(String userId,Integer  DedNum,String orderId){
		//dednum <= max used qb //钱币够用
		User u=utilsDao.queryUserByUserId(userId);
		
		List<Integer> listData=new ArrayList<Integer>();
		listData.add(u.getQbBalance()); //"qb_balance"
		listData.add( u.getaQb());      //"a_qb"
		listData.add( u.getbQb());         //"b_qb"
		listData.add( u.getcQb());           //"c_qb"
		int a=DedNum;
		String qbDes=null;
		StringBuffer sb=new StringBuffer();
		StringBuilder sb1=new StringBuilder();
		String s=null;
		for(int i=0;i<listData.size();i++){
			if(listData.get(i)>=DedNum&&listData.get(i)!=0){
				
				qbDes=sb1.toString()+DedNum;
				s="下单使用"+a+"个钱币，（"+sb.toString()+i+"类型"+DedNum+"个"+"）。"+"（订单编号："+orderId+"）";
				DedNum=listData.get(i)-DedNum;
				//listData.get(i)=listData.get(i)-DedNum;  这个
				listData.set(i, DedNum);
				break;
			}else if(listData.get(i)<DedNum&&listData.get(i)!=0){
				
				
				sb.append(i+"类型"+listData.get(i)+"个,");
				sb1.append(listData.get(i)+",");
				DedNum=DedNum-listData.get(i);
				System.err.println(DedNum);
				listData.set(i, 0);
			}
		}
		System.out.println(listData);
		Calendar Cld = Calendar.getInstance();
		int MI = Cld.get(Calendar.MILLISECOND);
		
		userMyQbService.updateDataToUser(listData,userId); //更改user 表  各种钱币类型
		userMyQbService.addMessageQbQ(a,userId,s,MI); //新增钱币记录表   
		return qbDes;
	}
	public Object checkSecurity(String userId,String orderId){
		 if(userId!=null){
			//User user= utilsDao.queryUserByUserId(userId);
			Integer qbNums=placeOrderDao.ded(userId);
			return qbNums;
		 }
		 //----------------
		 if(orderId!=null){
			 
		 }
		return null;
	}
	
	//验证重复订单
}
