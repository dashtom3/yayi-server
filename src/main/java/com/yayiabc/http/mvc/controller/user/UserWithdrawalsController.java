package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.UserWith;
import com.yayiabc.http.mvc.service.UserWithdrawalsService;

@Controller
@RequestMapping("api/userWith")
public class UserWithdrawalsController {
   @Autowired
   private UserWithdrawalsService userWithdrawalsService;
   /**
    * 提现列表的显示
    */
   @ResponseBody
   @RequestMapping("show")
   DataWrapper<Object> show(
		   @RequestParam(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="userName",required=false) String userName,
		   @RequestParam(value="currentPage",required=false,defaultValue="0")Integer currentPage,
		   @RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage,
		   @RequestParam(value="orderCTime",required=false)String orderCTime,
		   @RequestParam(value="orderETime",required=false)String orderETime
		   ){
	   HashMap<String, Object> hm=new HashMap<String,Object>();
	   hm.put("userName", userName);
	   hm.put("currentPage", currentPage);
	   hm.put("numberPerpage", numberPerpage);
	   hm.put("orderCTime", orderCTime);
	   hm.put("orderETime", orderETime);
	  return  userWithdrawalsService.show(hm);
   }
   /**
    * 
    * @param userWith
    * @param token
    * @return
    * 提交提现申请
    */
   @ResponseBody
   @RequestMapping("submit")
   DataWrapper<Object> submit(
		   @ModelAttribute UserWith  userWith,
		   @RequestParam(value="token",required=true) String token
		   ){
	  return  userWithdrawalsService.submit(userWith,token);
   }
   /**
    * 确定申请
    * (后台)
    */
   @ResponseBody
   @RequestMapping("YesOrNo")
   DataWrapper<Object> yesOrNo(
		   @RequestParam(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="withId",required=true) String withId,
		   @RequestParam(value="sign",required=true) Integer sign
		   ){
	  return  userWithdrawalsService.yesOrNo(withId,sign);
   }
}
