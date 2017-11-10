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
						

					}
					
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
