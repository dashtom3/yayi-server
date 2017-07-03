package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;

import com.yayiabc.http.mvc.service.OrderDetailsService;
/**
 *  <if test="PendingPayment !=NULL">
	          and  orde.state=${PendingPayment}  <!-- 待付款 -->
	       </if>
	       <if test="ShipmentPending !=NULL">
	         and   orde.state=${ShipmentPending} <!-- 待发货 -->
	       </if>
	       <if test="GoodsToBeReceived !=NULL">
	         and   orde.state=${GoodsToBeReceived} <!-- 待收货 -->
	       </if>
	        <if test="PendingEvaluation !=NULL">
	          and  orde.state=${PendingEvaluation}  <!-- 待评价 -->
 * @author Administrator
 *
 */
@Controller
@RequestMapping("api/OrderDetails")
public class OrderDetailsController {
     @Autowired 
     private OrderDetailsService orderDetailsService;
     @RequestMapping("show")
     @ResponseBody
     public DataWrapper<List<User>> show(
    		  @RequestParam(value="phone",required=false) String phone,
    		  @RequestParam(value="state",required=false) String state,
    		  @RequestParam(value="currentPage",required=false) Integer currentPage,
     		  @RequestParam(value="numberPerpage",required=false) Integer numberPerpage
    		 ){
    	    HashMap<String,String>map=new HashMap<String,String>();
    	    map.put("state", state);
    	 return orderDetailsService.orderDetailsShow(map,phone,currentPage,numberPerpage);
     }
}
