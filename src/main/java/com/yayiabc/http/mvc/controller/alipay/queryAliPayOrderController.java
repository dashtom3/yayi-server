package com.yayiabc.http.mvc.controller.alipay;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.http.mvc.service.AliPayService;
/**
 * 
 * @author me
 * 支付入口
 */

@Controller
@RequestMapping("api/queryPay")
@ResponseBody
public class queryAliPayOrderController {
	@Autowired
	private AliPayService alipayService;
	@RequestMapping("query")
	@ResponseBody
	String query(
			HttpServletRequest request
			){
		try {
			if(request.getParameter("WIDout_trade_no")!=null||request.getParameter("WIDtrade_no")!=null){
				//商户订单号，商户网站订单系统中唯一订单号，必填
				String out_trade_no = new String(request.getParameter("WIDout_trade_no"));
				//支付宝交易号
				String trade_no = new String(request.getParameter("WIDtrade_no"));
				/**********************/
				// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
				AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
				AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();

				AlipayTradeQueryModel model=new AlipayTradeQueryModel();
				model.setOutTradeNo(out_trade_no);
				model.setTradeNo(trade_no);
				alipay_request.setBizModel(model);

				AlipayTradeQueryResponse alipay_response =client.execute(alipay_request);
				return alipay_response.getBody();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
