package com.yayiabc.common.utils;

import java.util.UUID;

/**
 * 
 * 
 * @author me
 * 订单id:用户id+四位随机数
 */
public class OrderIdUtils {
	/*public static   String 	createOrderId(String userId){
		String orderId="";
		String[] str=UUID.randomUUID().toString().split("-");
		for (String string : str) {
			orderId+=string;
		}
		return orderId+new Random().nextInt(1000);
	}*/
	public static String createOrderId(String userId) {
		 
		String a= UUID.randomUUID().toString();/*userId.replaceAll("[a-zA-Z]","" ).replace("-", "").substring(0,8)+*///String.valueOf(System.currentTimeMillis());
		//userId.split("-");
		return a;
	}

}
