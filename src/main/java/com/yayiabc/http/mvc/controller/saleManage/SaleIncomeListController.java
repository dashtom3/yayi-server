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
	 * 收入列表
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleIncomeVo>> query(
			@RequestParam(value="saleId",required=false)String saleId,
			@RequestParam(value="saleName",required=false)String saleName,
			@RequestParam(value="salePhone",required=false)String salePhone,
			@RequestParam(value="orderId",required=false)String orderId,
			@RequestParam(value="signLateSeven",required=false)Integer signLateSeven,
			@RequestParam(value="getState",required=false)Integer getState,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
	){
		return saleIncomeListService.query(saleId, saleName, salePhone, orderId, signLateSeven, getState, startDate, endDate, currentPage, numberPerPage);
	}
	
	/**
	 * 收入详情
	 */
	@RequestMapping(value="detail",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<SaleIncomeVo> detail(
			@RequestParam(value="saleId",required=true)String saleId,
			@RequestParam(value="userId",required=true)String userId,
			@RequestParam(value="orderId",required=true)String orderId
	){
		return saleIncomeListService.detail(saleId, userId, orderId);
	}
}
