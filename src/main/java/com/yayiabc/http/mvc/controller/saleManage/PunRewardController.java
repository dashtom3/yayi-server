package com.yayiabc.http.mvc.controller.saleManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.SaleLog;
import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.PunRewardService;

//销售员的惩罚奖励
@Controller
@RequestMapping("api/PW")
public class PunRewardController {
    @Autowired
    private PunRewardService punRewardService;
    @ResponseBody
    @RequestMapping("show")
    @SaleTokenValidate
    @SaleLog(description="创客:显示钱包余额 ")
    DataWrapper<Object> show(
    		@RequestHeader(value="saleToken",required=true) String saleToken
    		 ){
       	 
		return punRewardService.show(saleToken);
    }
    @ResponseBody
    @RequestMapping("shows")
    @SaleTokenValidate
    @SaleLog(description="后台:显示钱包余额 ")
    DataWrapper<Object> shows(
    		@RequestHeader(value="saleToken",required=true) String saleToken,
    		@RequestParam(value="saleId",required=true) String saleId
    		 ){
		return punRewardService.shows(saleId);
    }
    @ResponseBody
    @RequestMapping("adminShows")
    @AdminTokenValidate(description="后台:显示钱包余额 ")
    DataWrapper<Object> adminshows(
    		@RequestHeader(value="adminToken",required=true) String adminToken,
    		@RequestParam(value="saleId",required=true) String saleId
    		 ){
		return punRewardService.shows(saleId);
    }
    //增加或减少余额
    @RequestMapping("addOrDelMoney")
    @ResponseBody
    @AdminTokenValidate
    @SaleLog(description="创客:管理员增加或者减少余额 ")
     DataWrapper<Void> addOrDelMoney(
    		 @RequestHeader(value="adminToken",required=true)String adminToken,
    		 @RequestParam(value="saleId",required=true)String saleId,
    		 @RequestParam(value="sign",required=true) Integer sign,
    		 @RequestParam(value="money",required=true) String money
    		 ){
    	return punRewardService.addOrDelMoney(saleId,sign,money);
     }
}
