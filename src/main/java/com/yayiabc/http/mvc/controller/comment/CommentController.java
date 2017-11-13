package com.yayiabc.http.mvc.controller.comment;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.service.CommentService;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;



    //添加评论redis
    @RequestMapping("addCom")
    @ResponseBody
    public DataWrapper<Void> addCom(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) String type,//1.视频2.病例3.培训4.牙医圈
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId,//以上内容的id
            @RequestParam(value="parentId",required = true) Integer parentId,//父评论Id
            @ModelAttribute Comment comment
            ){
       return commentService.addCom(token,type,beCommentedId,comment,parentId);
    }


    //显示评论
    @RequestMapping("queryCom")
    @ResponseBody
    public DataWrapper<List<Comment>> queryCom(
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId,
            @RequestParam(value="currentPage",required = false,defaultValue = "1")Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10")Integer numberPerPage
    ){
        DataWrapper<List<Comment>> dataWrapper=new DataWrapper<List<Comment>>();
        int totalNumber=(int)redisService.SORTSET.zcard("点赞计数列表"+type+":"+beCommentedId);
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        dataWrapper.setPage(page,totalNumber);
        dataWrapper.setData(commentService.queryCom(type,beCommentedId,currentPage,numberPerPage));
        return dataWrapper;
    }

    //显示子评论
   /* @RequestMapping("querySubCom")
    @ResponseBody
    public DataWrapper<List<SubComment>> querySubCom(
            @RequestParam(value="preCommentId",required = true) Long preCommentId
    ){
          return commentService.querySubCom(preCommentId);
    }*/








}
