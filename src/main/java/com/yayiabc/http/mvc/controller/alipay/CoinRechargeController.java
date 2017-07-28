package com.yayiabc.http.mvc.controller.alipay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.CoinRechargeService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Controller
public class CoinRechargeController {
	@Autowired
	private UtilsDao utilsdao;
    private CoinRechargeService  coinRechargeService;
    @Autowired
	private UserMyQbService userMyQbService;
    @Autowired AliPayService alipay;
    public void recharge(
    		@RequestParam(value="token",required=true)String token,
    		@RequestParam(value="money",required=true)String money
    		){
    	String codeId=createId(token);
    	utilsdao.saveRechargeMessage(codeId,utilsdao.getUserID(token),money);
    	    //调用支付宝
    	alipay.packingParameter(codeId, "购买铅笔", money,"");
    }
     //生成 id的方法
     String createId(String token){
    	String userId= utilsdao.getUserID(token);
    	String codeId=("zfb"+userId.substring(0,5)+userId.substring(5)).replace("1", "7");
    	return codeId;
     }
}
