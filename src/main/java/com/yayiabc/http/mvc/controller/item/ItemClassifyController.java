package com.yayiabc.http.mvc.controller.item;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.pojo.model.SysResult;
import com.yayiabc.http.mvc.service.ItemClassifyService;


@Controller
@RequestMapping("api/item")
public class ItemClassifyController {
      @Autowired 
      private ItemClassifyService itemClassifyService;
      @RequestMapping("showClassify")
      @ResponseBody
      public  DataWrapper<List<Classify>>  showClassify(){
    	 return itemClassifyService.showsTreeClassify();
      }
      @RequestMapping("getAllClassifyAndBrand")
      @ResponseBody
      public DataWrapper<SysResult> getAllClassifyAndBrand(){
    	  return itemClassifyService.getAllClassifyAndBrand();
      }
      
      @RequestMapping("queryItemSearch")
      @ResponseBody
      public DataWrapper<List<ItemInfo>> queryItemSearch(
    		  @RequestParam(value="oneClassify",required=false) String oneClassify,
    		  @RequestParam(value="twoClassify",required=false) String twoClassify,
    		  @RequestParam(value="threeClassify",required=false) String threeClassify,
    		  @RequestParam(value="itemBrandName",required=false) String itemBrandName,
    		  @RequestParam(value="rule",required=false,defaultValue="1") Integer rule,
    		  @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		  @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
    		  ){
    	  return itemClassifyService.queryItemSearch(oneClassify,twoClassify,threeClassify,itemBrandName,rule,currentPage,numberPerPage);
      }
      
}
