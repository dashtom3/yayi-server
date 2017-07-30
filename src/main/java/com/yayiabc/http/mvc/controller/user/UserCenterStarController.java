package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.MyStar;
import com.yayiabc.http.mvc.service.UserCenterStarService;

@Controller
@RequestMapping("api/mystar")
public class UserCenterStarController {
      @Autowired 
      private UserCenterStarService ucss;
      //显示商品收藏数据
      @RequestMapping("shows")
      @ResponseBody
      @UserTokenValidate(description="显示所有收藏的商品")
      public DataWrapper<List<MyStar>> shows(
    		  //这里改为phone
    		  @RequestHeader(value = "token",required=true) String token,
    		  @RequestParam(value = "currentPage",required=false) Integer currentPage,//当前页
    		  @RequestParam(value = "numberPerPage",required=false) Integer numberPerPage//取多少
    		  ){
    	  return ucss.shows(token,currentPage,numberPerPage);
      }
      //取消单独商品收藏
      @RequestMapping("deleteOne")
      @ResponseBody
      @UserTokenValidate(description="删除收藏中的单一商品")
      public DataWrapper<Void> deleteOne(
    		  @RequestParam(value = "itemId",required=true) String itemId,
    		 
    		  @RequestHeader(value = "token",required=true) String token
    		  ){
    	       
    	  return ucss.deleteStarOne(token,itemId);
      }
      //取消全部商品收藏
      @RequestMapping("deleteAll")
      @ResponseBody
      @UserTokenValidate(description="清空收藏")
      public DataWrapper<Void> deleteAll(
    		 
    		  @RequestHeader(value = "token",required=true) String token
    		  ){
    	  return ucss.deleteStarAll(token);
      }
      
      //添加收藏
      @RequestMapping("addMyStar")
      @ResponseBody
      @UserTokenValidate(description="添加收藏")
      public DataWrapper<Void> addMyStar(
    		 
    		  @RequestParam(value = "itemId",required=true) String itemId,
    		  @RequestHeader(value = "token",required=true) String token
    		  ){
    	  return ucss.addMyStar(token,itemId);
      }
}
