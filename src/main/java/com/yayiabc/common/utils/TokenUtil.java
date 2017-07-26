package com.yayiabc.common.utils;

/**
 * 三种不同的方式获取token,并且转化成原来的token
 * @author 小月亮
 *
 */
public class TokenUtil {
	
	/**
	 * 获取电商平台的token,规则是在token之前加user
	 */
	public static String getUserToken(String token){
		String userToken=token.substring(4,40);
		return userToken;
	}
	
	/**
	 * 获取管理员的token,规则是在token之前加admin
	 */
	public static String getAdminToken(String token){
		String adminToken=token.substring(5,41);
		return adminToken;
	}
	
	/**
	 * 获取创客系统的token,规则是在token之前加sale
	 */
	public static String getSaleToken(String token){
		String saleToken =token.substring(4,40);
		return saleToken;
	}
	
	/**
	 * 生成电商平台的token,规则是在token之前加user,并且在token之后加时间戳
	 */
	public static String makeUserToken(String token){
		String userToken="user"+token+System.currentTimeMillis();
		return userToken;
	}
	
	/**
	 * 生成管理员的token,规则是在token之前加admin,并且在token之后加时间戳
	 */
	public static String makeAdminToken(String token){
		String adminToken="admin"+token+System.currentTimeMillis();	
		return adminToken;		
	}
	
	/**
	 * 生成创客系统的token,规则是在token之前加sale,并且在token之后加时间戳
	 */
	public static String makeSaleToken(String token){
		String saleToken= "sale" +token +System.currentTimeMillis();
		return saleToken;
	}
}
