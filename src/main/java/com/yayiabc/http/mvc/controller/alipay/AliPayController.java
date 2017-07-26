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
import org.springframework.web.bind.annotation.RequestMethod;
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
	//支付satrt 测试
	@RequestMapping("payParame")
	@ResponseBody   
	String payParame(
			
			@RequestParam(value="WIDout_trade_no",required=true) String WIDout_trade_no,//订单号
			@RequestParam(value="WIDsubject",required=true) String WIDsubject,//商品名称
			@RequestParam(value="WIDtotal_fee",required=true) String WIDtotal_fee,//付款金额 
			
			@RequestParam(value="WIDbody",required=false) String WIDbody//商品描述
			){
		return alipayService.packingParameter(WIDout_trade_no, WIDsubject, WIDtotal_fee, WIDbody);
	}
	
	// 14.29  点击选择类型确定支付宝支付时
	@RequestMapping("payParames")
	void paParames(
			@RequestParam(value="orderId",required=true) String orderId,//订单号
			 HttpServletResponse response
			){
		HashMap<String , String> hm=alipayService.queryY(orderId);
		
		for(String key:hm.keySet()){
			System.err.println("key: "+key+" "+hm.get(key));
		}
		String sHtmlText=alipayService.packingParameter(hm.get("WIDout_trade_no"), hm.get("WIDsubject"), hm.get("WIDtotal_fee"), hm.get("WIDbody"));
		System.err.println(sHtmlText);
		try {
			//写到页面的自动提交表单数据
			response.getWriter().write(sHtmlText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ?buyer_email=17621302014&buyer_id=2088612734610853&exterface=create_direct_pay_by_user&is_success=T&
	 * notify_id=RqPnCoPT3K9%252Fvwbh3InYwJKFDxNZfxZnZBsWXcEyYjtXMPGMPyDqDXSSKy%
	 * 252Bt7nWcteUB&notify_time=2017-07-13%2016%3A16%3
	 * A17&notify_type=trade_status_sync&out_trade_no=6c3cf294-51bb-471d-ad43-ddcac48fa79b2095&
	 * payment_type=1&seller_email=yzm%40yzmedu.com&seller_id=2088221734067901&
	 * subject=%E6%B5%8B%E8%AF%95%E4%B8%80%E4%B8%8B...
	 * &total_fee=0.01&trade_no=2017071321001004850242776603&
	 * trade_status=TRADE_SUCCESS&sign=3ee973ff791b75533176e7aa1749b56a&sign_type=MD5
	 * @param is_success
	 * @param sign_type
	 * @param sign
	 * @param trade_status
	 * @param out_trade_no
	 * @param trade_no
	 * @param request
	 * @param response
	 */
	//判断订单支付同步跳转
	@RequestMapping("payVerifica")
	void ReturnUrl(
			/*@RequestParam(value="is_success",required=true) String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			@RequestParam(value="sign_type",required=true) String sign_type,//MD5
			@RequestParam(value="sign",required=true) String sign,//签名与验签
			@RequestParam(value="trade_status",required=true) String trade_status,//交易状态
			@RequestParam(value="out_trade_no",required=true) String out_trade_no,//商户订单号
			@RequestParam(value="trade_no",required=true) String trade_no,//支付宝交易号
*/			HttpServletRequest request,
			HttpServletResponse response
			//。。。。下面还可以加一些参数 现在暂时不加
			){
		try {
			/*System.out.println("is_success: "+is_success);
			System.out.println("sign_type: "+sign_type);
			System.out.println("sign: "+sign);
			System.out.println("交易状态trade_status: "+trade_status);
			System.out.println("订单号out_trade_no: "+out_trade_no);
			System.out.println("支付宝交易号trade_no: "+trade_no);*/
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
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				//params.put(name, new String(valueStr).getBytes());
				params.put(name, valueStr);
			}
			System.out.println("已经请求到同步方法内");
			response.setContentType("text/html;charset=UTF-8"); 
			
			String Sign=alipayService.ReturnUrl(
					/*is_success, sign_type, sign, trade_status, out_trade_no, trade_no,*/params);
			System.out.println(Sign);
			if("success".equals(Sign)){
				System.out.println("已经成功  正在跳转");
				response.sendRedirect("http://www.yayiabc.com/paySuccess");
			}else{
				System.out.println("已经失败  正在跳转");
				response.sendRedirect("http://www.yayiabc.com/payFail");
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
			@RequestParam(value="is_success",required=true) String is_success,//表示接口调用是否成功，并不表明业务处理结果。
			
			@RequestParam(value="sign_type",required=true) String sign_type,//MD5
			@RequestParam(value="sign",required=true) String sign,//签名与验签
			@RequestParam(value="trade_status",required=false) String trade_status,//交易状态
			@RequestParam(value="out_trade_no",required=false) String out_trade_no,//商户订单号
			@RequestParam(value="trade_no",required=false) String trade_no,//支付宝交易号
			
			@RequestParam(value="notify_time",required=true) String notify_time,
			@RequestParam(value="notify_type",required=true) String notify_type,//
			@RequestParam(value="notify_id",required=true) String notify_id,//
			HttpServletRequest request,
			HttpServletResponse response
			//同上
			){
		System.out.println("456as45d54asda6da4s86d486464649648646845555555555555555555555555555555555554989749461619849848644yk46u8k46uk43y1k35y4k45u4k56k46y4ky4kyu4k");
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
					);//以UTF-8进行编码  0
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
}
