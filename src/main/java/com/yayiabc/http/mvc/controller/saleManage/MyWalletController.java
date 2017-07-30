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

import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
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
	@Autowired
	private UtilsDao u;
	//明细
	@RequestMapping("detail")
	@ResponseBody
	@SaleTokenValidate(description="我的钱包:明细 ")
	public  DataWrapper<List<Balance>>   detail(
			@RequestHeader(value="saleToken",required=true)String saleToken,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="starTime",required=false)String starTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="numberPerpage",required=false)Integer numberPerpage
			){
		HashMap<String, Object> hm=new HashMap<String,Object>();
		
		hm.put("state", state);
		hm.put("starTime", starTime);
		hm.put("endTime", endTime);
		return myWalletService.detail(hm,currentPage,numberPerpage,saleToken);
	}
	//详情
	@RequestMapping("details")
	@ResponseBody
	@SaleTokenValidate(description="我的钱包:详情 ")
	public DataWrapper<Balance>  details(
			@RequestHeader(value="saleToken",required=true)String saleToken,
			@RequestParam(value="balanceId",required=true)String balanceId
			){
		return myWalletService.details(balanceId);
	}
	//详情
		@RequestMapping("detailss")
		@ResponseBody
		@SaleTokenValidate(description="我的钱包:详情 ")
		public DataWrapper<List<Balance>>  detailss(
				@RequestHeader(value="saleToken",required=true)String saleToken,
				@RequestParam(value="currentPage",required=false)Integer currentPage,
				@RequestParam(value="numberPerpage",required=false)Integer numberPerpage
				){
			HashMap<String, Object> hm=new HashMap<String,Object>();
			String saleId=u.getSaleId(saleToken);
			hm.put("saleId", saleId);
			return myWalletService.detailsss(hm,currentPage,numberPerpage);
		}
}
