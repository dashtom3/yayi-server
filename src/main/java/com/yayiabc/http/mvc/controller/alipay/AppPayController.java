package com.yayiabc.http.mvc.controller.alipay;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.AppPayService;

@Controller
@RequestMapping("api/appPay")
public class AppPayController {

	@Autowired
	private AliPayService alipayService;

	@Autowired
	private AliPayDao alipayDao;
	@Autowired 
	private AppPayService appPayService;

	@ResponseBody
	@RequestMapping("callBack")
	public void aliPay_notify(
			HttpServletRequest request,
			HttpServletResponse response
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
			//乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。   alipay.trade.app.pay
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		PrintWriter printWriter=null;
		try {
			printWriter =response.getWriter();
			boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,  "utf-8", "RSA2");
			if(flag){
				if("TRADE_SUCCESS".equals(params.get("trade_status"))){
					//付款金额
					String amount = params.get("buyer_pay_amount");
					//商户订单号
					String out_trade_no = params.get("out_trade_no");
					//支付宝交易号
					//String trade_no = params.get("trade_no");
					//附加数据
					//String passback_params = URLDecoder.decode(params.get("passback_params"));

					//SecurityVerification 安全检查方法  0代表支付宝支付
					PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
					if( payAfterOrderUtil.SecurityVerification(out_trade_no,amount,"0")){
						System.out.println("成功啦");
						printWriter.write("success");
					}else{
						System.out.println("失败啦");
						printWriter.write("fail");
					}
				}
			}else{
				printWriter.write("success");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
	}
	//app支付
	@ResponseBody
	@RequestMapping("appPay")
	public DataWrapper<Object> apppay(String orderId){
		HashMap<String , String> hm=alipayService.queryY(orderId);
		return appPayService.packingParameter(orderId,hm.get("WIDout_trade_no"),hm.get("WIDtotal_fee"),hm.get("WIDbody"),hm.get("WIDsubject")
				,"QUICK_MSECURITY_PAY"
				);
	}
	//轮询接口
	@RequestMapping("polling")
	@ResponseBody
	public DataWrapper<Void> polling(
			@RequestParam(value="sign",required=true) String sign,
			@RequestParam(value="id",required=true) String id
			){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		if("orderPay".equals(sign)){
			int ss= alipayDao.querySatetIsTwo(id);
			if(ss==2){
				dataWrapper.setMsg("成功");
			}else{
				dataWrapper.setMsg("失败");
			}
		}else if("coinPay".equals(sign)){
			int ss= alipayDao.queryChargeIsTwo(id);
			if(ss==2){
				dataWrapper.setMsg("成功");
			}else{
				dataWrapper.setMsg("失败");
			}
		}else{
			dataWrapper.setMsg("NONONO");
		}
		return dataWrapper;
	}
}
