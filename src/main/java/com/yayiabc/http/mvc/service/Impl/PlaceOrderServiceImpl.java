package com.yayiabc.http.mvc.service.Impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.OrderIdUtils;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.service.PlaceOrderService;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{
	@Autowired
	private PlaceOrderDao placeOrderDao;
	@Autowired
	private UtilsDao utilsDao;
	@Override
	
	public DataWrapper<HashMap<String, Object>> buyNows(String token) {
		// TODO Auto-generated method stub
		String userId=utilsDao.getUserID(token);
		
		  //生成 orderId 
		 String orderId=OrderIdUtils.createOrderId(userId);
		//给order表 创建一个空order
			placeOrderDao.createOrder(orderId,userId);
		//容器
		HashMap<String, Object> hMap=new HashMap<String, Object>();
		int sumPrice=0;//商品总价
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String, Object>>();
		
		List<Cart> cartList=placeOrderDao.buyNows(userId);
		//下单赠送钱币数：
		int giveQbNum=0;
		int itemSum=cartList.size();//商品总数量
		for(int i=0;i<cartList.size();i++){
		 sumPrice+=cartList.get(i).getNum()*cartList.get(i).getPrice();
		  //将购物车的商品 同步到 订单商品表里
		 placeOrderDao.synchronization(cartList.get(i),orderId);
			//模拟
			giveQbNum+=0;
		}
		//运费计算
		

		//int yunfei=getFreight(receiver, sumPrice, itemSum);//运费
		hMap.put("sumPrice", sumPrice);
		hMap.put("itemSum", itemSum);
		//hMap.put("yunfei", yunfei);
		hMap.put("giveQbNum", giveQbNum);
		//hMap.put("ConsigneeName",receiver.getReceiverName());
		//hMap.put("ConsigneePhone",receiver.getPhone());
		//hMap.put("receiver",receiver);
		hMap.put("orderId",orderId);
		
		hMap.put("cartList",cartList);
		dataWrapper.setData(hMap);
		//System.out.println(cartList);
		return dataWrapper;
	}
    
	
	public int getFreight(Receiver receiver,int sumPrice,int itemSum){
		String Province =receiver.getProvince();
		//查询包邮表数据
		List<FreeShipping> list=placeOrderDao.queryPostFree();
		if(!list.isEmpty())
		{
			System.out.println(receiver);
			System.out.println(sumPrice);
			System.out.println(itemSum);

			for(int i=0;i<list.size();i++){
				
				String[] citys=list.get(i).getPostCity().split(",");
          System.out.println("检查是否是否包邮:"+Arrays.toString(citys));
				int money=list.get(i).getFreeShippingMoney();
				if(Province==null){
					String city=receiver.getCity();
					for(int x=0;x<citys.length;x++){
						if(city.equals(citys[x])){
							if(sumPrice>money||sumPrice==money){
								if(list.get(i).getState()==1){
									return 0;
								}
							}
						}
					}
				}else{
					for(int y=0;y<citys.length;y++){
						if(Province.equals(citys[y])){
							if(sumPrice>money||sumPrice==money){
								if(list.get(i).getState()==1){
									return 0;
								}
							}
						}
					}
				}
			}
		}
	    
		//查询自定义运费表数据
		List<PostFee> lists=placeOrderDao.query();
		if(!lists.isEmpty()){
			for(int i=0;i<lists.size();i++){
				String[] citys=lists.get(i).getPostCity().split(",");
				System.out.println("自定义邮费:城市"+Arrays.toString(citys));
				if(Province==null){
					String cityString=receiver.getCity();
					for(int x=0;x<citys.length;x++){
						if(cityString.equals(citys[x])){
							return lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney();
						}
					}
				}else{
					for(int x=0;x<citys.length;x++){
						if(Province.equals(citys[x])){
							return lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney();
						}
					}
				}
			}
		}
		return 0;
	}
	//钱币抵扣
	@Override
	public DataWrapper<Void> ded(String token, int num) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		// TODO Auto-generated method stub
		String userId=utilsDao.getUserID(token);
		//查询当前用户的钱币剩余
		int su= placeOrderDao.ded(userId);
		if(su>num){
			dataWrapper.setMsg("余额充足");
		}else{
			dataWrapper.setMsg("余额不足");
		}
		return dataWrapper;
	}
	//更改收货地址
	public DataWrapper<HashMap<String, Object>>  upateAddress(Integer receiverId,Integer sumPrice,Integer itemSum){
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String, Object>>();
		
		Receiver receiver=placeOrderDao.queryReceiver(receiverId);
		
		int yunfei=getFreight(receiver, sumPrice, itemSum);
		 System.out.println(yunfei);
		HashMap<String, Object> hashMap=new HashMap<String,Object>();
		hashMap.put("postFee", yunfei);
		hashMap.put("Receiver", receiver);
		dataWrapper.setData(hashMap);
		return  dataWrapper;
	}

	
	
	//提交订单
	public DataWrapper<Void>  placeOrder(){
		return null;
	}

	
	//单个商品的购买
	@Override
	public DataWrapper<HashMap<String, Object>> buyNow(OrderItem orderItem,
			String token) {
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String,Object>>();
		// 192.168.1.103
		String userId=utilsDao.getUserID(token);
		
		String orderId=OrderIdUtils.createOrderId(userId);
		//给order表 创建一个空order
		placeOrderDao.createOrder(orderId,userId);
		
		orderItem.setOrderId(orderId);
	    //把商品更新到   订单商品表
		//  测试加上的 
		//orderItem.setItemId("5");
		placeOrderDao.synchronizationOne(orderItem);
		//购买商品数
		int itemNum=orderItem.getNum();
		//商品单价
		int itemPrice=orderItem.getPrice();
		HashMap<String, Object> hmHashMap=new HashMap<String, Object>();
		//Receiver receiver=placeOrderDao.queryReceiver(receiverId);
		
		int priceNum=orderItem.getNum()*orderItem.getPrice();
		//int yunfei=getFreight(receiver,priceNum,orderItem.getNum());
		
		hmHashMap.put("sumPrice", itemPrice*itemNum);
		hmHashMap.put("OrderItem", orderItem);
		//hmHashMap.put("yunfei", yunfei);
		hmHashMap.put("ItemNum", orderItem.getNum());
		//hmHashMap.put("receiver", receiver);
		dataWrapper.setData(hmHashMap);
		return dataWrapper;
	}
  //保存到订单表
	@Override
	public DataWrapper<Void> saveMessage(Ordera order,String token) {
	  order.setUserId(utilsDao.getUserID(token));
	  System.out.println(order);
	  System.out.println(token);
	  System.out.println(utilsDao.getUserID(token));
	  DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=placeOrderDao.saveMessage(order);
		if(state>0){
			dataWrapper.setMsg("cg购物车清空了");
			emptyCart(token);
			
		}else{
			dataWrapper.setMsg("sb购物车没清空哦");
		}
		return dataWrapper;
		//return util(state);
	}

	//清空购物车
	@Override
	public DataWrapper<Void> emptyCart(String token) {
		// TODO Auto-generated method stub
		int state=placeOrderDao.emptyCart(utilsDao.getUserID(token));
		return util(state);
	}
	private DataWrapper<Void> util(int state){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}

}
