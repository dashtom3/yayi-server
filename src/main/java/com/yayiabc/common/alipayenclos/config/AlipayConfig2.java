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

public class AlipayConfig2 {

	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	    //合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	    public static String partner = "2088121334701862";

	    //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRvxFYA40megHgriSG7F1krSTkYGedyXbdzjBXcJ9juvPCmuLtpRBjQ110a4Pv0vD/AUG7GM5/DQEUm05XJq8Zrq/OJ6zHDN0dGsVyv5uvkhdFpDisUhAsZmNL05JnBFU1mgb+RVcDLKP7uUdxxboGu7VZMEyrhc2ig3hJudh495AyOvQQqSC2AFMURpLBa0sfUDpfk0H64vQBfzmRgFpYPLJUNYr5oIux5cPKj6A0XKb7Fz7+0WElibmcQ/tBpwzvCw55sNOEvqHS53Y38kGHm1nuLwOHdHggWiqGXNV9oeWhxjx9k7RsySAtwKFLH7kSHGO7M2PZjYN8EEXpYH5jAgMBAAECggEAVuy8/GBJ3AvieM2D7ZCldvkpSqgQxHO22D00acI63fFPlt5nv9K1vm4cG+ZP70ZBA+YM/Pijl/T6kv65r5Lb1hZwtiTFkIYkjko8Pt72jLU1+gwi3Ouox8JdMIlEBPr5kxmFWDuk1LjYKLbtXHbQJ1nmpAYBhD8uUOHWaSpVaHdf9rrkS4e5AtW5wHjsTmqOOvaRNFVKmEQG/7uiRTUQRJwVOvc7wd4K7En9EOHEBxo+Zhlf2clqSHCnxHIY56QWXbzzIvHeH6t99XwI16zoko8UuKDQ+MZhKEjuoWQ4wnvif262rsFR0mzZSNIiYi/WVuZ4e9AO8VPddyym7WcskQKBgQDC48RlcKU4Sb0U387MkI5osc8dq6yZZTPaB6z3STQEzQqvcM66vi+l+7/+zQ4uEw3sI2qes+ze02ZeRf+JhWZr711Mb3/qnZOYDD0W3DCiKqpb/v1j1O4i3TVegAi12I+oTUMgybIrfixf3vm8cl0N8JyR9BQaaaAm2/d01ciz2wKBgQC/cnQZ1O2x7W74WMYZ28DKGyiuBgWXniyzCFMFm+TKRVsXOw6WRmtU5L2/hbQUngjHKpemAK23LsjLrFd337spzkETlpYAMIFbO3MWCewMamPGyHGK/ZRh1Txynz2V+EjtjiPF5rfX9b2FLK1ypCCUBPiQONtt3phggJ6LJ18qGQKBgBGZO7zlRSYmEnr2EtiKHTH7DHbjb7ySVPJVSxsxcw8T7OkWyLQ4RFk6uIWrwqIKenFeaVxXZwZgFJhivcaPU0Zf4Q7r6qMnA5LxV/ree03JiISyK/N94NspGo6kBSVWnvJVt8AYNoyS4jLuEaMguKJvndPYNJCGBT49EsVsIKmNAoGAO/1pVA7czLxR4ZAvwzqRFsOb46wPZIs0BoW9PiyzbI5/FLB0ybIXP+rNao5C95LtvTsjeekMhjIqyWefoPiCdX8j9TdimXyZjDT8wxMo3FsaGNgGfD8pl95xqpkreyrp70Zy/zhks09Vq71sPFghvUAxgz/NwofVx6eGBqS3tUECgYEAhAiOZl8l4Cx6PqyFe8WCRVzzYL7IEVSHxoKunxyHhIGLL+wQQfuoJyxqYXPjF+sh2iswQYCeNcIWYAW91hjRh4rS5gAlWdQe/poeWAeYbg9ZxwwZltg7HcjsyM4RTgwhpZKP6KjLe5vPDr3p22BGt5plqoB0dFG4H8sC7qTbHjY=";

	    //支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiwaux/v2n0kay820yiHBhMogk/VqL/weA/KNph91yqLbKoWwBH6xaL0AlJUuoC/PV60VHjvv/ACS0ScOoihKQ0CIfQS1AtT+1ZNOyiygXpcj/eE/aAOjrohsIqVr+Z1DQe++lOstSVULLmi6JJRh0bYjaHNcFisSqB85UOjxUgY23eWHP4JhlLxVE8UXHXASPCRnXcM2iAa+wNSSD/HopV5WfurzrTMA4tkDMHmftqb73dZmgLIlqNFxEnQt0t/80BRrfMGMCv2+hGCMHlIc9FNfTRZoMueNWuRxf7sPJi0dGEUdQW+qLo02an85+ZHUg4R2PJ0iNUWdAU0jXk7jIQIDAQAB";

	    // 签名方式
	    public static String sign_type = "RSA2";

	    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	    public static String log_path ="C://";

	    // 字符编码格式 目前支持 gbk 或 utf-8
	    public static String input_charset = "utf-8";

	    // 接收通知的接口名
	    public static String service = "http://47.93.48.111:6181/api/appPay/callBack";
	    //public static String service = "mobile.securitypay.pay";

	    //APPID
	    public static String app_id="2016022401160229";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	}

