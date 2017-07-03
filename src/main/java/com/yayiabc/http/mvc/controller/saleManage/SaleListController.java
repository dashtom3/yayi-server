package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleListService;

@Controller
@RequestMapping("api/saleList")
public class SaleListController {
	@Autowired
	SaleListService saleListService;
	

	/**
	 * 销售员列表
	 */
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public DataWrapper<List<SaleInfo>> query(
			@RequestParam(value="saleId",required=true)String saleId,
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="trueName",required=true)String trueName,
			@RequestParam(value="isBindUser",required=true)Integer isBindUser,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="token",required=true)String token
	){
		return saleListService.query(saleId, phone, trueName,isBindUser, currentPage, numberPerPage);
	}
}
