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

import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.CoinRechargeService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Controller
@RequestMapping("api/pay")
public class CoinRechargeController {
	@Autowired
	private UtilsDao utilsdao;
    @Autowired AliPayService alipay;
    @RequestMapping("recharge")
    @ResponseBody
   // @UserTokenValidate(description="开始支付宝充值乾币")
    public void recharge(
    		@RequestParam(value="token",required=true)String token,
    		@RequestParam(value="money",required=true)String qbNum,
    		@RequestParam(value="qbType",required=true)String qbType,
    		 HttpServletResponse response
    		){
    	//String qbType="a_qb";
    	String codeId=createId(token);
    	double money=QbExchangeUtil.getQbByMoney(Integer.parseInt(qbNum),qbType);
    	utilsdao.saveRechargeMessage(codeId,utilsdao.getUserID(token),qbNum,qbType);
    	//test  钱币充值  前台传来的钱币类型 为: a_qb b_qb ,c_qb
    	money=0.01;
    	//调用支付宝
       String sHtmlText=alipay.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币");
    	try {
			response.getWriter().write(sHtmlText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
     //生成 id的方法
     String createId(String token){
    	String userId= utilsdao.getUserID(token);
    	String codeId=("zfb"+userId.substring(0,5)+userId.substring(5)).replace("1", "7")+System.currentTimeMillis();
    	return codeId;
     }
}
