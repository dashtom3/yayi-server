package com.yayiabc.http.mvc.controller.alipay;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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

import com.alipay.api.internal.util.AlipaySignature;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.PhoneAliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
/**
 * 
 * @author me
 * 支付入口
 */

@Controller
@RequestMapping("api/phonePay")
@ResponseBody
public class PhoneAliPayController {
	@Autowired
	private PhoneAliPayService phoneAlipayService;
	@Autowired
	private AliPayDao aliPayDao;
	@Autowired
	private AliPayService alipayService;
	@Autowired
	private UserMyQbService userMyQbService;
	@Autowired
	private UtilsDao utilsDao;
	// 14.29  点击选择类型确定支付宝支付时(手机网站)
	@RequestMapping("PhonePayParames")
	void PhonePayParames(
			@RequestParam(value="orderId",required=true) String orderId,//订单号
			 HttpServletResponse response
			){
		HashMap<String , String> hm=alipayService.queryY(orderId);
		
		
		String product_code="QUICK_WAP_PAY";
		String sHtmlText=phoneAlipayService.packingParameter(hm.get("WIDout_trade_no"), hm.get("WIDsubject"), hm.get("WIDtotal_fee"), hm.get("WIDbody")
				,product_code
				);
		System.err.println(sHtmlText);
		try {
			//写到页面的自动提交表单数据
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
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
				/*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
				params.put(name, valueStr);
			}
			
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no"));

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no"));
			for(String key:params.keySet()){
				System.out.println("key: "+key+ " , value:"+params.get(key));
			}
			boolean Sign=AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		
			if(Sign){
				System.out.println("已经成功  正在跳转");
				response.sendRedirect("http://www.yayiabc.com/paySuccess");
			}else{				System.out.println("已经失败  正在跳转");
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
			
			HttpServletRequest request,
			HttpServletResponse response
			//同上
			){
		
		 try {
			  OutputStream out = response.getOutputStream();
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
				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
					//商户订单号

					String out_trade_no = new String(request.getParameter("out_trade_no"));
					//支付宝交易号

					String trade_no = new String(request.getParameter("trade_no"));

					//交易状态
					String trade_status = new String(request.getParameter("trade_status"));

					//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
					//计算得出通知验证结果
					//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
					boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA");
					if(verify_result){//验证成功
						//////////////////////////////////////////////////////////////////////////////////////////
						//请在这里加上商户的业务逻辑程序代码

						//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
						
						if(trade_status.equals("TRADE_FINISHED")){
							//判断该笔订单是否在商户网站中已经做过处理
								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
								//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
								//如果有做过处理，不执行商户的业务程序
							if("zfb".equals(out_trade_no.substring(0, 3))){
								aliPayDao.updateState(out_trade_no);
								Charge charge=aliPayDao.queryUserId(out_trade_no);
								String token=utilsDao.getToken(charge.getToken());
								QbRecord q=new QbRecord();
								q.setQbRget(charge.getMoney());
								q.setQbType(charge.getQbType());
								q.setRemark(charge.getQbType()+"乾币充值(支付宝)");
								userMyQbService.add(q, token);
								((PrintStream) out).println("success");
							}
							int  state=aliPayDao.querySatetIsTwo(out_trade_no);
							if(2!=state){
								PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
								payAfterOrderUtil.universal(out_trade_no,"0");
								((PrintStream) out).println("success");
							}
							//注意：
							//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
							//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
						} else if (trade_status.equals("TRADE_SUCCESS")){
							//判断该笔订单是否在商户网站中已经做过处理
								//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
								//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
								//如果有做过处理，不执行商户的业务程序
							if("zfb".equals(out_trade_no.substring(0, 3))){
								aliPayDao.updateState(out_trade_no);
								Charge charge=aliPayDao.queryUserId(out_trade_no);
								String token=utilsDao.getToken(charge.getToken());
								QbRecord q=new QbRecord();
								q.setQbRget(charge.getMoney());
								q.setQbType(charge.getQbType());
								q.setRemark(charge.getQbType()+"乾币充值(支付宝)");
								userMyQbService.add(q, token);
								((PrintStream) out).println("success");
							}
							int  state=aliPayDao.querySatetIsTwo(out_trade_no);
							if(2!=state){
								PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
								payAfterOrderUtil.universal(out_trade_no,"0");
								((PrintStream) out).println("success");
							}
							//注意：
							//如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
						}else{
							((PrintStream) out).println("fail");
						}

						//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
						
						/*((Map<String, String>) out).clear();*/
						//((PrintStream) out).println("success");	//请不要修改或删除

						//////////////////////////////////////////////////////////////////////////////////////////
					}else{//验证失败
						((PrintStream) out).println("fail");
					}	 
		} catch (Exception e) {
			throw  new RuntimeException(e);
		}
}
	}
