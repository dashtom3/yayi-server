package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.UserDao;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * Created by Jo on 2017/8/2.
 */
@Controller
@RequestMapping("api/wxLogin")
public class WXLoginController {
    @Autowired
    WxLoginService wxLoginService;
   
  
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Object> login(
            @RequestParam("code") String code
    ){
        return wxLoginService.login(code);
    }

    @RequestMapping(value = "bindUser",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Object> bindUser(
            @RequestParam(value="phone",required=true) String phone,
            @RequestParam(value="verifyCode",required=true) String verifyCode,
            @RequestParam(value="openid",required=true) String openid,
            @RequestParam(value="type",required=true) String type
    ){
        return wxLoginService.bindUser(phone,verifyCode,openid,type);
    }
    
   
    /**
     * sex:1为男2为女
     * @param user
     * @param type
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public DataWrapper<User> updateUserInfo(
    		@ModelAttribute User user,
    		@RequestParam(value="type",required=true) Integer type//1表示已注册2.表示未注册
    		){
    	return wxLoginService.updateUserInfo(user,type);
    }
    
    @RequestMapping("updateSaleInfo")
    @ResponseBody
    public DataWrapper<Void> updateSaleInfo(
    		@ModelAttribute SaleInfo saleInfo,
    		@RequestParam(value="type",required=true) Integer type//1表示已注册2.表示未注册
    		){
    	return wxLoginService.updateSaleInfo(saleInfo,type);
    }
    
   
}
