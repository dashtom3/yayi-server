package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     @RequestMapping("showWit")
     @ResponseBody
     public  DataWrapper<With> showWit(
    		 @RequestParam(value="token") String token,
    		 @RequestParam(value="phone") String phone
    		 ){
    	 return witManageService.showWit(phone);
     }
     
     //提交提现申请
     @RequestMapping("submitWit")
     @ResponseBody//real_name,type,anumber,cashMoney,phone
     public  DataWrapper<Void> submitWit(
    		 @RequestParam(value="token") String token,
    		 @RequestParam(value="vCode") String vCode,
    		/* @RequestParam(value="phone") String phone,
    		 @RequestParam(value="moneyNnm") String moneyNnm,*/
    		 /*real_name  type   anumber
    		 @RequestParam(value="phone") String phone,
    		 @RequestParam(value="vCode") String vCode,
    		 @RequestParam(value="type") String type*/
    		 @ModelAttribute With with
    		 ){
    	 
    	    if(vCode.equals(VerifyCodeManager.getPhoneCode(with.getPhone()))){
    	    	return witManageService.submitWit(with);
    	    }
    	    DataWrapper<Void> dataWrapper= new DataWrapper<Void>();
    	    dataWrapper.setMsg("验证验证码失败");
    	    return dataWrapper;
     }
     
     //获取验证码
     @ResponseBody
     @RequestMapping("gitVcode")
     public  void gitVcode(
    		 //@RequestParam(value="token") String token,
    		 @RequestParam(value="phone") String phone
    		 ){
    	 getVerifyCode(phone);
     }
     
     public String getVerifyCode(String phone) {
         String code = VerifyCodeManager.newPhoneCode(phone);
         System.out.println(code);
         if (code == null) {
             return code;
         }
         System.out.println(phone);
         boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
         if (result){
                System.out.println(1);
         } else {
             System.out.println(2);
         }
         return null;
  	}
     //
     @RequestMapping("oper")
     @ResponseBody
     public   DataWrapper<Void> oper(
    		 //@RequestParam(value="token") String token,
    		 @RequestParam(value="cashId") Integer cashId
    		 ){
    	 return witManageService.oper(cashId);
     }
     
     //show + query
     @RequestMapping("query")
     @ResponseBody
     public  DataWrapper<List<With>> query(
    		 //@RequestParam(value="token") String token,
    		 @RequestParam(value="message",required=false) String message ,  //姓名  或者手机号
    		 
    		 @RequestParam(value="state",required=false) Integer state
    		 ){
    	 return witManageService.query(message,state);
     }
}
