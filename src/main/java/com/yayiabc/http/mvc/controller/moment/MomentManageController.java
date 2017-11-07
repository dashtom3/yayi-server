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
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
    ){
        return momentManageService.queryList(currentPage,numberPerPage);
    }




    //为牙医圈的动态点赞和取消点赞
    @RequestMapping("upvote")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> upvote(
      @RequestParam("momentId") Integer momentId,
      @RequestHeader("token") String token
    ){
              return momentManageService.upvote(momentId,token);
    }

}
