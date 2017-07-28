package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.UserStatistics;
import com.yayiabc.http.mvc.service.SaleMyClientService;

@Controller
@RequestMapping("api/saleMyClient")
public class SaleMyClientController {
	@Autowired
	SaleMyClientService saleMyClientService;
	
	/**
	 * 我的客户
	 */
	@RequestMapping(value="myClient",method=RequestMethod.GET)
	@ResponseBody
	@SaleTokenValidate(description="销售员查询我的客户")
	public DataWrapper<List<UserStatistics>> myClient(
			@RequestParam(value="value",required=false)String value,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
    		@RequestHeader(value="saleToken",required=true)String token
	){
		return saleMyClientService.myClient(value, token, currentPage, numberPerPage);
	}
}
