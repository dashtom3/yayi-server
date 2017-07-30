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
    @SaleTokenValidate(description="客户资源显示")
    public DataWrapper<List<CusResources>> show(
    		@RequestHeader(value="saleToken") String saleToken,
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
    @SaleTokenValidate(description="客户资源添加")
    public DataWrapper<Void> insert(
    		//@RequestParam(value="state",required=true)String state 
    		@RequestHeader(value="saleToken") String saleToken,
    		@ModelAttribute CusResources cus
    		//四个参数全必须
    		){
    	return cusResoService.insert(cus);
    }
    //update
    @RequestMapping("update")
    @ResponseBody
    @SaleTokenValidate(description="客户资源更改")
    public DataWrapper<Void> update(
    		//@RequestParam(value="state",required=true)String state 
    		@RequestHeader(value="saleToken") String saleToken,
    		@ModelAttribute CusResources cus
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.update(cus);
    }
    //delete
    @RequestMapping("delete")
    @ResponseBody
    @SaleTokenValidate(description="客户资源删除")
    public DataWrapper<Void> delete(
    		@RequestHeader(value="saleToken") String saleToken,
    		@RequestParam(value="cusId",required=true)Integer id 
    		
    		//四个参数非必须     但id是必须
    		){
    	return cusResoService.delete(id);
    }
}
