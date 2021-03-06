package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.SaleTokenValidate;
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
    @AdminTokenValidate
    @AdminLog(description="客户资源显示")
    public DataWrapper<List<CusResources>> show(
    		   @RequestHeader(value="adminToken") String adminToken,
    		   @RequestParam(value="companyName",required=false) String companyName,
    		   @RequestParam(value="companyAdd",required=false) String companyAdd,
    		   @RequestParam(value="linkMan",required=false) String linkMan,
    		   @RequestParam(value="currentPage",required=false) Integer currentPage,
   			@RequestParam(value="numberPerpage",required=false) Integer numberPerpage
    		){
    	 HashMap<String, String> hashMap =new HashMap<String,String>();
    	 hashMap.put("companyName", companyName);
    	 hashMap.put("companyAdd", companyAdd);
    	 hashMap.put("linkMan", linkMan);
    	return cusResoService.show(hashMap,currentPage,numberPerpage);
    }
     //insert
    @RequestMapping("insert")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="客户资源添加")
    public DataWrapper<Void> insert(
    		//@RequestParam(value="state",required=true)String state 
    		@RequestHeader(value="adminToken") String adminToken,
    		@ModelAttribute CusResources cus
    		//四个参数全必须
    		){
    	return cusResoService.insert(cus);
    }
    //update
    @RequestMapping("update")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="客户资源更改")
    public DataWrapper<Void> update(
    		//@RequestParam(value="state",required=true)String state 
    		@RequestHeader(value="adminToken") String adminToken,
    		@ModelAttribute CusResources cus
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.update(cus);
    }
    //delete
    @RequestMapping("delete")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="客户资源删除")
    public DataWrapper<Void> delete(
    		@RequestHeader(value="adminToken") String adminToken,
    		@RequestParam(value="cusId",required=true)Integer id
    		){
    	return cusResoService.delete(id);
    }
}
