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
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
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
@RequestMapping("api/downOrder")
@ResponseBody
public class DownLoadUrl {

	@RequestMapping("down")
	@ResponseBody
	String query(
			HttpServletRequest request
			){
		try {
			if(request.getParameter("WIDbill_type")!=null&&request.getParameter("WIDbill_date")!=null){
				// 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
				// trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
				String bill_type = new String(request.getParameter("WIDbill_type"));
				// 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
				String bill_date = new String(request.getParameter("WIDbill_date"));
				/**********************/
				// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
				 AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
				AlipayDataDataserviceBillDownloadurlQueryRequest alipay_request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
				
				AlipayDataDataserviceBillDownloadurlQueryModel model =new AlipayDataDataserviceBillDownloadurlQueryModel();
				model.setBillType(bill_type);
				model.setBillDate(bill_date);
			    alipay_request.setBizModel(model);
			    
				AlipayDataDataserviceBillDownloadurlQueryResponse alipay_response = client.execute(alipay_request);
				System.out.println(alipay_response.getBillDownloadUrl());
				return alipay_response.getBillDownloadUrl();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
