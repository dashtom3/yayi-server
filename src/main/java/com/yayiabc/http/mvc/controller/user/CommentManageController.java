package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.CommentManageService;

@Controller
@RequestMapping("api/commentManage")
public class CommentManageController {
      @Autowired 
      private CommentManageService commentManage;
     
      /**
       * 显示评论列表 recoveryState 1表示全部2表示已回复 3表示未回复
       * @param orderid
       * @param phone
       * @param recoveryState
       * @param currentPage
       * @param numberPerpage
       * @return
       */
     @RequestMapping("show")
     @ResponseBody
     public DataWrapper<List<Ordera>> show(
    		 @RequestParam(value="orderId",required=false) String orderId,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="recoveryState",required=false) Integer recoveryState,
    		 @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		 @RequestParam(value="numberPerpage",required=false,defaultValue="10") Integer numberPerpage
    		 ){
    	 if("".equals(orderId)){
    		 orderId=null;
    	 }
    	 if("".equals(phone)){
    		 phone=null;
    	 }
		return commentManage.commentM(orderId,recoveryState,phone,currentPage,numberPerpage);
      }
      //回复评论
      @RequestMapping("reply")
      @ResponseBody
      public DataWrapper<Void> reply(
    		  @RequestParam(value="orderId",required=true) String orderId,
    		  @RequestParam(value="itemId",required=true) String itemId,
    		  @RequestParam(value="data",required=true) String data
     		 ){
 		return commentManage.reply(orderId,itemId,data);
       }
}


