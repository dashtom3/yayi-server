package com.yayiabc.http.mvc.controller.saleManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleInfoService;

@Controller
@RequestMapping("api/saleInfo")
public class SaleInfoController {

	@Autowired
	SaleInfoService saleInfoService;
	
	/**
	 * 编辑销售员个人信息基础资料
	 */
	@RequestMapping(value="updateSale",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateSale(
			@ModelAttribute SaleInfo saleInfo,
			@RequestParam(value = "token", required = true) String token
	){
		return saleInfoService.updateSale(saleInfo, token);
	}
	
	/**
	 * 编辑销售员个人信息提现设置
	 */
	@RequestMapping(value="updatePostal",method=RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updatePostal(
			@RequestParam(value = "postalType", required = true) String postalType,
			@RequestParam(value = "bankName", required = false) String bankName,
			@RequestParam(value = "openName", required = true) String openName,
			@RequestParam(value = "accountNumber", required = true) String accountNumber,
			@RequestParam(value = "token", required = true) String token
	){
		return saleInfoService.updatePostal(postalType, bankName, openName, accountNumber, token);
	}
	
	/**
	 * 查询个人信息
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SaleInfo> query(
			@RequestParam(value = "token", required = true) String token
	){
		return saleInfoService.query(token);
	}
}
