package com.yayiabc.http.mvc.controller.crawler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentist;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.service.CrawlerYellowPagesService;

@Controller
@RequestMapping("api/crawlerPages")
public class CrawlerYellowPagesController {
   
	@Autowired
	private CrawlerYellowPagesService crawlerYellowPagesService;
	/**
	 * 资料库列表
	 * @param token
	 * @param keyWord
	 * @param currentPage
	 * @param numberPerpage
	 * @return
	 */
	@RequestMapping("getMaterList")
	@ResponseBody
	public DataWrapper<List<DaForDentist>> getMaterList(
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "keyWord", required = false) String keyWord,
			@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage,
			@RequestParam(value="numberPerpage",required=false,defaultValue="10")Integer numberPerpage
			) {
	        return crawlerYellowPagesService.getMaterList(currentPage,numberPerpage,keyWord);
	}
	/**
	 * 资料库详情
	 * @param id
	 * @return
	 */
	@RequestMapping("getMaterDetail")
	@ResponseBody
	public DataWrapper<DaForDentist> getMaterDetail(
			@RequestParam(value = "id", required = false) String id
			) {
	        return crawlerYellowPagesService.getMaterDetail(id);
	}
	
	/**
	 * 诊所列表
	 * @param lng
	 * @param lat
	 * @param cityName
	 * @param keyWord
	 * @param currentPage
	 * @param numberPerpage
	 * @return
	 */
	@RequestMapping("getList")
	@ResponseBody
	public DataWrapper<List<Sheet1> > getList(
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
