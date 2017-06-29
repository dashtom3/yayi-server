package com.yayiabc.http.mvc.controller.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
      public DataWrapper<List<MyStar>> shows(
    		  @RequestParam(value = "phone",required=true) String phone,
    		  @RequestParam(value = "token",required=true) String token
    		  ){
    	  return ucss.shows(phone);
      }
      //取消单独商品收藏
      @RequestMapping("deleteOne")
      @ResponseBody
      public DataWrapper<Void> deleteOne(
    		  @RequestParam(value = "itemId",required=true) String itemId,
    		  @RequestParam(value = "phone",required=true) String phone,
    		  @RequestParam(value = "token",required=true) String token
    		  ){
    	       int itemStarIds=Integer.parseInt(itemId);
    	  return ucss.deleteStarOne(itemStarIds,phone);
      }
      //取消全部商品收藏
      @RequestMapping("deleteAll")
      @ResponseBody
      public DataWrapper<Void> deleteAll(
    		  @RequestParam(value = "phone",required=true) String phone,
    		  @RequestParam(value = "token",required=true) String token
    		  ){
    	  return ucss.deleteStarAll(phone);
      }
      
      //添加收藏
      @RequestMapping("addMyStar")
      @ResponseBody
      public DataWrapper<Void> addMyStar(
    		  @RequestParam(value = "phone",required=true) String phone,
    		  @RequestParam(value = "itemId",required=true) String itemId,
    		  @RequestParam(value = "token",required=true) String token
    		  ){
    	  return ucss.addMyStar(phone, itemId);
      }
}
