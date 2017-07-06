package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.OrderDetailsDao;
import com.yayiabc.http.mvc.dao.ShippingAddressDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsDao orderdetailsDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private ShippingAddressDao shippingAddressDao;
	@Override
	public DataWrapper<List<User>>  orderDetailsShow(HashMap<String,String> map,String token
			,Integer currentPage,Integer numberPerpage){
		// TODO Auto-generated method stub
		Page page=new Page();
		if(currentPage!=null&numberPerpage!=null){
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		
        		
		DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		String userId = null;
		if(token!=null){
			 userId=utilsDao.getUserID(token);
		}
		map.put("phone", userId);
		map.put("currentPage", String.valueOf(page.getCurrentPage()));
		map.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		//总条数
				int count=orderdetailsDao.queryCount(map);
		List<User> orderDetailsList=orderdetailsDao.orderDetailsShow(map);
		map.put("orderItemNum", String.valueOf(orderDetailsList.size()));
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
	public DataWrapper<Void> cancel(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=orderdetailsDao.cancel(orderId);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
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
	public DataWrapper<Void> makeSureCom(HashMap<String, String> hashMap,String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		hashMap.put("userId", userId);
		int state=orderdetailsDao.makeSureCom(hashMap);
		if(state>0){
			dataWrapper.setMsg("操作成功");
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
}



