package com.yayiabc.http.mvc.controller.moment;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.service.MomentManageService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/moment")
public class MomentManageController {
    @Autowired
    private MomentManageService momentManageService;

    
    //在朋友圈发布新动态
    @RequestMapping("add")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> add(
            @ModelAttribute Moment moment,
            @RequestHeader("token") String token
    ){
        System.out.println("moment"+moment);
        return momentManageService.add(moment,token);
    }

    //删除朋友圈的动态
    @RequestMapping("delete")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> delete(
            @RequestParam("momentId") Integer momentId,
            @RequestHeader("token") String token
    ){
        return momentManageService.delete(momentId);
    }

    //查询朋友圈的动态
    @RequestMapping("queryList")
    @ResponseBody
    public DataWrapper<List<Moment>> queryList(
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
            @RequestHeader(value="token",required = false) String token
    ){
        return momentManageService.queryList(currentPage,numberPerPage,token);
    }

    //我的动态
    @RequestMapping("myMoment")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<List<Moment>> myMoment(
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
            @RequestHeader(value="token",required = false) String token
    ){
        return momentManageService.myMoment(currentPage,numberPerPage,token);
    }

    //查看某条动态的详情
    @RequestMapping("detail")
    @ResponseBody
    public DataWrapper<Moment> detail(
            @RequestParam(value="momentId",required = true) Integer momentId,
            @RequestHeader(value="token",required = true) String token
    ){
        return momentManageService.detail(momentId,token);
    }




}
