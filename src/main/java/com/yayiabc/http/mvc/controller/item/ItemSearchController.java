package com.yayiabc.http.mvc.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.service.ItemSearchService;

@Controller
@RequestMapping("api/item")
public class ItemSearchController {
	@Autowired
	private ItemSearchService itemSearchService;
	
	@RequestMapping("itemSearch")
	@ResponseBody
	public DataWrapper<List<ItemInfo>> itemSearch(
			@RequestParam(value="keyWord",required=true) String keyWord,
			@RequestParam(value="rule",required=false,defaultValue="1") Integer rule,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return itemSearchService.itemSearch(keyWord,currentPage,numberPerPage,rule);
	}
	
	
}
