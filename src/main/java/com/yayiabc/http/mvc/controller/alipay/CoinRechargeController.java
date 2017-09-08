package com.yayiabc.http.mvc.controller.alipay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public void recharge(
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="qbNum",required=true)String qbNum,
			@RequestParam(value="qbType",required=true)String qbType,
			@RequestParam(value="computerOrPhone",required=true)String computerOrPhone,
			HttpServletResponse response
			){
		PrintWriter ppp=null;
		try {
			ppp=response.getWriter();
			DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
			//String qbType="a_qb";
			String userId=utilsdao.getUserID(token);
			String codeId=createId(userId);
			double money=QbExchangeUtil.getQbByMoney(Integer.parseInt(qbNum),qbType);
			//非法操作
			if(money==0.0){
				 throw new RuntimeException();
			}
			utilsdao.saveRechargeMessage(codeId,userId,String.valueOf(qbNum),qbType,String.valueOf(Math.round(money)));
			//test  钱币充值  前台传来的钱币类型 为: a_qb b_qb ,c_qb
	        if(computerOrPhone.equals("computer")){
	        	//调用PC网页支付宝.
	    		String sHtmlText=alipay.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币");
	    		
	    			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
	    			ppp.write(sHtmlText);
	    			
	        }else if(computerOrPhone.equals("phone")){
	        	//调用移动端 支付宝.
	        	dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
	        	String product_code="QUICK_WAP_PAY";
	    		String sHtmlText=phoneAliPayService.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币",product_code);
	    		
	    		ppp.write(sHtmlText);
	    		
	        }else if(computerOrPhone.equals("app")){
	        	dataWrapper.setMsg("参数已过时");
	        	//return appPayService.packingParameter(codeId, codeId, String.valueOf(money), "乾币充值", "乾币", "QUICK_MSECURITY_PAY");
	        }else{
	        	dataWrapper.setMsg("参数异常");
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(ppp != null){
				ppp.flush(); 
                ppp.close();
            }
		}
	}
	//app充值钱币
	@RequestMapping("appRecharge")
	@ResponseBody
	public DataWrapper<Object> appRecharge(
			@RequestHeader(value="token",required=true)String token,
			@RequestParam(value="qbNum",required=true)String qbNum,
			@RequestParam(value="qbType",required=true)String qbType
			){
		String userId=utilsdao.getUserID(token);
		String codeId=createId(userId);
		double money=QbExchangeUtil.getQbByMoney(Integer.parseInt(qbNum),qbType);
		int sign=utilsdao.saveRechargeMessage(codeId,userId,String.valueOf(qbNum),qbType,String.valueOf(Math.round(money)));
		if(sign>0){
		return appPayService.packingParameter(codeId, codeId, String.valueOf(money), "乾币充值", "乾币", "QUICK_MSECURITY_PAY");
		}
		return null;
	}
	//生成 id的方法
	String createId(String USERiD){
		String codeId=("zfb"+USERiD.substring(0,5)+System.currentTimeMillis());
		return codeId;
	}
}
