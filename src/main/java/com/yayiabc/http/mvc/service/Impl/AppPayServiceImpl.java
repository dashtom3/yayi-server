package com.yayiabc.http.mvc.service.Impl;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.AppPayService;
@Service
public class AppPayServiceImpl implements AppPayService {

	@Override
	public  DataWrapper<Object> packingParameter(String orderId, String out_trade_no, String total_fee, String body, String subject,
			String QUICK_MSECURITY_PAY) {
		// TODO Auto-generated method stub
		// 实例化客户端  
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, 
                "json", AlipayConfig.input_charset, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");  
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay  
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();  
          
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。  
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);  
        model.setSubject(subject);  
        model.setOutTradeNo(out_trade_no);  
        model.setTimeoutExpress("30m");  
        model.setTotalAmount(total_fee);  
        model.setProductCode(QUICK_MSECURITY_PAY);  
        request.setBizModel(model);  
        request.setNotifyUrl("http://47.93.48.111:6181/api/appPay/callBack");//回调地址  
        //request.setReturnUrl("http://www.yayiabc.com/center/myMoney");  
        String orderInfo = null; 
        try {  
            //这里和普通的接口调用不同，使用的是sdkExecute  
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);  
            //response.getBody()就是orderString 可以直接给客户端请求，无需再做处理。  
            orderInfo = response.getBody();  
        } catch (AlipayApiException e) {  
            e.printStackTrace();  
        }  
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>(); 
        dataWrapper.setData(orderInfo);
        dataWrapper.setMsg(out_trade_no);
        return dataWrapper; 
	}
}
