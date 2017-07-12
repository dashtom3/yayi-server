package com.yayiabc.http.mvc.controller.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConfigImpl;

@Controller
@RequestMapping("api/weixin")
public class WXPayController {
	
	
	/**
	 * 统一下单接口
	 * @return
	 */
	@RequestMapping("unifiedOrderReturnUrl")
	@ResponseBody
	public DataWrapper<Void> unifiedOrderReturnUrl(){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		try {
			WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:8080/api/item/getItemId");
			Map<String,String> reqData =new HashMap<String,String>();
			reqData.put("body", "http://47.93.48.111:85/");//必传
			//必传,商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。可以传参进来
			reqData.put("out_trade_no",System.currentTimeMillis()+"123");//out_trade_no不要传人你自己订单系统的订单号，而是重新生成一个新的唯一流水，这样每次请求提交支付，可以保证out_trade_no不重复。
			reqData.put("total_fee", "1");//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			reqData.put("spbill_create_ip","47.93.48.111");//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//			reqData.put("notify_url","http://47.93.48.111:8080/api/item/getItemId");//通知地址,接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
			reqData.put("trade_type","NATIVE");//必传,现场扫码付
			reqData.put("product_id","714454131");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			System.out.println(reqData);
			Map<String,String> respMap=null;
				respMap=wxPay.unifiedOrder(reqData);
				System.out.println(respMap);
			String urlCode=(String)respMap.get("code_url");
			System.out.println(urlCode);
			dataWrapper.setMsg(urlCode);
		} catch (Exception e) {
			String msg="服务器错误";
			dataWrapper.setMsg(msg);
			e.printStackTrace();
		}
		return dataWrapper;
	}
	
	/**
	 * 接收微信系统发送的回掉的请求，并展示给用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getReturnUrl")
	@ResponseBody
	public DataWrapper<Void> getReturnUrl(HttpServletRequest request,HttpServletResponse response){
		
		return null;
	}
}
