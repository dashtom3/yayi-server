package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.Refund;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.OrderManagementService;

@Service
public class OrderManagementServiceImpl implements OrderManagementService{
	@Autowired
	private OrderManagementDao orderManagementDao;

	@Override
	public DataWrapper<List<User>> showOrder(HashMap<String, Object> hMap) {
		// TODO Auto-generated method stub
		DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		List<User> userOrderList=orderManagementDao.showOrder(hMap);
		if(userOrderList.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}else{
			dataWrapper.setData(userOrderList);
		}
		return dataWrapper;
	}
	//关闭交易
	@Override
	public DataWrapper<Void> closeTrading(String orderId,String flagBits) {
		// TODO Auto-generated method stub
		Integer flagBit=Integer.parseInt(flagBits);
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int a=orderManagementDao.closeTrading(orderId,flagBit);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		if(a>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}

		return dataWrapper;
	}
	//显示退款处理
	@Override
	public DataWrapper<List<Ordera>> refundProcessing(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<List<Ordera>> dataWrapper=new DataWrapper<List<Ordera>>();
		List<Ordera> list=orderManagementDao.refundProcessing(orderId);
		dataWrapper.setData(list);
		return dataWrapper;
	}
	//检查此订单是否包邮   如果是 那就是 如果不包邮就查出 该订单运费   (此处的 是否包邮应该在下订单模块里实现 这里 不需要  先暂时搁置)

	//模拟失去焦点事件
	public  DataWrapper<Map<String, Integer>> loseFocus(int refundNum,String OrederId,String itemId){
		DataWrapper<Map<String, Integer>> dataWrapper=new DataWrapper<Map<String, Integer>>();
		//根据订单id 查询出 当前订单的用户id 去钱币表 查看 该用户的钱币余额   queryUser
		String userId=orderManagementDao.queryUser(OrederId);
		//根据userId 查询用户余额
		int balance=orderManagementDao.userBalance(userId);
		if(balance>0){
			//
		}
		List<OrderItem> list=orderManagementDao.showFund(OrederId,itemId);
		String itemIdy=  list.get(0).getItemId();
		int QbDed= list.get(0).getQbDed();
		int price= list.get(0).getPrice();
		int num = list.get(0).getNum();
		//单个商品的退回钱币数
		int returnQbNum=price*refundNum;

		//退款金额
		int returnMoneyNum;
		if(balance>=returnQbNum){
			returnMoneyNum=0;
		}else{
			returnMoneyNum=returnQbNum-balance;
		}
		//扣除钱币数  需要先获取当前商品的对应钱币百分比    int percent=queryQbPercent(num,itemId);
		int percent=0;
		Integer dedQb=returnQbNum*percent;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("returnQbNum", returnQbNum);
		map.put("returnMoneyNum", returnMoneyNum);
		map.put("dedQb", dedQb);
		//这里的  我可以直接就把这条数据 插入到退货管理数据库中了  考虑到 用户又可能不退  暂时搁置 明天再写
		/*if(refundNum>num){  这里前端做判断
			dataWrapper.setMsg("");
		}*/
		dataWrapper.setData(map);
		return dataWrapper;
	}

	//操作退款数据
	@Override  //接收的是 前端传送过来的json数据
	public DataWrapper<Void> makeRefundData(Map<String, String> map){
		// TODO Auto-generated method stub
		//操作影响行数
		int state=0;
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Map<String,String> hmap=new HashMap<String, String>();
		//获取orderID
		String orderId=map.get("orderId");
		hmap.put("orderId", orderId);
		//orderManagementDao.queryMaxNum(, itemId);
		for(String key:map.keySet()){
			if(key.equals("arry")){
				String arry=map.get(key);
				String[] str=arry.split(",");
				for(int i=0;i<str.length;i++){
					//这里我需要数组的内容有   userid itemid num state 
					hmap.put("userId",str[0]);
					hmap.put("itemId",str[1]);
					hmap.put("num",str[2]);
					hmap.put("state",str[3]);
					 state=orderManagementDao.makeRefundData(hmap);
				}
			}
		}
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	
	//仓库发货
	@Override
	public DataWrapper<Void> warehouseDelivery(String orderId,String logisticsName,String  logisticsCode) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state =orderManagementDao.warehouseDelivery(orderId,logisticsName,logisticsCode);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}

}
