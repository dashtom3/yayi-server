package com.yayiabc.http.mvc.controller.saleManage;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.SaleMyWalletDetail;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.MyWalletService;
import com.yayiabc.http.mvc.service.WitManageService;

@Controller
@RequestMapping("api/myWallet")
public class MyWalletController {
	@Autowired
	private MyWalletService myWalletService;
	@Autowired
	private WitManageService witManageService;
	
	
	/*//获取钱包余额
	@RequestMapping("getBalance")
	@ResponseBody
	public DataWrapper<Void> getBalance(@RequestHeader String token){
		return myWalletService.getBalance(token);
	}
	
	//获取进账总额
	@RequestMapping("getAllIn")
	@ResponseBody
	public DataWrapper<Void> getAllIn(@RequestHeader String token){
		return myWalletService.getAllIn(token);
	}
	
	//获取出账总额
	@RequestMapping("getAllOut")
	@ResponseBody
	public DataWrapper<Void> getAllOut(@RequestHeader String token){
		return myWalletService.getAllOut(token);
	}
	
	//钱包明细,1表示全部 2表示进账,3表示出账 
	@RequestMapping("detail")
	@ResponseBody
	public DataWrapper<List<Balance>> detail(
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="state",required=false)Integer state,
			@RequestParam(value="starTime",required=false)String starTime,
			@RequestParam(value="endTime",required=false)String endTime
			){
		if("".equals(starTime)){
			starTime=null;
		}
		if("".equals(endTime)){
			endTime=null;
		}
		return myWalletService.myWalletDetails(token,state,starTime,endTime);
	}

	
	@RequestMapping("viewDetail")
	@ResponseBody
	public DataWrapper<List<SaleMyWalletDetail>> viewDetail(
			@RequestParam(value="token",required=true)String token,
			@RequestParam(value="balanceId",required=true) Integer balanceId
			){
		return myWalletService.viewDetail(balanceId);
	}

	//提现入口
	@RequestMapping("showWit")
	@ResponseBody
	public  DataWrapper<With> showWit(
			@RequestParam(value="token") String token
			){
		return witManageService.showWit(token);
	}
	//提交提现申请
	@RequestMapping("submitWit")
	@ResponseBody//real_name,type,anumber,cashMoney,phone
	public  DataWrapper<Void> submitWit(
			@RequestParam(value="token") String token,
			@RequestParam(value="vCode") String vCode,
			 @RequestParam(value="phone") String phone,
   		 @RequestParam(value="moneyNnm") String moneyNnm,
			real_name  type   anumber
   		 @RequestParam(value="phone") String phone,
   		 @RequestParam(value="vCode") String vCode,
   		 @RequestParam(value="type") String type
			@ModelAttribute With with
			){

		if(vCode.equals(VerifyCodeManager.getPhoneCode(with.getPhone()))){
			//return witManageService.submitWit(with,token);
		}
		DataWrapper<Void> dataWrapper= new DataWrapper<Void>();
		dataWrapper.setMsg("验证验证码失败");
		return dataWrapper;
	}
	//查看操作 的订单详情
	@RequestMapping("queryOrder")
	@ResponseBody//real_name,type,anumber,cashMoney,phone
	public  DataWrapper<SaleInfo> queryOrder(
				 @RequestParam(value="orderId") String orderId,
				 @RequestParam(value="token") String token
				 ){
		return 	 myWalletService.queryOrder(orderId,token);
			
		  }*/
	@RequestMapping("detail")
	@ResponseBody
	public  void detail(@RequestParam(value="token",required=true)String token,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="starTime",required=false)String starTime,
			@RequestParam(value="endTime",required=false)String endTime){
		   HashMap<String, String> hm=new HashMap<String,String>();
		   hm.put("token", token);
		   hm.put("state", state);
		   hm.put("starTime", starTime);
		   hm.put("endTime", endTime);
		myWalletService.detail(hm);
	}
}
