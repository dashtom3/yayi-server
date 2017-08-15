package com.yayiabc.http.mvc.controller.weixin;


import java.util.Map;

import org.apache.http.client.methods.HttpGet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.weixin.Sign;
import com.yayiabc.http.mvc.pojo.jpa.WXEntry;

@Controller
@RequestMapping("api/weixin")
public class WXShareController {
	
	@RequestMapping("share")
	@ResponseBody
	public DataWrapper<WXEntry> share (
		@RequestParam(value="url",required=true)String url
			){
		DataWrapper<WXEntry> dataWrapper =new DataWrapper<WXEntry>();
		WXEntry wXEntry =new WXEntry();
		wXEntry.setAppId("wx4b1a6fde77626a32");
		//1.获取ACCESS_token,

		Map<String, Object> map=HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4b1a6fde77626a32&secret=90d4bae1c1843cec9aff6b4533f05881");
		String access_token=(String)map.get("access_token");
		//https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
		//2.生成jsapi_ticket
		Map<String, Object> mapTwo=HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi");
		String jsapi_ticket=(String)mapTwo.get("ticket");
		//3.生成签名
		Map<String, String> signMap=Sign.sign(jsapi_ticket, url);
		wXEntry.setNonceStr(signMap.get("nonceStr"));
		wXEntry.setSignature(signMap.get("signature"));
		wXEntry.setTimeStamp(signMap.get("timestamp"));
		dataWrapper.setData(wXEntry);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}
}
