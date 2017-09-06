package com.yayiabc.http.mvc.service.Impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.OrderIdUtils;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.model.FinalList;
import com.yayiabc.http.mvc.service.PlaceOrderService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{
	@Autowired
	private PlaceOrderDao placeOrderDao;
	@Autowired
	private UtilsDao utilsDao;




	public Integer getFreight(Receiver receiver,Double sumPrice,Integer itemSum){
		String Province =receiver.getProvince();
		//查询包邮表数据
		List<FreeShipping> list=placeOrderDao.queryPostFree();
		if(!list.isEmpty())
		{
			for(int i=0;i<list.size();i++){

				String[] citys=list.get(i).getPostCity().split(",");
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
				if(Province==null){
					String cityString=receiver.getCity();
					for(int x=0;x<citys.length;x++){
						if(cityString.equals(citys[x])){
							return (lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney());
						}
					}
				}else{
					for(int x=0;x<citys.length;x++){
						if(Province.equals(citys[x])){
							return  (lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney());
						}
					}
				}
			}
		}
		return 0;
	}
	//钱币抵扣
	@Override
	public DataWrapper<Integer> ded(String token, int num/*,Integer  receiverId*/) {
		DataWrapper<Integer> dataWrapper=new DataWrapper<Integer>();
		// TODO Auto-generated method stub
		//该单计算运费
		/*Receiver receiver=placeOrderDao.queryReceiver(receiverId);

		Double postFee=getFreight(receiver,sumPrice,itemSum);*/
		String userId=utilsDao.getUserID(token);
		//查询当前用户的钱币剩余
		int su= placeOrderDao.ded(userId);
		dataWrapper.setData(su);
		if(su>=num){
			dataWrapper.setMsg("余额充足");
		}else{
			dataWrapper.setMsg("余额不足");
		}
		return dataWrapper;
	}
	//更改收货地址
	public DataWrapper<HashMap<String, Object>>  upateAddress(Integer receiverId,Double sumPrice,Integer itemSum){
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String, Object>>();

		Receiver receiver=placeOrderDao.queryReceiver(receiverId);

		double yunfei=getFreight(receiver, sumPrice, itemSum);
		HashMap<String, Object> hashMap=new HashMap<String,Object>();
		hashMap.put("postFee", yunfei);
		hashMap.put("Receiver", receiver);
		dataWrapper.setData(hashMap);
		return  dataWrapper;
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
	@Transactional
	@Override
	public DataWrapper<HashMap<String, Object>> generaOrder(String token, List<OrderItem> orderItemList, Ordera order,
			Invoice  invoice
			) {
		//判断  乾币！！！、
		if(order.getQbDed()==null){
			order.setQbDed(0);
		}
		DataWrapper<Integer> data=ded(token,order.getQbDed());
		int qbBalance=data.getData();
		if(qbBalance<order.getQbDed()){
			throw new OrderException(ErrorCodeEnum.QBDED_Error);
		}
		//记录工具设备类的商品 个数
		int TooldevicesSumCount=0;
		// TODO Auto-generated method stub
		//  get userId
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String,Object>>();
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		String userId=null;
		try {
			userId=utilsDao.getUserID(token);
			//obtain orderId 
			String orderId=OrderIdUtils.createOrderId(userId);
			//将订单信息保存在订单里 你如是否需要发表  留言等。。2[=。 
			if(order != null){
				if(order.getQbDed()==null){
					order.setQbDed(0);
				}if(order.getInvoiceHand()==null){
					order.setInvoiceHand("该用户不需要发票");
				}if(order.getIsRegister()==null){
					order.setIsRegister(0);
				}if(order.getBuyerMessage()==null){
					order.setBuyerMessage("该单暂时未被评价呢");
					order.setBuyerRate(0);
				}
			} else {
				//set datawrapper
				return  dataWrapper;
			}
			//创建订单并保存订单数据
			placeOrderDao.createOrder(orderId,userId,order);
			//本单赠送钱币百分比
			double giveQbNum=0;
			//道邦总价
			double daoBnagSumPrice=0;

			//除道邦之外的耗材类总价格 
			double SuppliesSumPrice=0;
			//除道邦之外的工具设备类总价格
			double TooldevicesSumPrice=0;	
			//存放 商品名称的 
			StringBuffer sb=new StringBuffer();
			//全部的耗材类总价格
			double AllSuppliesSumPrice=0;
			//全部的工具设备类总价格
			double AllTooldevicesSumPrice=0;	


			//把传过来的 List<OrderItem> 遍历到order_item 表中
			Double sumPrice=0.0;
			int itemSum=orderItemList.size();//商品总数量
			//抽取for循环的第一步  根据orderItemList 的itemsku 去数据库做批量查询  出 ArrayList<ItemValue>
			//List<ItemValue> itemValueList=placeOrderDao.queryAttributesList(orderItemList);
			//抽取for循环的第二步 根据 itemValueList里的 itemId 去数据库做批量查询 出ArrayList<orderItem>
			/**
			 * <resultMap type="com.yayiabc.http.mvc.pojo.jpa.OrderItem" id="queryOrderA">
				<result property="itemName" column="item_name" />
				<result property="itemType" column="item_sort" />
				<result property="itemBrandName" column="item_brand_name" />
				<result property="picPath" column="item_pica" />
	           </resultMap>
			 */
			//List<OrderItem> orderItemListAttributes=placeOrderDao.queryItemIdListByitemValueList(itemValueList);

			//-----啦啦
			List<FinalList> finalList=placeOrderDao.queryFinalList(orderItemList);
			/*if(itemValueList.size()>orderItemListAttributes.size()){

			}*/
			
			for(int i=0;i<orderItemList.size();i++){
				for(int x=0;x<finalList.size();x++){
					if(orderItemList.get(i).getItemSKU().equals(finalList.get(x).getItemSKU())){
						orderItemList.get(i).setItemBrandName(finalList.get(x).getItemBrandName());
						orderItemList.get(i).setItemName(finalList.get(x).getItemName());
						orderItemList.get(i).setPicPath(finalList.get(x).getPicPath());
						orderItemList.get(i).setItemPropertyNamea(finalList.get(x).getItemPropertyInfo());
						orderItemList.get(i).setItemPropertyNameb(finalList.get(x).getItemPropertyTwoValue());
						orderItemList.get(i).setItemPropertyNamec(finalList.get(x).getItemPropertyThreeValue());
						orderItemList.get(i).setPrice(finalList.get(x).getItemSkuPrice());
						orderItemList.get(i).setOrderId(orderId);
						orderItemList.get(i).setItemType(finalList.get(x).getItemType());
					}
				}
				
				if("上海道邦".equals(orderItemList.get(i).getItemBrandName())){
					daoBnagSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				}else{
					//这里计算除道邦之外的商品分类价格  耗材类  工具设备类
					if("耗材类".equals(orderItemList.get(i).getItemType())){
						SuppliesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
					}else if("工具设备类".equals(orderItemList.get(i).getItemType())){
						TooldevicesSumCount+=orderItemList.get(i).getNum();
						TooldevicesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
					}
				}
				//订单商品总价
				sumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				//这里计算除道邦之外的商品分类价格  耗材类  工具设备类
				if("耗材类".equals(orderItemList.get(i).getItemType())){
					AllSuppliesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				}else if("工具设备类".equals(orderItemList.get(i).getItemType())){
					AllTooldevicesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				}
			}
			//更改库存 与校验商品是否在售卖状态
			boolean flag=changeStockNum(orderItemList,finalList);
			if(!flag){
				throw new OrderException(ErrorCodeEnum.ITEMSTOCK_Error); 
			}
			//将商品 同步到 订单商品表里--------双休优化  批量 插入到order_item表中
			int a=placeOrderDao.batchSynchronization(orderItemList);
			//清空购物车 双休优化
			int q=placeOrderDao.cleanCartList(userId,orderItemList);
			if(a<=0){
				throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
			}
			//计算本单赠送钱币数
			giveQbNum=calculQbNum(daoBnagSumPrice,SuppliesSumPrice,TooldevicesSumCount,TooldevicesSumPrice);
			
			
			//该单计算运费
			Receiver receiver=placeOrderDao.queryReceiver(order.getReceiverId());
			Integer postFee=getFreight(receiver,sumPrice,itemSum);
			//生产发票性质
			if("1".equals(order.getInvoiceHand())){
				invoice.setOrderId(orderId);
				invoice.setUserId(userId);
				int  sign=placeOrderDao.saveInvoiced(invoice);
				if(sign<=0){
					throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
				}
			}
			//订单实际价格的计算
			Double actualPay=sumPrice+postFee-order.getQbDed();
			//放入订单表
			//placeOrderDao.insertActualPay(orderId,String.valueOf(actualPay));
			hashMap.put("OrderId",orderId);
			hashMap.put("sumPrice",String.valueOf(sumPrice));
			hashMap.put("giveQbNum", giveQbNum);
			hashMap.put("itemSum", itemSum);
			hashMap.put("postFee", postFee);
			hashMap.put("actualPay", actualPay);
			hashMap.put("AllSuppliesSumPrice", AllSuppliesSumPrice);
			hashMap.put("AllTooldevicesSumPrice", AllTooldevicesSumPrice);
			hashMap.put("SuppliesSumPrice", SuppliesSumPrice);
			hashMap.put("TooldevicesSumPrice", TooldevicesSumPrice);
			hashMap.put("daoBnagSumPrice", daoBnagSumPrice);

			//本单赠送钱币数保存到数据库
			if(
					placeOrderDao.saveGiveQbNum(String.valueOf(giveQbNum),String.valueOf(postFee),
							String.valueOf(sumPrice)
							,orderId,String.valueOf(AllSuppliesSumPrice),String.valueOf(AllTooldevicesSumPrice)
							,String.valueOf(actualPay)
							)<=0
					)
			{
				throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
			}
            //放入缓存
			CacheUtils.getInstance().getCacheMap().put(orderId, new Date());

			//判断客服是否全额乾币支付
			if(actualPay==0){
				PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
				if(!payAfterOrderUtil.universal(orderId,"3")){
					throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
				}
			}
			dataWrapper.setData(hashMap);
			return dataWrapper;
		} catch (Exception e){
			throw new RuntimeException(e); 
		}
	}
	
	//下单更改订单里的商品库存
	private boolean changeStockNum(List<OrderItem> orderItemList,List<FinalList> finalList){
		if(orderItemList.size()!=finalList.size()){
			return false;
		}else{
			for(int i=0;i<orderItemList.size();i++){
				//判断当前商品是否在售卖状态
				if(finalList.get(i).getCanUse()==1){
				if(finalList.get(i).getStockNum()>=orderItemList.get(i).getNum()){
					//如果库存数量大于购买数量  就去库存减去购买数
					//更改库存数量
					placeOrderDao.updateInventNum(
							String.valueOf(finalList.get(i).getStockNum()-orderItemList.get(i).getNum()),finalList.get(i).getItemSKU()
							);
					return true;
					//---
				}else{
					//删除该订单   和订单商品表里的信息
					return false;
					//return dataWrapper;
				}
				}else{
					return false;
				}
			}
		}
		return false;
	}
	private double calculQbNum(double daoBnagSumPrice,double SuppliesSumPrice,double TooldevicesSumCount,double TooldevicesSumPrice){
		double giveQbNum=0.0;
		//首先道邦品牌
		if(daoBnagSumPrice>0&&daoBnagSumPrice<300){
			giveQbNum=giveQbNum+daoBnagSumPrice*0.03;
		}else if(daoBnagSumPrice>=300&&daoBnagSumPrice<600){
			giveQbNum=giveQbNum+daoBnagSumPrice*0.05;
		}else if(daoBnagSumPrice>=600&&daoBnagSumPrice<1200){
			giveQbNum=giveQbNum+daoBnagSumPrice*0.08;
		}else if(daoBnagSumPrice>=1200&&daoBnagSumPrice<2500){
			giveQbNum=giveQbNum+daoBnagSumPrice*0.12;
		}else if(daoBnagSumPrice>=2500){
			giveQbNum=giveQbNum+daoBnagSumPrice*0.15;
		}
		//其他品牌 耗材类
		if(SuppliesSumPrice>0&&SuppliesSumPrice<500){
			giveQbNum=giveQbNum+SuppliesSumPrice*0.03;
		}else if(SuppliesSumPrice>=500&&SuppliesSumPrice<1000){
			giveQbNum=giveQbNum+SuppliesSumPrice*0.05;
		}else if(SuppliesSumPrice>=1000&&SuppliesSumPrice<3000){
			giveQbNum=giveQbNum+SuppliesSumPrice*0.08;
		}else if(SuppliesSumPrice>=3000){
			giveQbNum=giveQbNum+SuppliesSumPrice*0.12;
		}
		//其他品牌 工具设配类
		if(TooldevicesSumCount==1){
			giveQbNum=giveQbNum+TooldevicesSumPrice*0.05;
		}else if(TooldevicesSumCount>=2){
			giveQbNum=giveQbNum+TooldevicesSumPrice*0.10;
		}
		return giveQbNum;
	}
	//查询上次下订单时填写的发票信息
	@Override
	public DataWrapper<Invoice> queryLastInvoice(String token) {
		// TODO Auto-generated method stub
		String userId=utilsDao.getUserID(token);
		DataWrapper<Invoice> dataWrapper=new DataWrapper<Invoice>();
		List<Invoice> in=placeOrderDao.queryLastInvoice(userId);
		if(in!=null){
			if(!in.isEmpty()){
				dataWrapper.setData(in.get(0));
			}
		}
		return dataWrapper;
	}
}
