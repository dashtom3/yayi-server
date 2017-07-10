package com.yayiabc.http.mvc.controller.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.common.alipayenclos.util.AlipayNotify;
@Controller
@RequestMapping("api/returnUrl")
public class ReturnUrl {
	@RequestMapping
	void ReturnUrl(
			@RequestParam(value="is_success",required=true) String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			@RequestParam(value="sign_type",required=true) String sign_type,//MD5
			@RequestParam(value="sign",required=true) String sign,//签名与验签
			@RequestParam(value="trade_status",required=true) String trade_status1,//交易状态
			@RequestParam(value="out_trade_no",required=true) String out_trade_no1,
			@RequestParam(value="trade_no",required=true) String trade_no1,
			HttpServletRequest request
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
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no = new String(out_trade_no1.getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(trade_no1.getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(trade_status1.getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);
			if(verify_result){//验证成功
				 
				//请在这里加上商户的业务逻辑程序代码  明天写
				
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					   //判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
				}
				
				//out.println("验证成功<br />");
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{
				//out.println("验证失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
