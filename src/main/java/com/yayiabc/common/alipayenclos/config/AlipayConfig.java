package com.yayiabc.common.alipayenclos.config;

import com.yayiabc.common.utils.GlobalVariables;

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
	public static String notify_url = GlobalVariables.domain+"/api/pay/notifyVerifica";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url =  GlobalVariables.domain+"/api/pay/payVerifica";
	//public static String return_url = "http://47.93.48.111:85/paySuccess";
	

	// 签名方式
	public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
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
	public static String APPID = "2016022401160229";
	//沙箱测试appid
	public static String DEVAPPID = "2016080600182827";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRvxFYA40megHgriSG7F1krSTkYGedyXbdzjBXcJ9juvPCmuLtpRBjQ110a4Pv0vD/AUG7GM5/DQEUm05XJq8Zrq/OJ6zHDN0dGsVyv5uvkhdFpDisUhAsZmNL05JnBFU1mgb+RVcDLKP7uUdxxboGu7VZMEyrhc2ig3hJudh495AyOvQQqSC2AFMURpLBa0sfUDpfk0H64vQBfzmRgFpYPLJUNYr5oIux5cPKj6A0XKb7Fz7+0WElibmcQ/tBpwzvCw55sNOEvqHS53Y38kGHm1nuLwOHdHggWiqGXNV9oeWhxjx9k7RsySAtwKFLH7kSHGO7M2PZjYN8EEXpYH5jAgMBAAECggEAVuy8/GBJ3AvieM2D7ZCldvkpSqgQxHO22D00acI63fFPlt5nv9K1vm4cG+ZP70ZBA+YM/Pijl/T6kv65r5Lb1hZwtiTFkIYkjko8Pt72jLU1+gwi3Ouox8JdMIlEBPr5kxmFWDuk1LjYKLbtXHbQJ1nmpAYBhD8uUOHWaSpVaHdf9rrkS4e5AtW5wHjsTmqOOvaRNFVKmEQG/7uiRTUQRJwVOvc7wd4K7En9EOHEBxo+Zhlf2clqSHCnxHIY56QWXbzzIvHeH6t99XwI16zoko8UuKDQ+MZhKEjuoWQ4wnvif262rsFR0mzZSNIiYi/WVuZ4e9AO8VPddyym7WcskQKBgQDC48RlcKU4Sb0U387MkI5osc8dq6yZZTPaB6z3STQEzQqvcM66vi+l+7/+zQ4uEw3sI2qes+ze02ZeRf+JhWZr711Mb3/qnZOYDD0W3DCiKqpb/v1j1O4i3TVegAi12I+oTUMgybIrfixf3vm8cl0N8JyR9BQaaaAm2/d01ciz2wKBgQC/cnQZ1O2x7W74WMYZ28DKGyiuBgWXniyzCFMFm+TKRVsXOw6WRmtU5L2/hbQUngjHKpemAK23LsjLrFd337spzkETlpYAMIFbO3MWCewMamPGyHGK/ZRh1Txynz2V+EjtjiPF5rfX9b2FLK1ypCCUBPiQONtt3phggJ6LJ18qGQKBgBGZO7zlRSYmEnr2EtiKHTH7DHbjb7ySVPJVSxsxcw8T7OkWyLQ4RFk6uIWrwqIKenFeaVxXZwZgFJhivcaPU0Zf4Q7r6qMnA5LxV/ree03JiISyK/N94NspGo6kBSVWnvJVt8AYNoyS4jLuEaMguKJvndPYNJCGBT49EsVsIKmNAoGAO/1pVA7czLxR4ZAvwzqRFsOb46wPZIs0BoW9PiyzbI5/FLB0ybIXP+rNao5C95LtvTsjeekMhjIqyWefoPiCdX8j9TdimXyZjDT8wxMo3FsaGNgGfD8pl95xqpkreyrp70Zy/zhks09Vq71sPFghvUAxgz/NwofVx6eGBqS3tUECgYEAhAiOZl8l4Cx6PqyFe8WCRVzzYL7IEVSHxoKunxyHhIGLL+wQQfuoJyxqYXPjF+sh2iswQYCeNcIWYAW91hjRh4rS5gAlWdQe/poeWAeYbg9ZxwwZltg7HcjsyM4RTgwhpZKP6KjLe5vPDr3p22BGt5plqoB0dFG4H8sC7qTbHjY=";
	//public static String RSA_PRIVATE_KEY="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCLZOwkgzEyh2yHAEfPXadsT+Foo2tWJ/bqoVF8MA1ZrqmD+852Ar+nDA/Bo4pj6EdBMTzZU260/8p+s6ioTeD9SWPFu1s+kw++u6kAYQHET4fAOHk7WiDnHD2iyMdMZjsrPS1mO0oKdIBWLF9noBolBFuOzYsET22UNOa3Qe1AW0rHOx0cgTXqRIEVO1/TtOAMyOMf2eJxfvjhxNt5AfMx1J7/La3VCLWyTZBjkySEInM3hLeoGiQuTlPY/vvZWh2IZJFl6HajlMgRDPmLYeipifyJghpynSHPWIIiCOYSuh1Ykui25mT2xLdTJ0+4470V8XF4mTVOb93oFt1X2GKZAgMBAAECggEAC0bw68tJUaMvHoThSl9zSgn0XHjMGD5HdZfzda6VhenhgPIAM//o7WTGaY4HhimmihmnqJVdYXtIMJPsUyFp0FP9pGOR8DAJmA6qj4NJl04v11c6R5j1DH12ZxMDPKUQqGDcqWamiM4AC/ivBz0moFQH7DeoxYJ/hPv6plzJCum5MhsPaFrh5D3h3Cp+uykcA8XjSz6TrOqWfqAv3fd5TTDDMd9LAa7awYcaQJC7FTpjsTvOJvj6MXcExvpO9RagJsYDfhwG970o9t0QJ64upHg2/J/KkEzErz522UXFJ5YrqzJqKBVpTs2/Nj1EoQzqGk81zktTg85Eq+ux/jQJYQKBgQDKNGcOOnys1yCET1DA5I31G0Dlr1b2nyju+h4IawBZcJWazXMiSfoXJ7cejOuEn59GfmsaIk/4Qqo/8BgiAk2etCMc8cxNOHyD1yh5x+P00YkGEGOUtq0goi68xKaONt0RU8HgPI69PhVQrbEFcL/IJv5bQ1rXkiiauVkmY33LFQKBgQCweqyV6Yk1AcT10WQPKXyEJzozp9nJkI+fCvZfQ1fe3XDTlxnQiq1zjOmA0o0unwJn7+DEThzG48KtjG81B1pE0jhEpZNe1NRxObK3vJ6ToZhPXwIrup4qHtDT9YtdGsLpxJhhGkddWqyZiblczq5INj/aKsJ6v2EF2muKP6zKdQKBgEbfpTuUfnyqjsgxcNGbirJd0hpvoeChmtxgqMQ0lRPEZXu0BWYAXftdvU6lACwlMtOnney0GCmhXEE664qIGiLFawVLL1vlr6cadCJzUNBP3WJVvkGy4e3tSsp5dxOj4Uqge/UX5MQqDZiQuNp6UY5bNI5ShnXG0hqhWsEc4EudAoGAfmMsrKQ2zk55Fy8hgLZOZXqOdSYVlARCxz0bmrfo+0QNpItHQpG+jM1zAP5JfAUGvRcPGqdydoHxD0dO8WRVoooHICqQc07q4jfXJT0hOEt3gAhjzpt04Gd3WQU2DLS4uKBCHPCsAVCJ4d+/b5bZPAAWFJGg/DWJL57we28EuW0CgYBQKWSIgoIgxJP/PBjKd8Wg7DjiL7qqffTQSIqHD29/DsyfA6OmKhAkFdRTPiJxLCQ0TtyBcWLcy4Rs3r/FP7jBsen0gVI+ouE6afkX2tC94VDyLTLWX27VBmjPbZjb3IDxfmf6t5cX3AH40uOQxZ2BFXmFYHp7UbtzEUBxwhLm4A==";

	// 请求网关地址
	public static String DEVURL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "utf-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiwaux/v2n0kay820yiHBhMogk/VqL/weA/KNph91yqLbKoWwBH6xaL0AlJUuoC/PV60VHjvv/ACS0ScOoihKQ0CIfQS1AtT+1ZNOyiygXpcj/eE/aAOjrohsIqVr+Z1DQe++lOstSVULLmi6JJRh0bYjaHNcFisSqB85UOjxUgY23eWHP4JhlLxVE8UXHXASPCRnXcM2iAa+wNSSD/HopV5WfurzrTMA4tkDMHmftqb73dZmgLIlqNFxEnQt0t/80BRrfMGMCv2+hGCMHlIc9FNfTRZoMueNWuRxf7sPJi0dGEUdQW+qLo02an85+ZHUg4R2PJ0iNUWdAU0jXk7jIQIDAQAB";
	//public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzkvD/bS/xXY/EkMs0A8INWx0xfrc1loEzV0nI+p0KNJU1AjQ0UtKJk+GhCtY0URZUrtmcjJk8xK6jmxtnsEf6Jt6ELdirXTzi1XaCSS2YPVftBtgDf8b+Xby0qGOuuzf7wtP/17Lc9vpl93GSa3SX5jq1G5V89jpPmvwERIKglx9GMul3hsJu8u+z6bTIIjYUH5izaUuW+M0NwvDi3KPQQlNBDCeazzYLd8mrD/cxuLapyFjyHxsnPfIbG/i7TdEd19z0q8Poo0/Z8RU2rm9Y20V/aa4R4M9/+V9RifzY6wMPENv7+/6TWzKcO9+sIWpKnJomOqv7LYXsZrte8cF+wIDAQAB";
	/*// 日志记录目录
	public static String log_path = "/log";*/
	// RSA2
	public static String SIGNTYPE = "RSA";
	
	public static String phoneNotify_url =  GlobalVariables.domain+"/api/phonePay/notifyVerifica";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String phoneReturn_url =  GlobalVariables.domain+"/api/phonePay/payVerifica";
	
	//---------------------------------------
	//app支付 接受通知的接口名
	public static String appService = "mobile.securitypay.pay";
}

