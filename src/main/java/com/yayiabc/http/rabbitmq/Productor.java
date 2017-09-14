package com.yayiabc.http.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.BeanUtil;
/** 
 * 功能概要：消息产生，提交到队列中去 
 * @author lihui 
 * 
 */  
@Controller
@RequestMapping("api/a")
public class Productor   {  
	final String queue_key = "test_queue_key";
	@Autowired
    private RabbitTemplate rabbitTemplate;  
      
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {  
        this.rabbitTemplate = rabbitTemplate;  
    }  
    @RequestMapping("b")  
    @ResponseBody
    public void sendMessage(String message){  
    	//Productor produtor= BeanUtil.getBean("Productor");
       System.out.println("productor:" + message);  
      rabbitTemplate.convertAndSend("test_queue_key", message);
        //rabbitTemplate.convertAndSend("RECON_START", message);  
    }  
} 
