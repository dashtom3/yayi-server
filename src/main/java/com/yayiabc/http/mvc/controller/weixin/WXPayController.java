package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.GetQCode;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConfigImpl;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.WXPayService;
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
@RequestMapping("api/weixin")
public class WXPayController {
	
	@Autowired
	private AliPayService aliPayService;
	
	@Autowired
	private AliPayDao aliPayDao;
	
	@Autowired 
	private WXPayDao wXPayDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMyQbService userMyQbService;

	@Autowired
	private UtilsDao utilsDao;

	@Autowired
	private WXPayService wxPayService;
	
	/**
	 * 下订单付款时统一下单接口
	 * @return
	 */
	@RequestMapping("unifiedOrderReturnUrl")
	@ResponseBody
	
	public void unifiedOrderReturnUrl(
			@RequestParam("orderId") String orderId,HttpServletResponse response,HttpServletRequest request){
		System.out.println("开始处理回调");
		HashMap<String, String> hashMap=aliPayService.queryY(orderId);
		String total_fee=hashMap.get("WIDtotal_fee");//0.01
		Double total=Double.parseDouble(total_fee);
		Integer totalFee=(int)(total*100);
		String body=hashMap.get("WIDsubject");
		try {
			WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:8080/api/weixin/getReturnUrl");
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
			if(totalFee!=null&&!"".equals(totalFee)){
				reqData.put("total_fee",totalFee+"");//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			}else {
				reqData.put("total_fee","1");
			}
			reqData.put("spbill_create_ip","47.93.48.111");//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqData.put("trade_type","NATIVE");//必传,现场扫码付
			reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			System.out.println(reqData);
			Map<String,String> respMap=wxPay.unifiedOrder(reqData);
			System.out.println(respMap);
			String urlCode=(String)respMap.get("code_url");
			GetQCode.getqCode(urlCode, response);
		} catch (Exception e) {
			String msg="服务器错误";
			e.printStackTrace();
		}
	}
	
	/**
	 * 下订单付款完成后接收微信系统发送的回掉的请求
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getReturnUrl")
	@ResponseBody
	public void getReturnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
		wxPayService.callBack(request,response,WXPayEnum.ORDER_PC);
	}
	
	//下订单成功后付款成功后接受前台的校验
	@RequestMapping("checkOrderState")
	@ResponseBody
	public DataWrapper<Void> checkOrderState(String out_trade_no){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Integer num=aliPayDao.querySatetIsTwo(out_trade_no);
		dataWrapper.setNum(num);
		return dataWrapper;
	}
	
	@RequestMapping("unifiedOrderCharge")
	@ResponseBody
	public void unifiedOrderCharge(@RequestParam(value="money",required=true) Integer money,
			@RequestParam("qbType")String qbType,
			@RequestParam(value="token",required=true) String token,
			HttpServletResponse response){
		String chargeId=UUID.randomUUID().toString();
		String[] str=chargeId.split("-");
		chargeId="";
		for (String string : str) {
			chargeId+=string;
		}
		double totalMoney= QbExchangeUtil.getQbByMoney(money,qbType);
		Charge charge=new Charge();
		charge.setChargeId(chargeId);
		charge.setQbNum(money);
		String totalFee=(int)(totalMoney*100)+"";
		charge.setMoney(totalFee);
		charge.setState(1);
		charge.setToken(utilsDao.getUserID(token));
		charge.setQbType(qbType);
		wXPayDao.deleteChargeByToken(utilsDao.getUserID(token));
		wXPayDao.addCharge(charge);
		try {
			WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:8080/api/weixin/getChargeReturnUrl");
			Map<String,String> reqData =new HashMap<String,String>();
			reqData.put("body","乾币充值");//必传
			reqData.put("out_trade_no",chargeId);
			reqData.put("fee_type", "CNY");
			reqData.put("total_fee",totalFee);//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
			reqData.put("spbill_create_ip","47.93.48.111");//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqData.put("trade_type","NATIVE");//必传,现场扫码付
			reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
			System.out.println(reqData);
			Map<String,String> respMap=wxPay.unifiedOrder(reqData);
			System.out.println(respMap);
			String urlCode=(String)respMap.get("code_url");
			GetQCode.getqCode(urlCode, response);
		} catch (Exception e) {
			String msg="服务器错误";
			e.printStackTrace();
		}
	}
	
	/**
	 * 下订单付款完成后接收微信系统发送的回掉的请求，并展示给用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getChargeReturnUrl")
	@ResponseBody
	public void getChargeReturnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
		wxPayService.callBack(request,response,WXPayEnum.QB_PC);
	}
	
	//下订单成功后付款成功后接受前台的校验
	@RequestMapping("checkChargeState")
	@ResponseBody
	public DataWrapper<Void> checkChargeState(
			@RequestParam(value="token",required=true) String token){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String userId=userDao.getUserIdByToken(token);
		Integer num=wXPayDao.getStateByToken(userId);
		dataWrapper.setNum(num);
		return dataWrapper;
	}
}
