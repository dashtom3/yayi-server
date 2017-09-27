package com.yayiabc.http.mvc.controller.alipay;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.AppPayService;
import com.yayiabc.http.mvc.service.PhoneAliPayService;



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
			utilsdao.saveRechargeMessage(codeId,userId,String.valueOf(qbNum),qbType,money+"");
			//test  钱币充值  前台传来的钱币类型 为: a_qb b_qb ,c_qb
			if(computerOrPhone.equals("computer")){
				//调用PC网页支付宝.
				String sHtmlText=alipay.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币");
				ppp.write(sHtmlText);
			}else if(computerOrPhone.equals("phone")){
				//调用移动端 支付宝.
				String product_code="QUICK_WAP_PAY";
				String sHtmlText=phoneAliPayService.packingParameter(codeId, "乾币充值", String.valueOf(money),"乾币",product_code);
				ppp.write(sHtmlText);
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
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="qbNum",required=true)String qbNum,
			@RequestParam(value="qbType",required=true)String qbType
			){
		String userId=utilsdao.getUserID(token);
		String codeId=createId(userId);
		System.out.println("qbType  qbType   qbType   "+qbType);
		
		double money=QbExchangeUtil.getQbByMoney(Integer.parseInt(qbNum),qbType);
		
		System.out.println("money   money   money "+money);
		int sign=utilsdao.saveRechargeMessage(codeId,userId,qbNum,qbType,money+"");
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
