package com.yayiabc.common.utils;


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
		 
		userId = userId.replaceAll("[a-zA-Z]","" ).replace("-", "").substring(0,8)+String.valueOf(System.currentTimeMillis()).substring(6);
		//userId.split("-");
		return userId;
	}
	public static void main(String[] args) {
		System.out.println(createOrderId("b43d02eb-f076-4b77-a081-d28fe81b1a83"));
		
	}
}
