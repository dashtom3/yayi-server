package com.yayiabc.common.utils;

import java.util.UUID;

/**
 * 
 * 
 * @author me
 * 订单id:用户id+四位随机数
 */
public class OrderIdUtils {
	public static String createOrderId(String userId) {
		 
		return userId+new StringBuffer(""+System.currentTimeMillis()).reverse();/*userId.replaceAll("[a-zA-Z]","" ).replace("-", "").substring(0,8)+*///String.valueOf(System.currentTimeMillis());
	}
   public static void main(String[] args) {
	   System.out.println(createOrderId("123"));
}
}
