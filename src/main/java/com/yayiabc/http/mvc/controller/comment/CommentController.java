package com.yayiabc.http.mvc.controller.comment;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    //添加评论redis
    @RequestMapping("addCom")
    @ResponseBody
    public DataWrapper<Void> addCom(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId,
            @ModelAttribute Comment comment
            ){
        System.out.println(comment);
       return commentService.addCom(token,type,beCommentedId,comment);
    }

    //添加子评论
    @RequestMapping("addSubCom")
    @ResponseBody
    public DataWrapper<Void> addSubCom(
            @RequestHeader(value="token",required = true)String token,
            @RequestParam(value="preCommentId",required = true) Long preCommentId,
            @ModelAttribute SubComment subComment
            ){
        return commentService.addSubCom(token,preCommentId,subComment);
    }


    //显示评论
    @RequestMapping("queryCom")
    @ResponseBody
    public DataWrapper<List<Comment>> queryCom(
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId
    ){
        return commentService.queryCom(type,beCommentedId);
    }

    //显示子评论
    @RequestMapping("querySubCom")
    @ResponseBody
    public DataWrapper<List<SubComment>> querySubCom(
            @RequestParam(value="preCommentId",required = true) Long preCommentId
    ){
          return commentService.querySubCom(preCommentId);
    }

    //评论点赞
    @RequestMapping("zan")
    @ResponseBody
    public DataWrapper<Void> zan(
            @RequestParam(value="type",required = true)String type,
            @RequestParam(value="beCommentedId",required = true)Integer beCommentedId,
            @RequestParam(value="category",required = true)Integer category,//1.一级分类2.二级分类
            @RequestParam(value="commentId",required = true)Long commentId
    ){
         return commentService.zan(type,beCommentedId,category,commentId);
    }






}
