package com.yayiabc.http.mvc.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.classfile.StackMapTable_attribute.append_frame;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AdvManageService;
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
