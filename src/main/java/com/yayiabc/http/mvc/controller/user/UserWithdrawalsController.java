package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
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
    * 提现列表的显示(后台)
    */
   @ResponseBody
   @RequestMapping("show")
   DataWrapper<Object> show(
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="nameOrPhone",required=false) String nameOrPhone,
		   @RequestParam(value="sign",required=false) String sign,
		   @RequestParam(value="currentPage",required=false,defaultValue="0")Integer currentPage,
		   @RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage,
		   @RequestParam(value="orderCTime",required=false)String orderCTime,
		   @RequestParam(value="orderETime",required=false)String orderETime
		   ){
	   HashMap<String, Object> hm=new HashMap<String,Object>();
	   hm.put("nameOrPhone", nameOrPhone);
	   hm.put("sign", sign);
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
		   @RequestHeader(value="token",required=true) String token
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
		   @RequestHeader(value="adminToken",required=true) String adminToken,
		   @RequestParam(value="withId",required=true) String withId,
		   @RequestParam(value="sign",required=true) Integer sign
		   ){
	  return  userWithdrawalsService.yesOrNo(withId,sign);
   }
   
  /**
   * 设置提现类型
   * @param token
   * @param accountHolder
   * @param cardNumber
   * @return
   */
   @ResponseBody
   @RequestMapping("setUpWitType")
   DataWrapper<Object> setUpWitType(
		   @RequestHeader(value="token",required=true) String token,
		   @RequestParam(value="witType",required=true) String witType,
		   @RequestParam(value="accountHolder",required=true) String accountHolder,
		   @RequestParam(value="oBank",required=false,defaultValue="非银行") String oBank,
		   @RequestParam(value="cardNumber",required=true) String cardNumber
		   ){
	  return  userWithdrawalsService.setUpWitType(token,witType,accountHolder,oBank,cardNumber);
   }
   
   @ResponseBody
   @RequestMapping("witSetUpShow")
   DataWrapper<Object> witSetUpShow(
		   @RequestHeader(value="token",required=true) String token
		   ){
	  return  userWithdrawalsService.witSetUpShow(token);
   }
   //显示当前用户的钱币余额
   @ResponseBody
   @RequestMapping("showUserQbNum")
   DataWrapper<Object> showUserQbNum(
		   @RequestHeader(value="token",required=true) String token
		   ){
	  return  userWithdrawalsService.showUserQbNum(token);
   }
}
