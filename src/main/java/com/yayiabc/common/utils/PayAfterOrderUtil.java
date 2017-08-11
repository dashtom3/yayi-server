package com.yayiabc.common.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
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
	
	public  void universal(String orderId,String type){
		//更改支付类型
		if(type!=null){
			int t=aliPayDao.updatePayType(orderId,type);
		}
		//更改订单状态与付款时间
		int ax=aliPayDao.updateStateAndPayTime(orderId);
		if(ax>0){
			System.out.println("更改成功");
		}
		Ordera o=aliPayDao.queryOrder(orderId);
		QbRecord q=new QbRecord();
		if(o.getQbDed()!=0){
		q.setQbRout(-o.getQbDed());
		//下单获得37个乾币。（订单编号：xxxxx）
		q.setRemark("下单使用"+o.getQbDed()+"个乾币。（订单编号:"+orderId+"）");
		String token=utilsDao.queryTokenByOrderId(orderId);
		userMyQbService.add(q, token);
		}
		/*//该用户的钱币余额进行更改
		int a=aliPayDao.updateQb(o.getQbDed(),o.getUserId());*/
		//统计销量
		List<OrderItem> orderItemList=orderManagementDao.queryOrderItemList(orderId);
		for(int i=0;i<orderItemList.size();i++){
			//增加销量
			try {
				aliPayDao.addSales(orderItemList.get(i).getItemId(),
						orderItemList.get(i).getNum()
						);
			} catch (Exception e) {
			   throw new RuntimeException(e);
			}
		}
		if(SetSaleInCome(orderId)){
			System.out.println("一切执行完毕");
		}
	}
	//private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
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
		q.setRemark("下单获得"+oo.getGiveQb()+"个乾币。（订单编号:"+orderId+"）");
		String token=utilsDao.queryTokenByOrderId(orderId);
		userMyQbService.add(q, token);
		}
		   //把钱币放入该用户的余额中
		/*int signs=utilsDao.saveQbToUser(o.getUserId(),String.valueOf(qbNum+o.getGiveQb()));*/
	  return true;
		} 
		
}
