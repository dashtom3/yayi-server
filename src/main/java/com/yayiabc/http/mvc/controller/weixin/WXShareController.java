package com.yayiabc.http.mvc.controller.weixin;


import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.client.methods.HttpGet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.weixin.Sign;
import com.yayiabc.common.weixin.WXPayUtil;
import com.yayiabc.http.mvc.pojo.jpa.WXEntry;

@Controller
@RequestMapping("api/weixin")
public class WXShareController {

	@RequestMapping("share")
	@ResponseBody
	public DataWrapper<WXEntry> share (
		@RequestParam(value="url",required=true)String url
			){
		
//		url="wap.yayiabc.com";
		DataWrapper<WXEntry> dataWrapper =new DataWrapper<WXEntry>();
		SortedMap<String, String> parameterMap = new TreeMap<String, String>();
		 parameterMap.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));//原先的  90d4bae1c1843cec9aff6b4533f05881
         parameterMap.put("nonceStr", WXPayUtil.generateNonceStr());
        //获取签名
        Map<String, Object> cache=Sign.map;
        WXEntry wXEntry =new WXEntry();
        wXEntry.setTimestamp(parameterMap.get("timeStamp"));
        wXEntry.setNonceStr(parameterMap.get("nonceStr"));
        wXEntry.setAppId("wx4b1a6fde77626a32");
        //1.获取ACCESS_token,
        Long dateTime=(Long)cache.get("date");
        String jsapi_ticket="";
        if(!cache.isEmpty()&&dateTime!=null&&(System.currentTimeMillis()-dateTime<7200000)){
            jsapi_ticket=(String)cache.get("jsapi_ticket");
        }else{
            Map<String, Object> map=HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx4b1a6fde77626a32&secret=90d4bae1c1843cec9aff6b4533f05881");
            String access_token=(String)map.get("access_token");
            //https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
            //2.生成jsapi_ticket
            Map<String, Object> mapTwo=HttpUtil.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi");
            jsapi_ticket=(String)mapTwo.get("ticket");
            cache.put("jsapi_ticket",jsapi_ticket);
            cache.put("date",System.currentTimeMillis());
        }
        System.out.println("jsapi_ticket     "+jsapi_ticket);
        
        dataWrapper.setMsg(jsapi_ticket);
        String signature =Sign.sign(jsapi_ticket,url,parameterMap.get("nonceStr"),parameterMap.get("timeStamp")).get("signature");
        wXEntry.setSignature(signature);
        dataWrapper.setData(wXEntry);
      
        System.out.println(signature);
		return dataWrapper;
	}
}
