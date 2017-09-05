package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comments;
import com.yayiabc.http.mvc.service.CommentManageService;

@Controller
@RequestMapping("api/commentManage")
public class CommentManageController {
      @Autowired 
      private CommentManageService commentManage;
     
      /**
       * 显示评论列表 recoveryState 1表示全部2表示未回复 3表示已回复
       * @param orderId
       * @param recoveryState
       * @param currentPage
       * @param numberPerpage
       * @return
       */
     @RequestMapping("show")
     @ResponseBody
     @AdminTokenValidate
     @AdminLog(description="显示评论管理")
     public DataWrapper<List<Comments>> show(
    		 @RequestHeader(value="adminToken")String adminToken,
    		 @RequestParam(value="orderId",required=false) String orderId,
    		 @RequestParam(value="recoveryState",required=false) Integer recoveryState,
    		 @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		 @RequestParam(value="numberPerpage",required=false,defaultValue="10") Integer numberPerpage
    		 ){
    	 if("".equals(orderId)){
    		 orderId=null;
    	 }
		return commentManage.commentM(orderId,recoveryState,currentPage,numberPerpage);
      }
      //回复评论
      @RequestMapping("reply")
      @ResponseBody
      @AdminTokenValidate
      @AdminLog(description="回复评论")
      public DataWrapper<Void> reply(
    		  @RequestHeader(value="adminToken")String adminToken,
    		  @RequestParam(value="commentId",required=true) Integer commentId,
    		  @RequestParam(value="data",required=true) String data
     		 ){
 		return commentManage.reply(commentId,data);
       }
}


