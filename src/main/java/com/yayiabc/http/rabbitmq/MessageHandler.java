package com.yayiabc.http.rabbitmq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.model.FinalList;
import com.yayiabc.http.mvc.service.PlaceOrderService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//MessageListenerAdapter
public class MessageHandler {  
@Autowired
private PlaceOrderService placeOrderService;
@Autowired
private PlaceOrderDao placeOrderDao;


	public void onMessage(CommonMessage message) {  
		 if(message!=null){
			 Ordera ordera=null;
			 ArrayList<OrderItem> orderItemList=null;
			 Invoice invoice=null;
			// System.out.println(message);
			//将消息强转为字符串类型
			 String mess=String.valueOf(message.getMessage());
			 mess= mess.substring(15, mess.length()-1);//placeOrde
			 
			  try{
				System.err.println(mess);
				String[] st=mess.split("@"); //0 是 ordera  1是 orderItem 2是 invoice 3token
				for(int i=0;i<st.length;i++){
					st[i]=st[i].replaceAll("\\\\","");
					if(i==0){
						//将 ordera json对象字符串 转为 对象
						System.out.println(st[i]);
						JSONObject orderaJsonObject=JSONObject.fromObject(st[i].replace("]",""));
						ordera=(Ordera)JSONObject.toBean(orderaJsonObject, Ordera.class);
					}else if(i==1){
						//将 orderItem json数组字符串 转为 List对象
						JSONArray json = JSONArray.fromObject(st[i]);
						orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
					}else if(i==2){
						//将 ordera json对象字符串 转为 对象
						JSONObject invoiceJsonObject=JSONObject.fromObject(st[i]);
						invoice=(Invoice)JSONObject.toBean(invoiceJsonObject, Invoice.class);
					}
				}
				placeOrderService.generaOrder(st[3].replace("\"", ""), orderItemList, ordera, invoice);
				
			}catch(Exception e){  
				e.printStackTrace();  
			}
		 }
	}  
     public void dedStock(CommonMessage message){
    	 if(message!=null){
    		 ArrayList<OrderItem> orderItemList=null;
    		//将消息强转为字符串类型
			 String mess=String.valueOf(message.getMessage());
			 mess= mess.substring(14,mess.length()-1);//placeOrde
			 System.out.println("............."+mess);
			 JSONArray json = JSONArray.fromObject(mess);
			 orderItemList = (ArrayList<OrderItem>)JSONArray.toCollection(json,OrderItem.class);
			 List<FinalList> finalList=placeOrderDao.queryFinalList(orderItemList);
			 placeOrderService.changeStockNum(orderItemList, finalList);
    	 }
     }
}  
