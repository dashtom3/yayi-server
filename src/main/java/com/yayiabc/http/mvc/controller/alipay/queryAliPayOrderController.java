package com.yayiabc.http.mvc.controller.alipay;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
/**
 * 
 * @author me
 * 支付入口
 */

@Controller
@RequestMapping("api/queryPay")
@ResponseBody
public class queryAliPayOrderController {
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
				AlipayClient client = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,"RSA2");
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
