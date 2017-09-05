package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.service.FreightManageService;

@Controller
@RequestMapping("api/freightManage")
public class FreightManageController {
	@Autowired 
    private FreightManageService freightManageService;
	//显示
    @RequestMapping("show")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="运费管理显示")
    public DataWrapper< List<PostFee>> show(
    		@RequestHeader(value="adminToken") String adminToken
    		){
    	return freightManageService.showFreight();
    }
    //更改自定义运费
    @RequestMapping("customFreight")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="更改自定义运费")
    public DataWrapper< Void> customFreight(
    		@RequestParam(value="postFeeId",required=true) String postFeeId,
    		@RequestHeader(value="adminToken") String adminToken,
    	/*	@RequestParam(value="postCity",required=false) String postCity,
    		@RequestParam(value="firstNum",required=false) Integer firstNum,
    		@RequestParam(value="firstMoney",required=false) Integer firstMoney,
    		@RequestParam(value="addNum",required=false) Integer addNum,
    		@RequestParam(value="addMoney",required=false) Integer addMoney*/
    		@ModelAttribute PostFee postFee
    		){
    	//PostFee postFee=new PostFee();
    	postFee.setPostFeeId(postFeeId);
    	/*postFee.setPostCity(postCity);
    	postFee.setFirstNum(firstNum);
    	postFee.setFirstMoney(firstMoney);
    	postFee.setAddNum(addNum);
    	postFee.setAddMoney(addMoney);*/
    	return freightManageService.customFreight(postFee);
    }
    //add
    @RequestMapping("addCustomFreight")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="增加自定义运费管理")
    public DataWrapper< Void> addCustomFreight(
    		@RequestHeader(value="adminToken") String adminToken,
    		//@RequestParam(value="postFeeId",required=true) Integer postFeeId,
    	/*	@RequestParam(value="postCity",required=false) String postCity,
    		@RequestParam(value="firstNum",required=false) Integer firstNum,
    		@RequestParam(value="firstMoney",required=false) Integer firstMoney,
    		@RequestParam(value="addNum",required=false) Integer addNum,
    		@RequestParam(value="addMoney",required=false) Integer addMoney*/
    		@ModelAttribute PostFee postFee
    		){
    	//PostFee postFee=new PostFee();
    	
    	/*postFee.setPostCity(postCity);
    	postFee.setFirstNum(firstNum);
    	postFee.setFirstMoney(firstMoney);
    	postFee.setAddNum(addNum);
    	postFee.setAddMoney(addMoney);*/
    	return freightManageService.addCustomFreight(postFee);
    }
    //删除
    @RequestMapping("deleteCustomFreight")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="删除自定义运费")
    public DataWrapper< Void> customFreight(
    		@RequestHeader(value="adminToken") String adminToken,
    		//@RequestParam(value="postFeeId",required=true)
    		 Integer postFeeId
    		 
    		){
    	return freightManageService.deleteCustomFreight(postFeeId);
    }
    
    //显示包邮数据
    @RequestMapping("showFreeShipp")
    @ResponseBody
    public DataWrapper<List<FreeShipping>> showFreeShipp(
    		@RequestHeader(value="adminToken") String adminToken) {
		// TODO Auto-generated method stub
    	
		DataWrapper<List<FreeShipping>> list=freightManageService.showFreeShipp();
	    
		return list;
	}
	//包邮Add
    @AdminTokenValidate
	 @RequestMapping("insertFreeShipp")
	    @ResponseBody
	    @AdminLog(description="插入包邮数据")
		public DataWrapper<Void> insertFreeShipp(
				//freeShipping f
				@RequestParam(value="postCity",required=true) String postCity,
				@RequestParam(value="freeShippingMoney",required=true) String freeShippingMoney,
				@RequestParam(value="state",required=true) String state,
				@RequestHeader(value="adminToken") String adminToken
				) {
		 FreeShipping f=new FreeShipping();
		 f.setPostCity(postCity);
		 f.setState(Integer.parseInt(state));
		 f.setFreeShippingMoney(Integer.parseInt(freeShippingMoney));
			return freightManageService.insertFreeShipp(f);
		}
	//包邮update
		 @RequestMapping("updateFreeShipp")
		 @ResponseBody
		 @AdminTokenValidate
		 @AdminLog(description="更改包邮数据")
			public DataWrapper<Void> updateFreeShipp(
					//freeShipping f
					@RequestParam(value="postCity",required=false) String postCity,
					@RequestParam(value="freeShippingMoney",required=false) String freeShippingMoney,
					@RequestParam(value="state",required=false) String state,
					@RequestParam(value="freePostId",required=true) String freePostId,
					@RequestHeader(value="adminToken") String adminToken
					) {
			 FreeShipping f=new FreeShipping();
			 f.setPostCity(postCity);
			 
			 if(freeShippingMoney!=null){
				 f.setFreeShippingMoney(Integer.parseInt(freeShippingMoney));
			 }if(state!=null){
				 f.setState(Integer.parseInt(state));
			 }
			 f.setFreePostId(Integer.parseInt(freePostId));
				return freightManageService.updateFreeShipp(f);
			}
}
