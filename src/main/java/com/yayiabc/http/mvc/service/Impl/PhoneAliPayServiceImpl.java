package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.alipayenclos.util.AlipayNotify;
import com.yayiabc.common.alipayenclos.util.AlipaySubmit;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.PhoneAliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class PhoneAliPayServiceImpl implements PhoneAliPayService{

	
	@Override
	public String packingParameter(String out_trade_no, String subject, String total_fee, String WIDbody
			,String product_code
			) {
		// TODO Auto-generated method stub
			
         		
			  // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
		    //调用RSA签名方式
		    AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,"RSA2");
		    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
		    
		    // 封装请求支付信息
		    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
		    model.setOutTradeNo(out_trade_no);
		    model.setSubject(subject);
		    model.setTotalAmount(total_fee);
		    //model.setBody(body);
		    model.setTimeoutExpress("2m");
		    model.setProductCode(product_code);
		    alipay_request.setBizModel(model);
		    // 设置异步通知地址
		    alipay_request.setNotifyUrl(AlipayConfig.phoneNotify_url);
		    // 设置同步地址
		    alipay_request.setReturnUrl(AlipayConfig.phoneReturn_url);   
		    // form表单生产
		    String form = "";
			try {
				// 调用SDK生成表单
				form = client.pageExecute(alipay_request).getBody();
			    return form;
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null;
	}
	/**
	 * 1同步通知是给用户看的
	 * 2异步通知是给服务器看的
	 */
}
