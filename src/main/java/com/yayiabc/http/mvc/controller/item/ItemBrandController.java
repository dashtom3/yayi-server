package com.yayiabc.http.mvc.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.service.ItemBrandService;

@Controller
@RequestMapping(value="api/item")
public class ItemBrandController{
	@Autowired
    private ItemBrandService itemBrandService;
    public ItemBrandController()
    {
    }

    @RequestMapping(value={"brandList"})
    @ResponseBody
    public DataWrapper<List<ItemBrand>> brandList()
    {
        return itemBrandService.brandList();
    }

    @RequestMapping(value={"brandItemList"})
    @ResponseBody
    public DataWrapper<List<ItemInfo>> brandItemList(
    		@RequestParam(value="itemBrandId") Integer itemBrandId,
    		@RequestParam(value="rule",required=false,defaultValue="1") Integer rule,
    		@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
    		@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
    		){
        return itemBrandService.brandItemList(itemBrandId,currentPage,numberPerPage,rule);
    }

    @RequestMapping(value={"itemDetailDes"})
    @ResponseBody
    public DataWrapper<ItemInfo> itemDetailDes(
    		@RequestParam(value="itemId") String itemId,
    		@RequestParam(value="token",required=false) String token)
    {
        return itemBrandService.itemDetailDes(itemId,token);
    }
    
   
    
    

    
}

