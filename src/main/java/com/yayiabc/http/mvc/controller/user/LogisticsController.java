package com.yayiabc.http.mvc.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.LogisticsService;

@Controller
@RequestMapping("api/Exp")
public class LogisticsController {
	private LogisticsService logisticsService;
    @RequestMapping("queryExp")
    @ResponseBody
    public DataWrapper<String> queryLogMain(
    		@RequestParam(value="token",required=true)String  token,
    		@RequestParam(value="orderId",required=true)String  orderId
    		
    		//@RequestParam(value="ShipperCode",required=false) String  ShipperCode,//快递公司编码
    		//@RequestParam(value="LogisticCode",required=false) String  LogisticCode,//物流编号
    		){
    	return logisticsService.queryLog(token, orderId);
    }
}
