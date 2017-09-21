package com.yayiabc.http.mvc.controller.item;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

	@RequestMapping("search")
	@ResponseBody
	public DataWrapper<List<ItemInfo>> search(
//			@RequestParam(value="oneClassify",required=false) String oneClassify,
//			@RequestParam(value="twoClassify",required=false) String twoClassify,
//			@RequestParam(value="itemBrandName",required=false) String itemBrandName,
//			@RequestParam(value="keyWord",required=false) String keyWord,
//			@RequestParam(value="rule",required=false,defaultValue="1") Integer rule,
//			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
//			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			@ModelAttribute Search search
			){
//		return itemSearchService.search(oneClassify,twoClassify,itemBrandName,keyWord,rule,currentPage,numberPerPage);
		return itemSearchService.search(search);
	}
	
	
}
