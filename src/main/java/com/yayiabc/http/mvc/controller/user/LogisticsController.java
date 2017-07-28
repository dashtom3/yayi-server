package com.yayiabc.http.mvc.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.LogisticsService;

import net.sf.json.JSONArray;
@Controller
@RequestMapping("api/Exp")
public class LogisticsController {
	@Autowired
	private LogisticsService logisticsService;
	@RequestMapping("queryExp")
	@ResponseBody
	public DataWrapper<String> queryLogMain(
			@RequestParam(value="orderId",required=true)String  orderId

			//@RequestParam(value="ShipperCode",required=false) String  ShipperCode,//快递公司编码
			//@RequestParam(value="LogisticCode",required=false) String  LogisticCode,//物流编号
			){
		DataWrapper<String>  l=logisticsService.queryLog(orderId);
		if(l!=null)
			return l;
		else
			return null;
	}
	@RequestMapping("queryExpQS")
	@ResponseBody
	public String queryExpQS(//这是我的回调地址  里面的形参我应该有哪些
			@RequestParam(value="RequestData",required=false)String RequestData,
			@RequestParam(value="RequestType",required=false)String RequestType
			){
		int sign=0;
		//  ----如果已经签收  
		JSONArray jsonArray = JSONArray.fromObject("["+RequestData+"]");

		List<Map<String,Object>> mapListJson = (List)jsonArray;
		for (int i = 0; i < mapListJson.size(); i++) {
			Map<String,Object> obj=mapListJson.get(i);
			for(String key:obj.keySet()){
				if(key.equals("state")){
					if(obj.get(key).equals(3)){
						sign= logisticsService.updateState((String)obj.get("OrderCode")); 
					}
				}
			}
		}
		System.out.println("回调成功");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(sign>0){
			return "{\"EBusinessId\":\"1292696\",\"UpdateTime\":\""+formatter.format(new Date())+"\",\"Success\":true,\"Reason\":\"\"}";
		}else{
			return "{\"EBusinessId\":\"1292696\",\"UpdateTime\":\""+formatter.format(new Date())+"\",\"Success\":false,\"Reason\":\"\"}";
		}
	}
}
