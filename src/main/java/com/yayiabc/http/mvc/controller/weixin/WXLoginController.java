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

    @RequestMapping(value = "bandUser",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Object> bandUser(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("openid") String openid,
            @RequestParam("type") String type
    ){
        return wxLoginService.bandUser(phone,password,openid,type);
    }

}
