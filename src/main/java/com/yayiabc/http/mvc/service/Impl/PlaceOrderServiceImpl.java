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


	//本单可获得钱币数

	@Override
	public DataWrapper<HashMap<String, Object>> buyNows(String token,String[] itemSKUs
			,Ordera order
			) {
		//  get userId
		String userId=utilsDao.getUserID(token);

		//obtain orderId 
		String orderId=OrderIdUtils.createOrderId(userId);
		//将订单信息保存在订单里 你如是否需要发表  留言等。。。 
		/**
		 * state,order_id,user_id,qb_ded,
		 * invoice_hand, is_register, buyer_message, total_fee,
		 * actual_pay，post_fee,give_qb,created,
		 * buyer_nick,buyer_rate
		 */
		if(order.getQbDed()==null|order.getQbDed()==0){
			order.setQbDed(0);
		}if(order.getInvoiceHand()==null){
			order.setInvoiceHand("没买价没有输入发票抬头");
		}if(order.getIsRegister()==null){
			order.setIsRegister(0);
		}if(order.getBuyerMessage()==null){
			order.setBuyerMessage("该单暂时未被评价呢");
			order.setBuyerRate(0);
		}
		placeOrderDao.createOrder(orderId,userId,order);
		//容器container
		HashMap<String, Object> hMap=new HashMap<String, Object>();

		int sumPrice=0;//商品总价
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String, Object>>();
		// obtain sure shoppingCart item
		List<Cart> cartList=placeOrderDao.buyNows(userId,itemSKUs);
		System.out.println(cartList);
		//下单赠送钱币数：
		int giveQbNum=0;
		int itemSum=cartList.size();//商品总数量
		for(int i=0;i<cartList.size();i++){
			//订单商品总价
			sumPrice+=cartList.get(i).getNum()*cartList.get(i).getPrice();

			//query钱币赠送百分比
			Integer qbPercentage=placeOrderDao.queryQbPercentage(cartList.get(i).getItemSKU());
			giveQbNum+=cartList.get(i).getNum()*cartList.get(i).getPrice()*qbPercentage;
			//将购物车的商品 同步到 订单商品表里
			//placeOrderDao.synchronization(cartList.get(i),orderId);

			//giveQbNum+=0;
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
			String token,Ordera order) {
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String,Object>>();
		// 192.168.1.103
		String userId=utilsDao.getUserID(token);
		//  考虑一下  用户没有登陆直接下订单的情况
		String orderId=OrderIdUtils.createOrderId(userId);
		/**
		 * state,order_id,user_id,qb_ded,
		 * invoice_hand, is_register, buyer_message, total_fee,
		 * actual_pay，post_fee,give_qb,created,
		 * buyer_nick,buyer_rate
		 */
		if(order.getQbDed()==null|order.getQbDed()==0){
			order.setQbDed(0);
		}if(order.getInvoiceHand()==null){
			order.setInvoiceHand("没买价没有输入发票抬头");
		}if(order.getIsRegister()==null){
			order.setIsRegister(0);
		}if(order.getBuyerMessage()==null){
			order.setBuyerMessage("该单暂时未被评价呢");
			order.setBuyerRate(0);
		}
		//create Order and 填写 数据(orderid userid 是否需要残品注册证，留言等等 )
		placeOrderDao.createOrder(orderId,userId,order);

		orderItem.setOrderId(orderId);
		//把商品更新到   订单商品表
		//  测试加上的 
		//orderItem.setItemId("5");
		int state=placeOrderDao.synchronizationOne(orderItem);
		if(state>0){
			emptyCart(token);
			System.out.println("订单已生成，购物车已经清空");
		}
		//购买商品数
		int itemNum=orderItem.getNum();
		//商品单价
		int itemPrice=orderItem.getPrice()*orderItem.getNum();
		//赠送钱币数
		int giveQbNum=orderItem.getPrice()*orderItem.getNum()*placeOrderDao.queryQbPercentage(orderItem.getItemSKU());
		HashMap<String, Object> hmHashMap=new HashMap<String, Object>();
		hmHashMap.put("giveQbNum", giveQbNum);
		hmHashMap.put("sumPrice", itemPrice);
		hmHashMap.put("OrderItem", orderItem);
		//hmHashMap.put("yunfei", yunfei);
		hmHashMap.put("ItemNum", itemNum);
		//hmHashMap.put("receiver", receiver);
		dataWrapper.setData(hmHashMap);
		return dataWrapper;
	}
	//保存到订单表
	@Override  //low!!
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

	//1234
	@Override
	public DataWrapper<HashMap<String, Object>> generaOrder(String token, List<OrderItem> orderItemList, Ordera order) {
		// TODO Auto-generated method stub
		//  get userId
		String userId=utilsDao.getUserID(token);

		//obtain orderId 
		String orderId=OrderIdUtils.createOrderId(userId);
		//将订单信息保存在订单里 你如是否需要发表  留言等。。。 
		/**
		 * state,order_id,user_id,qb_ded,
		 * invoice_hand, is_register, buyer_message, total_fee,
		 * actual_pay，post_fee,give_qb,created,
		 * buyer_nick,buyer_rate
		 */
		if(order.getQbDed()==null|order.getQbDed()==0){
			order.setQbDed(0);
		}if(order.getInvoiceHand()==null){
			order.setInvoiceHand("没买价没有输入发票抬头");
		}if(order.getIsRegister()==null){
			order.setIsRegister(0);
		}if(order.getBuyerMessage()==null){
			order.setBuyerMessage("该单暂时未被评价呢");
			order.setBuyerRate(0);
		}
		//保存订单数据
		placeOrderDao.createOrder(orderId,userId,order);
		int giveQbNum=0;
		//把传过来的 List<OrderItem> 遍历到order_item 表中
		Integer sumPrice=0;
		int itemSum=orderItemList.size();//商品总数量
		for(int i=0;i<orderItemList.size();i++){
			//订单商品总价
			sumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();

			//query钱币赠送百分比
			Integer qbPercentage=placeOrderDao.queryQbPercentage(orderItemList.get(i).getItemSKU());
			giveQbNum+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice()*qbPercentage;
			//将购物车的商品 同步到 订单商品表里
			placeOrderDao.synchronization(orderItemList.get(i),orderId);
			//giveQbNum+=0;
		}
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		hashMap.put("giveQbNum", giveQbNum);
		hashMap.put("itemSum", itemSum);
		
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String,Object>>();
		dataWrapper.setData(hashMap);
		return dataWrapper;
	}
}
