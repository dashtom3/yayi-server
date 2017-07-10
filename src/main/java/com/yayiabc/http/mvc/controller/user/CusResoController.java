package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
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
    		   @RequestParam(value="companyName",required=false) String companyName,
    		   @RequestParam(value="companyAdd",required=false) String companyAdd,
    		   @RequestParam(value="linkMan",required=false) String linkMan
    		){
    	 HashMap<String, String> hashMap =new HashMap<String,String>();
    	 hashMap.put("companyName", companyName);
    	 hashMap.put("companyAdd", companyAdd);
    	 hashMap.put("linkMan", linkMan);
    	return cusResoService.show(hashMap);
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
    		@RequestParam(value="cusId",required=true)Integer id 
    		
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.delete(id);
    }
}
