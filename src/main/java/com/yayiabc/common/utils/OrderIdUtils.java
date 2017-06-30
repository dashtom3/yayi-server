package com.yayiabc.common.utils;

import java.util.Random;

/**
 * 
 * 
 * @author me
 * 订单id:用户id+四位随机数
 */
public class OrderIdUtils {

	public static   String 	createOrderId(String userId){
		String str="0123456789";
		StringBuilder sb=new StringBuilder(userId);
		for(int i=0;i<4;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		///System.out.println(sb.toString());
		return sb.toString();
	}
	/*public static void main(String[] args) {
		createOrderId("lhdd=888");
		
    }*/
}
