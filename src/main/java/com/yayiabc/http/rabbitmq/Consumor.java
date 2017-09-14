package com.yayiabc.http.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/** 
 * 功能概要：消费接收 
 * @author zhangyunye 
 * 
 */  
@Component
public class Consumor implements MessageListener{  

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		  System.out.println("consumor:" + arg0); 
	}     
} 