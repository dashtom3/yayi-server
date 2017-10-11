package com.yayiabc.http.mvc.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.AppUserService;
import com.yayiabc.http.mvc.service.AppVersionservic;

/**
 *  Created by 严强    2017-10-11
 */
@Controller
@RequestMapping("api/appVer")
public class AppVersionContrller {
	@Autowired
	 private AppVersionservic appVersionservic;
	/**
	 * 获取版本号
	 */
	@RequestMapping("Ver")
	@ResponseBody
	 public DataWrapper<Void> ver(){
		return  appVersionservic.ver();
		 
		
	 }
}
