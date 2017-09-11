package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.SaleInfoDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Created by Jo on 2017/8/2.
 */
@Controller
@RequestMapping("api/wxLogin")
public class WXLoginController {
    static ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String,String>();
    @Autowired
    WxLoginService wxLoginService;

    @Autowired
    SaleInfoDao saleInfoDao;

    @Autowired
    UserDao userDao;
   
  
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
            @RequestParam(value="type",required=true) String type
    ){
        return wxLoginService.bindUser(phone,verifyCode,type);
    }
    
   

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public DataWrapper<User> updateUserInfo(
    		@ModelAttribute User user,
            @RequestParam(value="openid",required=true) String openid,
    		@RequestParam(value="number",required=true) Integer number//1表示已注册2.表示未注册
    		){
    	return wxLoginService.updateUserInfo(user,number,openid);
    }
    
    @RequestMapping("updateSaleInfo")
    @ResponseBody
    public DataWrapper<Void> updateSaleInfo(
    		@ModelAttribute SaleInfo saleInfo,
            @RequestParam(value="openid",required=true) String openid,
    		@RequestParam(value="number",required=true) Integer number//1表示已注册2.表示未注册
    		){
    	return wxLoginService.updateSaleInfo(saleInfo,number,openid);
    }
    


    @RequestMapping(value="testProcedure")
    @ResponseBody
    public DataWrapper<User> testProcedure(
    ){
        DataWrapper<User> dataWrapper =new DataWrapper<User>();
        saleInfoDao.testProcedure();
        return dataWrapper;
    }

    @RequestMapping("test")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<User> test(@RequestHeader("token")String token) throws InterruptedException {
        DataWrapper<User> dataWrapper =new DataWrapper<User>();
        dataWrapper.setData(userDao.getUserByPhone("15900901007"));
        return dataWrapper;
    }






    
   
}
