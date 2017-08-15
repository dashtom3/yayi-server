package com.yayiabc.http.mvc.controller.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.service.TimerChangeStateService;
public class MyTask /*extends TimerTask*/{
	
	public synchronized  void kk(){
		TimerChangeStateService timerChangeStateService=BeanUtil.getBean("TimerChangeStateServiceImpl");
		CacheUtils cache=	CacheUtils.getInstance();
		Map<String,Date> map=cache.getCacheMap();
		//得到一个临时容器
		List<String> temporaryList=CacheUtils.getTemporaryList();
		if(map!=null)
			if(!map.isEmpty()){
                
				/*List<String> temporaryList =new ArrayList<String>();*/
				
				Iterator<Map.Entry<String, Date>> it = map.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<String, Date> entry = it.next();
					
					if(new Date().getTime()-entry.getValue().getTime()>=30*60*1000
							){
						//查看该单state  状态 
						//System.err.println();9421c01f-d987-46dd-bcd8-9de8d2b63fd9
                        
                        //将超时的订单id 放入temporaryList中 
					    temporaryList.add(entry.getKey());
						map.remove(entry.getKey());
						/*int state=timerChangeStateService.timerQueryState(entry.getKey());
						if(state==1){
							System.out.println("该订单已经关闭2:"+entry.getKey());
							//  更改数据库交易状态为 交易关闭
							int sign=timerChangeStateService.changeState(entry.getKey());
							System.out.println("该订单已经关闭3:"+entry.getKey());
							if(sign>0){
								System.out.println("该订单已经关闭4:"+entry.getKey());
								//把该订单里的 商品 在返回库存表里去啊啊啊啊啊
								//根据订单id 查该订单里面的所有商品

								List<OrderItem> orderItemList=timerChangeStateService.queryOrderItems(entry.getKey());
								int a=timerChangeStateService.stillItemValueNum(
										orderItemList
										);
								for(int i=0;i<orderItemList.size();i++){
									//还给库存表orderItemList.get(i).getItemSKU()
									timerChangeStateService.stillItemValueNum(orderItemList.get(i).getItemSKU(),
											orderItemList.get(i).getNum()
											);
								}
								System.out.println("库存已还原");
								map.remove(entry.getKey());
							}
						}else{
							map.remove(entry.getKey());
						}*/

					}
					/*if(map.get(key)!=null){
					if(new Date().getTime()-map.get(key).getTime()>=cache.getContinuedTime()){
						map.remove(key);
					}
				}*/
				}
	               //订单关闭
		  if(!temporaryList.isEmpty()){
				int a=timerChangeStateService.closeOrder(temporaryList);
			    /*还原库存操作
			     * 1查询订单下面的所有商品 主要获取商品的购买个数
			     * 2一一还原到item_value的库存字段上 
			     */
			
			List<OrderItem> OrderItemNums=timerChangeStateService.queryOrderItemNums(temporaryList);
			temporaryList.clear();
			int q=timerChangeStateService.stillItemsListValueNum(OrderItemNums);
		  }
	    }
	}
}
