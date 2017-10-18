package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.alipayenclos.util.AlipayNotify;
import com.yayiabc.common.alipayenclos.util.AlipaySubmit;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class AliPayServiceImpl implements AliPayService{
	@Autowired
	private AliPayDao aliPayDao;
	@Autowired
	private UserMyQbService userMyQbService;
	@Autowired
	private UtilsDao utilsDao;
	
	@Override
	public String packingParameter(String WIDout_trade_no, String WIDsubject, String WIDtotal_fee, String WIDbody) {
		// TODO Auto-generated method stub
		try {
			//订单编号
			/*String out_trade_no = new String(WIDout_trade_no.getBytes("ISO-8859-1"),"UTF-8");
			//订单名称，必填
			//String subject = new String(WIDsubject.getBytes("ISO-8859-1"),"UTF-8");
			String subject=WIDsubject;

			//付款金额，必填
			String total_fee = new String(WIDtotal_fee.getBytes("ISO-8859-1"),"UTF-8");

			//商品描述，可空
			String body = new String(WIDbody.getBytes("ISO-8859-1"),"UTF-8");*/
			String out_trade_no=WIDout_trade_no;
			String subject=WIDsubject;
			String total_fee=WIDtotal_fee;
			
			//String body=WIDbody;
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", AlipayConfig.service);
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("seller_id", AlipayConfig.seller_id);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", AlipayConfig.payment_type);
			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			sParaTemp.put("return_url", AlipayConfig.return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
			//如sParaTemp.put("参数名","参数值");

			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
			//out.println(sHtmlText);
			return sHtmlText;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 1同步通知是给用户看的
	 * 2异步通知是给服务器看的
	 */
	//支付校验结果同步
	@Override
	@Transactional
	public String ReturnUrl(/*String is_success, String sign_type, String sign, String trade_statuss, String out_trade_nos,
			String trade_nos,*/Map<String,String> params) {
		// TODO Auto-generated method stub
		try {
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号
			String out_trade_no =params.get("out_trade_no"); /*new String(out_trade_nos.getBytes("ISO-8859-1"),"UTF-8");*/

			//支付宝交易号

			String trade_no =params.get("trade_no"); /*new String(trade_nos.getBytes("ISO-8859-1"),"UTF-8");*/

			//交易状态
			String trade_status =params.get("trade_status"); /*new String(trade_statuss.getBytes("ISO-8859-1"),"UTF-8");*/
			//付款金额
            String amount = params.get("buyer_pay_amount");
			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);

			if(verify_result){//验证成功
				//请在这里加上商户的业务逻辑程序代码  明天写

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					/*Ordera order=aliPayDao.queryOrder(out_trade_no);
					System.out.println("orderorderorderorder   "+order);
					System.out.println("amountamountamount   "+amount);
					System.out.println("out_trade_noout_tra      "+out_trade_no);
					if(!amount.equals(order.getActualPay())&&order.getOrderId().equals(out_trade_no)){
						 return "fail";
					}*/
					 return "success";
				}
				//验证成功  支付失败 123

			}else{
				//out.println("验证失败");  
				//转发到验证失败页面
				return "fail";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
		}
		return "fail";
	}
	//支付校验结果异步
	@Override
	public String notifyVerifica(Map<String,String> params) {
		// TODO Auto-generated method stub
		try {
			//商户订单号
			String out_trade_no =params.get("out_trade_no"); /*new String(out_trade_nos.getBytes("ISO-8859-1"),"UTF-8");*/

			//支付宝交易号

			String trade_no =params.get("trade_no"); /*new String(trade_nos.getBytes("ISO-8859-1"),"UTF-8");*/

			//交易状态
			String trade_status =params.get("trade_status"); /*new String(trade_statuss.getBytes("ISO-8859-1"),"UTF-8");*/
			//付款金额
            String amount = params.get("total_fee");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理finished
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					 //安全验证
					  PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
	                   if( payAfterOrderUtil.SecurityVerification(out_trade_no,amount,"0")){
	                	   return "success";
	                   }else{
	                	   return "fail";
	                   }
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					  //安全验证
					   PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
	                   if( payAfterOrderUtil.SecurityVerification(out_trade_no,amount,"0")){
	                	   return "success";
	                   }else{
	                	   return "fail";
	                   }
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					
				}

				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				//notify_url页面只能返回success，异步通知页面上不可有任何HTML代码。支付结果请以异步通知为准。
				//out.print("success");	//请不要修改或删除

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				//out.print("fail");
				return "fail";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();  
		}
		return "fail";
	}

	//根据订单id 查询结账时需要的数据（订单号，商品名称，易交金额）
	@Override
	public HashMap<String , String> queryY(String orderId) {
		// TODO Auto-generated method stub
		HashMap<String , String>  hmHashMap=new HashMap<String,String>();
		//易交金额
		double WIDtotal_fee=aliPayDao.queryYorderIdAndActualMonry(orderId); 	
		//商品名称
		List<String> itemList=aliPayDao.queryYitemNames(orderId);
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<itemList.size();i++){
			if(i<=2){
				sb.append(itemList.get(i));
			}

		}
		sb.append("...");
		//订单留言
		String WIDbody=aliPayDao.queryYorderMessage(orderId);
		if(WIDbody==null){
			WIDbody="此订单无用户留言";
		}
		hmHashMap.put("WIDout_trade_no", orderId);  //
		hmHashMap.put("WIDtotal_fee",String.valueOf(WIDtotal_fee)); //交易金额 WIDtotal_fee
		hmHashMap.put("WIDsubject", sb.toString());  //描述
		hmHashMap.put("WIDbody",WIDbody);    //dingdan留言


		return hmHashMap;
	}
}
