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

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;


    /**
     * 添加评论
     * @param token             用户的身份标识
     * @param type             1.视频2.病例3.培训4.牙医圈
     * @param beCommentedId  评论的内容的id
     * @param parentId      父评论Id,如果是子评论就传Null
     * @param comment      接收评论相关信息
     * @return
     */
    @RequestMapping(value="addCom",method = RequestMethod.POST)
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Object> addCom(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId,
            @RequestParam(value="parentId",required = true) Integer parentId,
            @ModelAttribute Comment comment
            ) {
       return commentService.addCom(token,type,beCommentedId,comment,parentId);
    }


    /**
     * 查找评论
     * @param type               1.视频2.病例3.培训4.牙医圈
     * @param beCommentedId     评论的内容的id
     * @param currentPage       当前第几页(默认为1)
     * @param numberPerPage     每页显示多少条(默认为10)
     * @param token             用户的身份标识
     * @param order             排序(默认为1.时间,2.点赞数)
     * @return
     */
    @RequestMapping("queryCom")
    @ResponseBody
    public DataWrapper<List<Comment>> queryCom(
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) Integer beCommentedId,
            @RequestParam(value="currentPage",required = false,defaultValue = "1")Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10")Integer numberPerPage,
            @RequestHeader(value="token",required = false) String token,
            @RequestParam(value="order",required = false,defaultValue = "1") Integer order
    ){
        return commentService.queryCom(type,beCommentedId,currentPage,numberPerPage,token,order);
    }

    /**
     * 删除评论
     * @param token                 用户的身份标识，必须，只有自己才能删除自己
     * @param type                  1.视频2.病例3.培训4.牙医圈
     * @param beCommentedId         评论的内容的id
     * @param parentId              一级评论的id
     * @param presentId             二级评论的id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public DataWrapper<Void> delete(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="beCommentedId",required = true) String beCommentedId,
            @RequestParam(value="parentId",required = true) Integer parentId,
            @RequestParam(value="presentId",required = false) Integer presentId
    ){
        return commentService.delete(type,beCommentedId,parentId,presentId);
    }








}
