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
	
	
	
	//tets
	@RequestMapping("test")
	void lala(@RequestParam(value="orderId",required=true) String orderId){
			for(String key:alipayService.queryY(orderId).keySet()){
				System.out.println(key+"    "+alipayService.queryY(orderId).get(key));
			}
	}
	// 14.29  点击选择类型确定支付宝支付时
	@RequestMapping("payParames")
	void paParames(
			@RequestParam(value="orderId",required=true) String orderId,//订单号
			 HttpServletResponse response
			){
		 if(orderId==null){
			 return;
		 }
		HashMap<String , String> hm=alipayService.queryY(orderId);
		String sHtmlText=alipayService.packingParameter(hm.get("WIDout_trade_no"), hm.get("WIDsubject"), hm.get("WIDtotal_fee"), hm.get("WIDbody"));
		try {
			//写到页面的自动提交表单数据
			response.getWriter().write(sHtmlText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//判断订单支付同步跳转
	@RequestMapping("payVerifica")
	void ReturnUrl(
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
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				//params.put(name, new String(valueStr).getBytes());
				params.put(name, valueStr);
			}
			
			String Sign=alipayService.ReturnUrl(params);
			if("successQB".equals(Sign)){
				response.sendRedirect("http://123.56.220.72:6644/center/myMoney");
			}
			if("success".equals(Sign)){
				response.sendRedirect("http://123.56.220.72:6644/paySuccess");
			}else{
				response.sendRedirect("http://123.56.220.72:6644/payFail");
			}
			/*out.write(
					);//以UTF-8进行编码  
*/
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	////判断订单支付异步跳转
	@RequestMapping("notifyVerifica")
	void notifyVerifica(
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
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(alipayService.notifyVerifica(
					params).getBytes()
					);//以UTF-8进行编码  0
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

	}
}
