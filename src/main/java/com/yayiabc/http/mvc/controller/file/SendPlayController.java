package com.yayiabc.http.mvc.controller.file;

import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SendPlayController {

    @Autowired
    private UserService userService;

    @RequestMapping("api/sendPlay")
    @ResponseBody
    public void sendPlay(){
        List<String> phoneList=userService.getAllPhoneList();
        System.out.println(phoneList);
        for (String phone:phoneList
             ) {
            boolean flag=HttpUtil.sendPhoneVerifyCode1111(phone);
            System.out.println("发送给"+phone+flag);
        }
    }
}
