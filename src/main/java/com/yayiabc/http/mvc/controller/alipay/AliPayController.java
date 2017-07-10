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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.http.mvc.service.AliPayService;
/**
 * 
 * @author me
 * 支付入口
 */

@Controller
@RequestMapping("api/pay")
@ResponseBody
public class AliPayController {
	@Autowired
	private AliPayService alipayService;
	//支付satrt
	@RequestMapping("payParame")
	@ResponseBody
	String payParame(
			@RequestParam(value="WIDout_trade_no",required=true) String WIDout_trade_no,//订单号
			@RequestParam(value="WIDout_trade_no",required=true) String WIDsubject,//商品名称
			@RequestParam(value="WIDtotal_fee",required=true) String WIDtotal_fee,//付款金额 
			@RequestParam(value="WIDbody",required=false) String WIDbody//商品描述
			){
		return alipayService.packingParameter(WIDout_trade_no, WIDsubject, WIDtotal_fee, WIDbody);
	}
	//判断订单支付同步跳转
	@ResponseBody
	@RequestMapping("payVerifica")
	void ReturnUrl(
			@RequestParam(value="is_success",required=true) String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			@RequestParam(value="sign_type",required=true) String sign_type,//MD5
			@RequestParam(value="sign",required=true) String sign,//签名与验签
			@RequestParam(value="trade_status",required=true) String trade_status,//交易状态
			@RequestParam(value="out_trade_no",required=true) String out_trade_no,//商户订单号
			@RequestParam(value="trade_no",required=true) String trade_no,//支付宝交易号
			HttpServletRequest request,
			HttpServletResponse response
			//。。。。下面还可以加一些参数 现在暂时不加
			){
		try {
			//获取支付宝GET过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			OutputStream out = response.getOutputStream();  
			response.setContentType("text/html;charset=UTF-8");  
			out.write(alipayService.ReturnUrl(
					is_success, sign_type, sign, trade_status, out_trade_no, trade_no,params).getBytes("UTF-8")
					);//以UTF-8进行编码  

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	////判断订单支付异步跳转
	@ResponseBody
	@RequestMapping("notifyVerifica")
	void notifyVerifica(
			@RequestParam(value="is_success",required=true) String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			@RequestParam(value="sign_type",required=true) String sign_type,//MD5
			@RequestParam(value="sign",required=true) String sign,//签名与验签
			@RequestParam(value="trade_status",required=true) String trade_status,//交易状态
			@RequestParam(value="out_trade_no",required=true) String out_trade_no,//商户订单号
			@RequestParam(value="trade_no",required=true) String trade_no,//支付宝交易号
			HttpServletRequest request,
			HttpServletResponse response
			//同上
			){
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		OutputStream out;
		try {
			out = response.getOutputStream();
			response.setContentType("text/html;charset=UTF-8");  
			out.write(alipayService.notifyVerifica(
					is_success, sign_type, sign, trade_status, out_trade_no, trade_no,params).getBytes("UTF-8")
					);//以UTF-8进行编码  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
}
