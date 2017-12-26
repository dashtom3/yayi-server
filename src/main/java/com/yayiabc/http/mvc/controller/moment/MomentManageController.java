package com.yayiabc.http.mvc.controller.moment;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.service.MomentManageService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 牙医圈动态管理
 * @author  yehu
 */
@Controller
@RequestMapping("api/moment")
public class MomentManageController {
    @Autowired
    private MomentManageService momentManageService;


    /**
     * 牙医圈发布新动态
     * @param moment        动态的实体类对象
     * @param token         发布对象的用户标识
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> add(
            @ModelAttribute Moment moment,
            @RequestHeader("token") String token
    ){
        return momentManageService.add(moment,token);
    }

    /**
     * 删除牙医圈的动态
     * @param momentId      动态id
     * @param token         用户的身份标识
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> delete(
            @RequestParam("momentId") Integer momentId,
            @RequestHeader("token") String token
    ){
        return momentManageService.delete(momentId);
    }

    /**
     * 查询朋友圈的动态列表
     * @param currentPage       当前第几页(默认为1)
     * @param numberPerPage     每页显示多少条(默认为10)
     * @param token             用户的身份标识(可不传)
     * @return
     */
    @RequestMapping("queryList")
    @ResponseBody
    public DataWrapper<List<Moment>> queryList(
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
            @RequestHeader(value="token",required = false) String token
    ){
        return momentManageService.getMommentList(currentPage,numberPerPage,token,1);
    }

    /**
     * 我的动态列表
     * @param currentPage       当前第几页(默认为1)
     * @param numberPerPage     每页显示多少条(默认为10)
     * @param token             用户的身份标识
     * @return
     */
    @RequestMapping("myMoment")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<List<Moment>> myMoment(
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
            @RequestHeader(value="token",required = false) String token
    ){
        return momentManageService.getMommentList(currentPage,numberPerPage,token,2);
    }

    /**
     * 查看单条动态详情
     * @param momentId          动态id
     * @param token             用户的身份标识
     * @return
     */
    @RequestMapping("detail")
    @ResponseBody
    public DataWrapper<Moment> detail(
            @RequestParam(value="momentId",required = true) Integer momentId,
            @RequestHeader(value="token",required = true) String token
    ){
        return momentManageService.detail(momentId,token);
    }




}
