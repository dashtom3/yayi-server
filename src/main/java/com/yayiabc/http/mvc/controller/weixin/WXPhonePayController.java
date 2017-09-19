package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConfigImpl;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("api/weixinPhone")
public class WXPhonePayController {
	
	@Autowired
	private AliPayService aliPayService;
	
	@Autowired
	private WXPayDao wXPayDao;
	
	@Autowired
	private AliPayDao aliPayDao;

	@Autowired
	private UtilsDao utilsDao;
	
	@RequestMapping("phoneBuy")
	@ResponseBody
	public DataWrapper<Void> phoneBuy(
			@RequestParam("orderId") String orderId,
			HttpServletRequest request,
			HttpServletResponse response
			){
		HashMap<String, String> hashMap=aliPayService.queryY(orderId);
		String total_fee=hashMap.get("WIDtotal_fee");//0.01
		Double total=Double.parseDouble(total_fee);
		Integer totalFee=(int)(total*100);
		String body=hashMap.get("WIDsubject");
		try {
			WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/weixin/getReturnUrl");
			Map<String,String> reqData =new HashMap<String,String>();
			if(body!=null&&!"".equals(body)){
				reqData.put("body",body);//必传
			}else{
				reqData.put("body", "商品名称");
			}
			wXPayDao.deleteOrderRecord(orderId);
			String outTradeNo=UUID.randomUUID().toString();
			String[] str=outTradeNo.split("-");
			outTradeNo="";
			for (String string : str) {
				outTradeNo+=string;
			}
			wXPayDao.addOrderRecord(orderId,outTradeNo);
			reqData.put("out_trade_no",outTradeNo);
			reqData.put("fee_type", "CNY");
			//获取终端ip
			reqData.put("spbill_create_ip",request.getRemoteAddr());
			if(totalFee!=null&&!"".equals(totalFee)){
				reqData.put("total_fee",totalFee+"");//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			}else {
				reqData.put("total_fee","1");
			}
			//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqData.put("trade_type","MWEB");//必传,H5支付
			reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			reqData.put("scene_info","{'h5_info': {'type':'Wap','wap_url': 'https://pay.qq.com','wap_name': '牙医abc商品购买'}}" );//场景描述
			System.out.println(reqData);
			Map<String,String> respMap=wxPay.unifiedOrder(reqData);
			System.out.println(respMap);
			System.out.println(respMap.get("mweb_url"));
			response.addHeader("location",respMap.get("mweb_url"));
			response.setStatus(302);
		} catch (Exception e) {
			String msg="服务器错误";
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("unifiedOrderCharge")
	@ResponseBody
	public void unifiedOrderCharge(@RequestParam(value="money",required=true) Integer money,
			@RequestParam(value="token",required=true) String token,
			@RequestParam(value="qbType",required = true)String qbType,
			HttpServletRequest request,
			HttpServletResponse response){
		String chargeId=UUID.randomUUID().toString();
		String[] str=chargeId.split("-");
		for (String string : str) {
			chargeId+=string;
		}
		double totalMoney= QbExchangeUtil.getQbByMoney(money,qbType);
		Charge charge=new Charge();
		charge.setChargeId(chargeId);
		charge.setQbNum(money);
		String totalFee=(int)(Math.round(totalMoney)*100)+"";
		charge.setMoney(totalFee);
		charge.setState(1);
		charge.setToken(utilsDao.getUserID(token));
		charge.setQbType(qbType);
		wXPayDao.deleteChargeByToken(utilsDao.getUserID(token));
		wXPayDao.addCharge(charge);
		try {
			WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/weixin/getChargeReturnUrl");
			Map<String,String> reqData =new HashMap<String,String>();
			reqData.put("body","乾币充值");//必传
			reqData.put("out_trade_no",chargeId);
			reqData.put("fee_type", "CNY");
//			reqData.put("total_fee",totalFee);//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			reqData.put("spbill_create_ip","47.93.48.111");//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqData.put("out_trade_no",chargeId);
			reqData.put("fee_type", "CNY");
			//获取终端ip
			reqData.put("spbill_create_ip",request.getRemoteAddr());
			if(totalFee!=null&&!"".equals(totalFee)){
				reqData.put("total_fee",totalFee);//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			}else {
				reqData.put("total_fee","1");
			}
			//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqData.put("trade_type","MWEB");//必传,H5支付
			reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			reqData.put("scene_info","{'h5_info': {'type':'Wap','wap_url': 'https://pay.qq.com','wap_name': '乾币充值'}}" );//场景描述
			Map<String,String> respMap=wxPay.unifiedOrder(reqData);
			response.addHeader("location",respMap.get("mweb_url"));
			response.setStatus(302);
		} catch (Exception e) {
			String msg="服务器错误";
			e.printStackTrace();
		}
	}
	
	
	
	
}
