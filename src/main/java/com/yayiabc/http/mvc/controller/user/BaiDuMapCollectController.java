package com.yayiabc.http.mvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.BaiDuMapCollectService;

@Controller
@RequestMapping("api/crawlerMap")
public class BaiDuMapCollectController {
    
	@Autowired
	private BaiDuMapCollectService baiDuMapCollectService;
	 
	@RequestMapping("collect1")
	@ResponseBody
	@ExceptionHandler
	public DataWrapper<Void> collect1(){
		return baiDuMapCollectService.collect1();
	}
}
