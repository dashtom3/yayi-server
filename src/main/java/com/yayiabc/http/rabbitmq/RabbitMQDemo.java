package com.yayiabc.http.rabbitmq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;

import net.sf.json.JSONObject;  
@Controller
@RequestMapping("api/c")
public class RabbitMQDemo {  

	@Autowired  
	private MessageSender messageSender;  

	@RequestMapping("d")  
	@ResponseBody
	public DataWrapper<Object> testSendMessage(
			@RequestHeader(value="token",required=false) String  token,
			@RequestParam(value="ordera",required=true) String  ordera,
			@RequestParam(value="orderItem",required=true) String  orderItem,
			@RequestParam(value="invoice",required=true) String  invoice
			){
		System.out.println(123123);
		CommonMessage message = new CommonMessage();  
		message.setSource("order");  
		JSONObject obj = new JSONObject();  
		obj.put("placeOrder", ordera+"@"+orderItem+"@"+invoice+"@"+token);  
		message.setMessage(obj);
		messageSender.setRoutingKey("message.orderQueue");
		messageSender.sendDataToQueue(message);

System.out.println(123);
		//将库存放入队列
/*		CommonMessage messages = new CommonMessage();  
		JSONObject obs = new JSONObject();  
		obs.put("stockOrder", orderItem);
		messages.setMessage(obs);
		messageSender.setRoutingKey("stock.queue");
		messageSender.sendDataToQueue(messages);*/
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		dataWrapper.setData("下单成功");
		return dataWrapper; 
	}  
}  