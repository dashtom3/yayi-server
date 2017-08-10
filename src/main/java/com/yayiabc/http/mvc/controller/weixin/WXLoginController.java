package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
            @RequestParam("phone") String phone,
            @RequestParam("verifyCode") String verifyCode,
            @RequestParam("openid") String openid,
            @RequestParam("type") String type
    ){
        return wxLoginService.bindUser(phone,verifyCode,openid,type);
    }

}
