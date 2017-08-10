package com.yayiabc.http.mvc.controller.weixin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;

@Controller
@RequestMapping("api/weixinPhone")
public class WXPhonePayController {
	
	@RequestMapping("phoneBuy")
	@ResponseBody
	public DataWrapper<Void> phoneBuy(
			@RequestParam("orderId") String orderId,HttpServletResponse response
			){
		
		return null;
	}
	
}
