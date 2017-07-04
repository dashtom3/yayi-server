package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.service.AdvManageService;
import com.yayiabc.http.mvc.service.CusResoService;

@Controller
@RequestMapping("api/cus")
public class CusResoController {
    @Autowired
    private CusResoService cusResoService;
    //show+query
    @RequestMapping("show")
    @ResponseBody
    public DataWrapper<List<CusResources>> show(
    		
    		@RequestParam(value="state",required=false)String state 
    		){
    	return cusResoService.show(state);
    }
     //insert
    @RequestMapping("insert")
    @ResponseBody
    public DataWrapper<Void> insert(
    		
    		//@RequestParam(value="state",required=true)String state 
    		@ModelAttribute CusResources cus
    		//四个参数全必须
    		){
    	return cusResoService.insert(cus);
    }
    //update
    @RequestMapping("update")
    @ResponseBody
    public DataWrapper<Void> update(
    		//@RequestParam(value="state",required=true)String state 
    		@ModelAttribute CusResources cus
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.update(cus);
    }
    //delete
    @RequestMapping("delete")
    @ResponseBody
    public DataWrapper<Void> delete(
    		@RequestParam(value="id",required=true)Integer id 
    		
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.delete(id);
    }
}
