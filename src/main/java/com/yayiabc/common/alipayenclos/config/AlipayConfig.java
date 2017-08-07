package com.yayiabc.common.alipayenclos.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	//public static String partner = "2088221734067901";
	  public static String partner = "2088121334701862"; //妆前
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    //public static String key ="6k7n8kafmda1t22yl066v4o3lxws9u0s";
      public static String key ="qk5s6ct89j54syu3v84m32b0s5oie1to";//妆前
    
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://47.93.48.111:8080/api/pay/notifyVerifica";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.93.48.111:8080/api/pay/payVerifica";
	//public static String return_url = "http://47.93.48.111:85/paySuccess";
	

	// 签名方式
	public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "UTF-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	//|||||||||||||||||||||||||||下面是手机网站支付
	// 商户appid
	public static String APPID = "2016080600182827";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMr7ya59h5y8BNtgvqDTzTASVfthsBRxA+OgYYt8ODBKmw/EX0mfYD3d9AqZ4U+kytyvMC3GVveIR8RuundG+bHzPe+gI8BaKbae5KaOK1FCWFMAu3NlZnPGBNfaYG8syJD2PwfY2skFgeZRx6UVDud83xnMhzgo6uHDqb+CfLHxAgMBAAECgYBNhU2oAy74j+H2+oKzH1CczFPkFpHNrL/Efiriv4UiBEwmAGQ31NNHrW2XYt9msFP9ZjarNLVgAb8O6Q7zoLqZKfvElAFQW51G5DVo48hb/bN0rtWHjKHuTGTZ0fyCQ1BWSucPPJt3iPRi+AqQXR5eVImVvd/4bJEUpYDhCyN9RQJBAPQG2mrNPODnpG1uLN9USHGSDFWHoWd+Y7OO5kS+sS9aRBSnlyPa1UsABdr9aMaSTI9ibJeYYVZg8ZF89dRV70sCQQDU8Wctz6biWx1WQBTCjqmXSa7mlbW4N6DOoE5yiSQULA6EkCVrz3BFsU4ygwCwph3ZSY2zDH9oQ2nAXHIBKFIzAkEAjBfDHL1mzdcuVoSBzlScolauLgwHZVrx8gt/tsejAQRZQWJVKohfGweQVQBWPmmO+mIO5ZjdiLFu/Y83sgcDsQJBAIQDCFkxM59+pwhSf8xBz0d4KZLP2zFSY0HVxexhstlCmtl514knt82s5vKPnnzk7vpHDC6lH9NOf5/+b7rh+VsCQCG6y/WhKAFYhsGxnrhQ36bRRdl3QBs4bupEhcrhGupFi+acMYcJBa3Wze1GOI751RMN5E7GNvo5rylrH0RShKI=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
	/*// 日志记录目录
	public static String log_path = "/log";*/
	// RSA2
	public static String SIGNTYPE = "RSA";
	
	public static String phoneNotify_url = "http://47.93.48.111:8080/api/phonePay/notifyVerifica";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String phoneReturn_url = "http://47.93.48.111:8080/api/phonePay/payVerifica";
}

