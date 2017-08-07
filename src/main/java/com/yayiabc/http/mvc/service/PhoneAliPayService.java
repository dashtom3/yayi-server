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
public interface PhoneAliPayService {

	//打包参数
	String packingParameter(
			String WIDout_trade_no,
			String WIDsubject,
			String WIDtotal_fee,
			String WIDbody,
			String product_code
			);
	
	
	
}
