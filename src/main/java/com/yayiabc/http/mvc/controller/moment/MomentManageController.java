package com.yayiabc.http.mvc.controller.moment;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.MomentCommentModel;
import com.yayiabc.http.mvc.pojo.jpa.SubMomentComment;
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

    //写评论包括回复评论
    @RequestMapping("addComment")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> addComment(
            @ModelAttribute MomentCommentModel momentCommentModel,
            @RequestHeader("token") String token
    ){
        return momentManageService.addComment(momentCommentModel,token);
    }

    //删除一个动态的评论
    @RequestMapping("deleteComment")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> deleteComment(
            @RequestParam("momentCommentId") Integer momentCommentId,
            @RequestHeader("token") String token
    ){
        return momentManageService.deleteComment(momentCommentId);
    }

    //查看单个父评论下面的所有评论
    @RequestMapping("querySubCommentList")
    @ResponseBody
    public DataWrapper<List<SubMomentComment>> querySubCommentList(
            @RequestParam("momentCommentId")Integer momentCommentId
    ){
        return momentManageService.querySubCommentList(momentCommentId);
    }

    //为牙医圈的一级评论点赞
    @RequestMapping("upvote")
    @ResponseBody
    public DataWrapper<Void> upvote(
            @RequestParam(value="momentCommentId",required = true) Integer momentCommentId
    ){
        return momentManageService.upvote(momentCommentId);
    }

}
