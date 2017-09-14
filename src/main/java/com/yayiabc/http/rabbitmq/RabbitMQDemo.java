package com.yayiabc.http.rabbitmq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import net.sf.json.JSONObject;  
@Controller
@RequestMapping("api/c")
public class RabbitMQDemo {  

	@Autowired  
	private MessageSender messageSender;  

	@RequestMapping("d")  
	@ResponseBody
	public void testSendMessage(
			@RequestHeader(value="token",required=false) String  token,
			@RequestParam(value="ordera",required=true) String  ordera,
			@RequestParam(value="orderItem",required=true) String  orderItem,
			@RequestParam(value="invoice",required=true) String  invoice
			){
		CommonMessage message = new CommonMessage();  
		message.setSource("tonson");  
		JSONObject obj = new JSONObject();  
		obj.put("placeOrder", ordera+"|"+orderItem+"|"+invoice+"|"+token);  
		message.setMessage(obj);
		messageSender.setRoutingKey("message.orderQueue");
		messageSender.sendDataToQueue(message); 
		
	}  
}  