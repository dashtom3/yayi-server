package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.CusResoService;
import com.yayiabc.http.mvc.service.FindCusService;

@Controller
@RequestMapping("findCus")
public class FindCusController {
   @Autowired
   private  FindCusService findCusService;
   @Autowired
   private CusResoService cusResoService;
   //未注册客户
      @RequestMapping("unregistered")
      public DataWrapper<List<CusResources>>  unregistered(
    		  @RequestParam(value="state",required=false)String state     		  
    		  ){
    	 return  cusResoService.show(state);
      }
      //已注册客户 待绑定
      @RequestMapping("registered")
      public DataWrapper<List<SaleInfo>>  registered(
    		  ){
    	 return  findCusService.show();
      }
}
