package com.yayiabc.http.rabbitmq;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.PlaceOrderService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageHandler {  
@Autowired
private PlaceOrderService placeOrderService;

	public void handleMessage(CommonMessage message) {  
		 Ordera ordera=null;
		 ArrayList<OrderItem> orderItemList=null;
		 Invoice invoice=null;
		//将消息强转为字符串类型
		 String mess=(String) message.getMessage();
		 String str=mess.split(",")[1];
		 str= str.substring(12, mess.length()-1);//placeOrder
		 System.out.println("adsadasdsadddddddddddddddddddddddd         " +str      );
		try{
			
			String[] st=str.split("|"); //0 是 ordera  1是 orderItem 2是 invoice
			for(int i=0;i<st.length;i++){
				if(i==0){
					//将 ordera json对象字符串 转为 对象
					JSONObject orderaJsonObject=JSONObject.fromObject(st[i]);
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
			placeOrderService.generaOrder(st[3], orderItemList, ordera, invoice);
			
			System.out.println( message);  
		}catch(Exception e){  
			e.printStackTrace();  
		}  
	}  

}  
