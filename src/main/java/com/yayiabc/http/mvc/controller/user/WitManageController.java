package com.yayiabc.http.mvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.WitManageService;

@Controller
@RequestMapping("api/witManage")
public class WitManageController {
     @Autowired
     //显示提现
     private WitManageService witManageService;
     @RequestMapping("showWit")
     public  DataWrapper<Void> showWit(
    		 @RequestParam(value="token") String token,
    		 @RequestParam(value="phone") String phone
    		 ){
    	 return witManageService.showWit(phone);
     }
     
     //提交提现申请
     @RequestMapping("submitWit")
     public  DataWrapper<Void> submitWit(
    		 @RequestParam(value="token") String token,
    		 @RequestParam(value="moneyNnm") String moneyNnm,
    		 @RequestParam(value="phone") String phone,
    		 @RequestParam(value="vCode") String vCode
    		 ){
    	 return witManageService.submitWit(moneyNnm,phone,vCode);
     }
     
     //获取验证码
     @RequestMapping("gitVcode")
     public  void gitVcode(
    		 //@RequestParam(value="token") String token,
    		 @RequestParam(value="phone") String phone
    		 ){
    	 String code = VerifyCodeManager.getPhoneCode(phone);
    	 System.out.println(code);
     }
}
