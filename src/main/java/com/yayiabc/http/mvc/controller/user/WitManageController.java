package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.WitManageService;







@Controller
@RequestMapping("api/witManage")
public class WitManageController {
	@Autowired
	//显示提现
	private WitManageService witManageService;

	//提交提现申请
	@RequestMapping("submitWit")
	@ResponseBody//real_name,type,anumber,cashMoney,phone
	@SaleTokenValidate(description="提交提现申请")
	public  DataWrapper<Void> submitWit(
			@RequestHeader(value="saleToken") String saleToken,
			@RequestParam(value="vCode") String vCode,
			/* @RequestParam(value="phone") String phone,
    		 @RequestParam(value="moneyNnm") String moneyNnm,*/
			/*real_name  type   anumber
    		 @RequestParam(value="phone") String phone,
    		 @RequestParam(value="vCode") String vCode,
    		 @RequestParam(value="type") String type*/
			// @ModelAttribute With with
			@RequestParam(value="moneyNnm") String moneyNnm,
			@RequestParam(value="phone") String phone
			){

		if(vCode.equals(VerifyCodeManager.getPhoneCode(phone))){

			return witManageService.submitWit(saleToken,moneyNnm);
		}
		DataWrapper<Void> dataWrapper= new DataWrapper<Void>();
		dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		dataWrapper.setMsg("验证验证码失败");
		return dataWrapper;
	}

	//获取验证码
	@ResponseBody
	@RequestMapping("gitVcode")
	@SaleTokenValidate(description="获取验证码")
	public  DataWrapper<String> gitVcode(
			@RequestHeader(value="saleToken") String saleToken,
			@RequestParam(value="phone") String phone
			){
		return getVerifyCode(phone);
	}

	public DataWrapper<String> getVerifyCode(String phone) {
		String code = VerifyCodeManager.newPhoneCode(phone);
		System.out.println(code);
		DataWrapper<String>  dataWrapper=new DataWrapper<String>();
		if (code == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setData(code);
			return dataWrapper;
		}
		System.out.println(phone);
		boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
		if (result){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			return dataWrapper;
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			return dataWrapper;
		}
	}
	//
	@RequestMapping("oper")
	@ResponseBody
	@SaleTokenValidate(description="通过提现按钮")
	public   DataWrapper<Void> oper(
			@RequestHeader(value="saleToken") String saleToken,
			@RequestParam(value="balacceId") Integer balacceId
			){
		return witManageService.oper(balacceId);
	}

	//show + query
	@RequestMapping("query")
	@ResponseBody
	@SaleTokenValidate(description="对提现列表进行查询显示")
	public  DataWrapper<List<With>> query(
			@RequestHeader(value="adminToken",required=true) String adminToken,
			@RequestParam(value="message",required=false) String message ,  //姓名  或者手机号
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="numberPerpage",required=false)Integer numberPerpage
			){
		return witManageService.query(message,state,currentPage,numberPerpage);
	}
}
