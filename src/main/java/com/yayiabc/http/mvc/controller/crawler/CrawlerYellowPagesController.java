package com.yayiabc.http.mvc.controller.crawler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentistYa;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.service.CrawlerYellowPagesService;

@Controller
@RequestMapping("api/crawlerPages")
public class CrawlerYellowPagesController {
   
	@Autowired
	private CrawlerYellowPagesService crawlerYellowPagesService;
	
	@RequestMapping("getYellowPages")
	@ResponseBody
	public DataWrapper<List<Sheet1>> getYellowPage(
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage
			) {
	        return crawlerYellowPagesService.getYellowPage(currentPage,numberPerpage);
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public DataWrapper<List<DaForDentistYa> > getList(
			@RequestParam(value="lng",required=false,defaultValue="0.0")double lng, //经度
			@RequestParam(value="lat",required=false,defaultValue="0.0")double lat,//纬度
			@RequestParam(value="cityName",required=false)String cityName,
			@RequestParam(value="keyWord",required=false)String keyWord,
			@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage
			) {
	        return crawlerYellowPagesService.getList(currentPage,numberPerpage,lng,lat,cityName,keyWord);
	}
}
