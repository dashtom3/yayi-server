package com.yayiabc.http.mvc.controller.adminstrator;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.TokenValidate;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Benefit;
import com.yayiabc.http.mvc.pojo.jpa.BenefitDetail;
import com.yayiabc.http.mvc.service.BenefitChangeService;

@Controller
@RequestMapping("api/benefit")
public class BenefitChangeController {
	@Autowired
	private BenefitChangeService benefitChangeService;
	
	//添加优惠码
	@RequestMapping("add")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<Void> add(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="benefitName",required=true) String benefitName,
			@RequestParam(value="benefitQb",required=true) Integer benefitQb,
			@RequestParam(value="benefitNum",required=true) Integer benefitNum,
			@RequestParam(value="updated",required=true) String updated
			){
		return benefitChangeService.add(benefitName,benefitQb,benefitNum,updated);
	}
	
	//使用优惠码
	@RequestMapping("use")
	@ResponseBody
	@UserTokenValidate
	public DataWrapper<Void> use(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="benefitCode",required=true) String benefitCode
			){
		return benefitChangeService.use(token,benefitCode);
	}
	
	//查询优惠码
	@RequestMapping("list")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<List<Benefit>> list(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="benefitName",required=false) String benefitName,
			@RequestParam(value="enable",required=true) Integer enable,//1代表可用2代表不可用
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		
		return benefitChangeService.list(benefitName,enable,currentPage,numberPerPage);
	}
	
	//查询优惠码详情
	@RequestMapping("detail")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<List<BenefitDetail>> detail(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="benefitId",required=true) Integer benefitId,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return benefitChangeService.detail(benefitId,currentPage,numberPerPage);
	}
	
	//下载未兑换的优惠码
	@RequestMapping("downLoad")
	@ResponseBody
	public DataWrapper<Void> downLoad(
			@RequestParam(value="benefitId",required=true) Integer benefitId,
			HttpServletResponse response
			){
		return benefitChangeService.downLoad(benefitId,response);
	}
}
