package com.yayiabc.http.mvc.controller.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;

@Controller
public class TestController {
	
	@RequestMapping("api/test")
	@ResponseBody
	public DataWrapper<Void> test(
			@RequestHeader("token") String token,
			@RequestHeader("Content-Type") String Content_Type
			){
		System.out.println(token);
		System.out.println(Content_Type);
		return null;
	}
}
