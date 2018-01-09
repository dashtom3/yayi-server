package com.yayiabc.http.mvc.controller.app;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 小月亮 on 2017/8/29.
 */
@Controller
@RequestMapping("api/app")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    /**
     * app中普通电商用户的注册
     */
    @RequestMapping("register")
    @ResponseBody
    public DataWrapper<User> register(
            @ModelAttribute User user,
            @RequestParam(value="code",required = true) String code,
            @RequestParam(value="id",required = false)String id,
            @RequestParam(value="userType",required = true)Integer userType
    ){

        return appUserService.regiseter(user,code,id,userType);
    }
}
