package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author me
 *          @RequestParam(value="WIDout_trade_no") String WIDout_trade_no,//订单号
			@RequestParam(value="WIDout_trade_no") String WIDsubject,//商品名称
			@RequestParam(value="WIDtotal_fee") String WIDtotal_fee,//付款金额 
			@RequestParam(value="WIDbody") String WIDbody//商品描述
 */
public interface AliPayService {

	//打包参数
	String packingParameter(String WIDout_trade_no,String WIDsubject,
			String WIDtotal_fee,String WIDbody
			);
	//同步支付校验
	String ReturnUrl(
			/*String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			String sign_type,//MD5
			String sign,//签名与验签
			String trade_status,//交易状态
			String out_trade_no,//商户订单号
			String trade_no,//支付宝交易号
*/			Map<String,String> params//获取支付宝GET过来反馈信息
			);
	//异步支付校验
	String notifyVerifica(String is_success,
			String sign_type, 
			String sign, 
			String trade_status,
			String out_trade_no,
			String trade_no, 
			Map<String, String> params
			);
	HashMap<String , String> queryY(String orderId);
}
