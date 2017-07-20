package com.yayiabc.http.mvc.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.service.TimerChangeStateService;
@Component
public class MyTask extends TimerTask{
	/*@Autowired*/
	private TimerChangeStateService timerChangeStateService;
	@Override
	public void run() {
		kk();
			}
	private   synchronized void kk(){
		TimerChangeStateService timerChangeStateService=BeanUtil.getBean("TimerChangeStateServiceImpl");
		CacheUtils cache=	CacheUtils.getInstance();
		Map<String,Date> map=cache.getCacheMap();
		for(String key:map.keySet()){
			//System.out.println(new Date().getTime()-map.get(key).getTime()+"毫秒");
			
			if(new Date().getTime()-map.get(key).getTime()>=5*60*1000
					){
				  System.out.println("该订单已经关闭1:"+key);
				//查看该单state  状态 
				//System.err.println();9421c01f-d987-46dd-bcd8-9de8d2b63fd9
				
				
				int state=timerChangeStateService.timerQueryState(key);
				if(state==1){
					  System.out.println("该订单已经关闭2:"+key);
					//  更改数据库交易状态为 交易关闭
					int sign=timerChangeStateService.changeState(key);
					  System.out.println("该订单已经关闭3:"+key);
					if(sign>0){
						  System.out.println("该订单已经关闭4:"+key);
						//把该订单里的 商品 在返回库存表里去啊啊啊啊啊
					 //根据订单id 查该订单里面的所有商品
						List<OrderItem> orderItemList=timerChangeStateService.queryOrderItems(key);
						for(int i=0;i<orderItemList.size();i++){
							//还给库存表orderItemList.get(i).getItemSKU()
							timerChangeStateService.stillItemValueNum(orderItemList.get(i).getItemSKU(),
									orderItemList.get(i).getNum()
									);
							
						}
						System.out.println("库存已还原");
						map.remove(key);
					}
				}
                 
			}
			if(map.get(key)!=null){
				if(new Date().getTime()-map.get(key).getTime()>=cache.getContinuedTime()){
					map.remove(key);
				}
			}
		}

	}
}
