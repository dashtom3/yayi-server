package com.yayiabc.http.mvc.controller.alipay;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.enums.CallStatusEnum;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.AppPayService;
import com.yayiabc.http.mvc.service.CoinRechargeService;
import com.yayiabc.http.mvc.service.PhoneAliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;

import okhttp3.internal.framed.ErrorCode;

@Controller
@RequestMapping("api/pay")
public class CoinRechargeController {
	@Autowired
	private UtilsDao utilsdao;
	@Autowired AliPayService alipay;
	@Autowired PhoneAliPayService phoneAliPayService;
	@Autowired
	private AppPayService appPayService;
	@RequestMapping("recharge")
	@ResponseBody
	// @UserTokenValidate(description="开始支付宝充值乾币")
	public DataWrapper<Object> recharge(
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="qbNum",required=true)String qbNum,
			@RequestParam(value="qbType",required=true)String qbType,
			@RequestParam(value="computerOrPhone",required=true)String computerOrPhone,
			HttpServletResponse response
			){
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		//String qbType="a_qb";
		String codeId=createId(token);
		double money=QbExchangeUtil.getQbByMoney(Integer.parseInt(qbNum),qbType);
		utilsdao.saveRechargeMessage(codeId,utilsdao.getUserID(token),String.valueOf(qbNum),qbType,String.valueOf(money));
		//test  钱币充值  前台传来的钱币类型 为: a_qb b_qb ,c_qb
        if(computerOrPhone.equals("computer")){
        	//调用PC网页支付宝.
    		String sHtmlText=alipay.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币");
    		try {
    			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
    			response.getWriter().write(sHtmlText);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else if(computerOrPhone.equals("phone")){
        	//调用移动端 支付宝.
        	dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        	String product_code="QUICK_WAP_PAY";
    		String sHtmlText=phoneAliPayService.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币",product_code);
    		try {
    			response.getWriter().write(sHtmlText);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else if(computerOrPhone.equals("app")){
        	 //app充值钱币
        	/**
        	 * String orderId, String out_trade_no, String total_fee, String body, String subject,
			   String QUICK_MSECURITY_PAY
        	 */
        	System.out.println("33333333333333333333333333333333");
        	return appPayService.packingParameter(codeId, codeId, String.valueOf(money), "乾币充值", "乾币", "QUICK_MSECURITY_PAY");
        }else{
        	dataWrapper.setMsg("参数异常");
        }
        return dataWrapper;
	}
	//生成 id的方法
	String createId(String token){
		String userId= utilsdao.getUserID(token);
		String codeId=("zfb"+userId.substring(0,5)+userId.substring(5)).replace("1", "7")+System.currentTimeMillis();
		return codeId;
	}
}
