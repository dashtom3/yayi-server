package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.SaleIncomeVo;
import com.yayiabc.http.mvc.service.SaleIncomeListService;

@Controller
@RequestMapping("api/saleIncomeList")
public class SaleIncomeListController {
	@Autowired
	SaleIncomeListService saleIncomeListService;
	
	/**
	 * 收入已结算,销售员业绩列表
	 */
	@RequestMapping("queryDone")
	@ResponseBody
	public DataWrapper<List<SaleIncomeVo>> queryDone(
			@RequestParam(value="saleName",required=false)String saleName,
			@RequestParam(value="salePhone",required=false)String salePhone,
			@RequestParam(value="beYearMonth",required=false)String beYearMonth,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return saleIncomeListService.queryDone(saleName, salePhone, beYearMonth, startDate, endDate, currentPage, numberPerPage);
	}
	
	/**
	 * 收入未结算,销售员业绩列表
	 */
	@RequestMapping("queryNot")
	@ResponseBody
	public DataWrapper<List<SaleIncomeVo>> queryNot(
			@RequestParam(value="saleName",required=false)String saleName,
			@RequestParam(value="salePhone",required=false)String salePhone,
			@RequestParam(value="beYearMonth",required=false)String beYearMonth,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return saleIncomeListService.queryNot(saleName, salePhone, beYearMonth, currentPage, numberPerPage);
	}
	
	/**
	 * 收入详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SaleIncomeVo> detail(
			@RequestParam(value="saleId",required=true)String saleId,
			@RequestParam(value="beYearMonth",required=true)String beYearMonth,
			@RequestParam(value="getState",required=true)String getState,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return saleIncomeListService.detail(saleId, beYearMonth, getState, currentPage, numberPerPage);
	}
}
