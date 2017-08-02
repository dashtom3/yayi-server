package com.yayiabc.http.mvc.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.help.ClassificationHelpUtils;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.OrderIdUtils;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
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




	public Double getFreight(Receiver receiver,Double sumPrice,Integer itemSum){
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
									return 0.0;
								}
							}
						}
					}
				}else{
					for(int y=0;y<citys.length;y++){
						if(Province.equals(citys[y])){
							if(sumPrice>money||sumPrice==money){
								if(list.get(i).getState()==1){
									return 0.0;
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
							return (double) (lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney());
						}
					}
				}else{
					for(int x=0;x<citys.length;x++){
						if(Province.equals(citys[x])){
							return (double) (lists.get(i).getFirstMoney()+(itemSum-1)*lists.get(i).getAddMoney());
						}
					}
				}
			}
		}
		return 0.0;
	}
	//钱币抵扣
	@Override
	public DataWrapper<Void> ded(String token, int num/*,Integer  receiverId*/) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		// TODO Auto-generated method stub
		//该单计算运费
		/*Receiver receiver=placeOrderDao.queryReceiver(receiverId);

		Double postFee=getFreight(receiver,sumPrice,itemSum);*/
		String userId=utilsDao.getUserID(token);
		//查询当前用户的钱币剩余
		int su= placeOrderDao.ded(userId);
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
	@Override
	public DataWrapper<HashMap<String, Object>> generaOrder(String token, List<OrderItem> orderItemList, Ordera order,
			Invoice  invoice
			) {
		//记录工具设备类的商品 个数
		int TooldevicesSumCount=0;
		// TODO Auto-generated method stub
		//  get userId
		DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String,Object>>();
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		try {
			String userId=utilsDao.getUserID(token);
			System.out.println(userId);
			//obtain orderId 
			String orderId=OrderIdUtils.createOrderId(userId);
			//将订单信息保存在订单里 你如是否需要发表  留言等。。2[=。 
			if(order!=null){
				if(order.getQbDed()==null){
					order.setQbDed(0);
				}if(order.getInvoiceHand()==null){
					order.setInvoiceHand("没买价没有输入发票抬头");
				}if(order.getIsRegister()==null){
					order.setIsRegister(0);
				}if(order.getBuyerMessage()==null){
					order.setBuyerMessage("该单暂时未被评价呢");
					order.setBuyerRate(0);
				}
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
			for(int i=0;i<orderItemList.size();i++){
				//query钱币赠送百分比
				//Integer qbPercentage=placeOrderDao.queryQbPercentage(orderItemList.get(i).getItemSKU());
				//giveQbNum+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice()*qbPercentage;
				// 根据传过来的  商品sku 与 商品购买数目 查找对应的 其他参数（已知sku  num,orderId.,create,update） 
				//需要item_name,price,picpath, a,b,c 属性，
				ItemValue itemVlue=placeOrderDao.queryAttributes(orderItemList.get(i).getItemSKU());
				//新加入function 需要sku 找到itemid  找到商品表里面的item_sort（商品类型） 加入到订单商品表里 itemVlue里面有itemid 这里直接取
				String itemType=placeOrderDao.queryItemsort(itemVlue.getItemId());
				orderItemList.get(i).setItemType(itemType);
				//根据sku 查找商品名称 业户说的
				String itemName=placeOrderDao.queryItemName(itemVlue.getItemId());
				//根据sku查找商品的图片路径
				String itemPicPath=placeOrderDao.queryItemPicPath(itemVlue.getItemId());
				//新 function 本单获得钱币数新规则 这里Set进商品品牌
				String  itemBrandName=placeOrderDao.queryItemBrandNameByItemId(itemVlue.getItemId());
				orderItemList.get(i).setItemBrandName(itemBrandName);

				orderItemList.get(i).setItemSKU(itemVlue.getItemSKU());
				orderItemList.get(i).setItemName(itemName);
				orderItemList.get(i).setPicPath(itemPicPath);
				orderItemList.get(i).setPrice(itemVlue.getItemSkuPrice());
				orderItemList.get(i).setItemPropertyNamea(itemVlue.getItemPropertyInfo());
				orderItemList.get(i).setItemPropertyNameb(itemVlue.getItemPropertyTwoValue());
				orderItemList.get(i).setItemPropertyNamec(itemVlue.getItemPropertyThreeValue());
				//查询该商品的库存数量
				int ItemInventNum=placeOrderDao.queryItemInventNum(itemVlue.getItemSKU());
				//判断
				if(ItemInventNum>=orderItemList.get(i).getNum()){
					//如果库存数量大于购买数量  就去库存减去购买数
					//更改库存数量
					placeOrderDao.updateInventNum(
							String.valueOf(ItemInventNum-orderItemList.get(i).getNum()),itemVlue.getItemSKU()
							);				
					//---
				}else{
					hashMap.put("数量不足", "该商品"+orderItemList.get(i).getItemName()+"数量不足，您最多购买"
							+String.valueOf(ItemInventNum-orderItemList.get(i).getNum())+"件。"
							);	
					//删除该订单   和订单商品表里的信息
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					dataWrapper.setData(hashMap);
					return dataWrapper;
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

				//将商品 同步到 订单商品表里
				int sta=placeOrderDao.synchronization(orderItemList.get(i),orderId);
				//放一件到订单商品表清一件到
				if(sta>0){
					placeOrderDao.cleanCart(userId,orderItemList.get(i).getItemSKU());
				}
				//这里计算除道邦之外的商品分类价格  耗材类  工具设备类
				if("耗材类".equals(orderItemList.get(i).getItemType())){
					AllSuppliesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				}else if("工具设备类".equals(orderItemList.get(i).getItemType())){
					AllTooldevicesSumPrice+=orderItemList.get(i).getNum()*orderItemList.get(i).getPrice();
				}
				/*if(i!=orderItemList.size()-1){
					sb.append(orderItemList.get(i).getItemName()+",");
				}else{
					sb.append(orderItemList.get(i).getItemName());
				}*/
				//giveQbNum+=0;
				/*System.out.println(orderItemList.get(i));*/
			}
			
			//这里计算本单赠送钱币数
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
			/*try {
				//hashMap.put("itemNames", (sb.toString().getBytes("utf-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//该单计算运费
			Receiver receiver=placeOrderDao.queryReceiver(order.getReceiverId());

			Double postFee=getFreight(receiver,sumPrice,itemSum);

			//生产发票性质
			if("1".equals(order.getInvoiceHand())){
				invoice.setOrderId(orderId);
				int  sign=placeOrderDao.saveInvoiced(invoice);
				if(sign<0){
					dataWrapper.setMsg("发票生产失败");
					return dataWrapper;
				}
			}
			//订单实际价格的计算
			Double actualPay=sumPrice+postFee-order.getQbDed();
			//放入订单表
			placeOrderDao.insertActualPay(orderId,String.valueOf(actualPay));
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
			placeOrderDao.saveGiveQbNum(String.valueOf(giveQbNum),String.valueOf(postFee),
					String.valueOf(sumPrice)
					,orderId);
			//这里保存到ordera表里 并没有把赠送钱币数 给到用户余额中  必须等
			//该用户付款成功再根据orderId 把余额放到该用户的账户余额中。。。。。。。。。。。。。。。。。。。。。。。
			//直接放入order表
			int a=placeOrderDao.insertClassItemsSumMoney(String.valueOf(AllSuppliesSumPrice),
					String.valueOf(AllTooldevicesSumPrice)
					,orderId
					);
			//得到 商品分类价格工具类
			/*ConcurrentHashMap<String,Object> synMap=ClassificationHelpUtils.getInstance().getSynchronizedMap();
			//synMap
			 */			//商品描述==
			//hashMap.put("itemMS", "不错");
			//将该订单加入到Map缓存中
			CacheUtils.getInstance().getCacheMap().put(orderId, new Date());
			//判断 actualPay 是否是0付款
			if(actualPay==0){
				PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
				payAfterOrderUtil.universal(orderId);
			}
			dataWrapper.setData(hashMap);
			return dataWrapper;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
