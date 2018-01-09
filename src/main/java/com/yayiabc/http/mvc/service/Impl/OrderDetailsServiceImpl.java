package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.OrderDetailsDao;
import com.yayiabc.http.mvc.dao.ShippingAddressDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.model.OrderNums;
import com.yayiabc.http.mvc.pojo.model.itemIdList;
import com.yayiabc.http.mvc.service.OrderDetailsService;
import com.yayiabc.http.mvc.service.TimerChangeStateService;

import redis.clients.jedis.Jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsDao orderdetailsDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private ShippingAddressDao shippingAddressDao;
	@Autowired
	private AliPayDao aliPayDao;
	@Override
	public DataWrapper<List<Ordera>>  orderDetailsShow(HashMap<String,String> map,String token
			,Integer currentPage,Integer numberPerpage){
		String userId=utilsDao.getUserID(token);
		// TODO Auto-generated method stub
		/*RedisClient redis=RedisClient.getInstance();
		Jedis jedis=redis.jedis;
		jedis.set(orderId,orderId);
        jedis.expire(orderId,120);*/
		
		Page page=new Page();
		
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		
		DataWrapper<List<Ordera>> dataWrapper=new DataWrapper<List<Ordera>>();
		
//		hMap.put("currentPage", page.getCurrentPage());
		map.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		map.put("currentNum", String.valueOf(currentNum));
		map.put("userId", userId);
		List<Ordera> orderDetailsList=orderdetailsDao.orderDetailsShow(map);
		/*orderDetailsList.get(0).setOrderNums(String.valueOf(orderDetailsList));*/
		//总条数
	    int count=orderdetailsDao.queryCount(userId,map.get("state"));//totalnumber
		dataWrapper.setPage(page, count);
		dataWrapper.setData(orderDetailsList);
		if(orderDetailsList.isEmpty()){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("暂订单为空");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			return dataWrapper;
		}
	}
	
	//取消订单
	@Override
	public DataWrapper<Void> cancel(String orderId,String token){
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//判断该用户有没有这个订单
		String userId=utilsDao.getUserID(token);
		//判断这个订单的状态   如果是已付款则无法取消订单
		int sign=aliPayDao.querySatetIsTwo(orderId);
		if(sign==1){
		//还原库存
		TimerChangeStateService timerChangeStateService=BeanUtil.getBean("TimerChangeStateServiceImpl");
		List<String> l=new ArrayList<String>();
		l.add(orderId);
		List<OrderItem> OrderItemNums=timerChangeStateService.queryOrderItemNums(l);
		int q=timerChangeStateService.stillItemsListValueNum(OrderItemNums);
		
		
		
		//钱币的退回
		PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		payAfterOrderUtil.returnQbMed(orderId);
		if(q>0){
			int state=orderdetailsDao.cancel(orderId,userId);
			if(state>0){
				dataWrapper.setMsg("操作成功");
			}else{
				 throw  new OrderException(ErrorCodeEnum.CAN_CEL);
			}
		}else{
			 throw  new OrderException(ErrorCodeEnum.CAN_CEL);
		}
		}else{
			dataWrapper.setMsg("该订单已经付款，无法取消订单");	
		}
 		return dataWrapper;
	}
	
	
	
	
//确定收货
	@Override
	public DataWrapper<Void> confirmReceipt(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=orderdetailsDao.confirmReceipt(orderId);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
 		return dataWrapper;
	}
   //show comment data
	@Override
	public DataWrapper<Ordera> showComItem(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<Ordera> dataWrapper=new DataWrapper<Ordera>();
		dataWrapper.setData(orderdetailsDao.showComItem(orderId));
		
		return dataWrapper;
	}

	@Override   //这里
	public DataWrapper<Void> makeSureCom(String token,String orderId,ArrayList<itemIdList> itemIdListy) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
	   //根据userId  cha  phoen
		String userPhone=utilsDao.queryPhone(userId);
		String userName=utilsDao.queryNameByUserId(userId);
		//user_id,item_id,order_id,comment_grade,comment_content
	
		int count =0;
		for(int i=0;i<itemIdListy.size();i++){
		    OrderItem ItemShuXing=utilsDao.queryIt(itemIdListy.get(i).getItemSKU(),orderId);
		    StringBuffer sb=new StringBuffer(ItemShuXing.getItemName());
		      if(ItemShuXing.getItemPropertyNamea()!=null){
                      sb.append(ItemShuXing.getItemPropertyNamea());
		      }
		      if(ItemShuXing.getItemPropertyNameb()!=null){
                  sb.append(ItemShuXing.getItemPropertyNameb());
	      }
		      if(ItemShuXing.getItemPropertyNamec()!=null){
                  sb.append(ItemShuXing.getItemPropertyNamec());
	      }
			orderdetailsDao.makeSureCom(userId,itemIdListy.get(i).getItemId(),orderId,itemIdListy.get(i).getScore(),
					itemIdListy.get(i).getData(),itemIdListy.get(i).getItemSKU(),sb.toString()
					);
			count++;
		}
		
		if(count>0){
			//9
			int state=orderdetailsDao.updateState(orderId);
			if(state>0){
				dataWrapper.setMsg("操作成功");
			}
		}else{
			dataWrapper.setMsg("操作失败");
		}
 		return dataWrapper;
	}
   //结账
	@Override
	public void payment(Integer orderId, Integer receiverId) {
		// TODO Auto-generated method stub
		Ordera ordera=  orderdetailsDao.queryItemDetails(orderId);
	 List<OrderItem> list=ordera.getOrderitemList();
	 int sumMoney=0;
	 if(list.size()>1){
		 for(int i=0;i<list.size();i++){
			 sumMoney+= list.get(i).getNum()*list.get(i).getPrice();
		 }
		Receiver receiver =shippingAddressDao.queryShoppingAddress(String.valueOf(receiverId));
		//该订单商品数
		int  itemSum =list.size();
		
	 }else{
		 
	 }
	}

	@Override
	public DataWrapper<List<OrderNums>> queryOrderNums(String token) {
		// TODO Auto-generated method stub
		DataWrapper<List<OrderNums>>  dataWrapper=new DataWrapper<List<OrderNums>>();
	  String userId=utilsDao.queryUserByToken(token);
	  List<OrderNums> l=orderdetailsDao.queryOrderNums(userId);
	  int sumCount=0;
	  for(int i=0;i<l.size();i++){
		  sumCount+=l.get(i).getCounts();
	  }
	   dataWrapper.setData(l);
	   dataWrapper.setMsg(String.valueOf(sumCount));
		return dataWrapper;
	}
}



