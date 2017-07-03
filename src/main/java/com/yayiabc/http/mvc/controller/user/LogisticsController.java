package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.service.AdvManageService;

@Controller
@RequestMapping("api/Exp")
public class LogisticsController {
    @RequestMapping("queryExp")
    @ResponseBody
    public String queryLogMain(
    		@RequestParam(value="ShipperCode",required=true) String  ShipperCode,//快递公司编码
    		@RequestParam(value="LogisticCode",required=true) String  LogisticCode,//物流编号
    		HttpServletResponse response
    		){
    	response.setContentType("text/html;charset=UTF-8");
    	try {
    		String s=new LogisticsMain().getOrderTracesByJson(ShipperCode, LogisticCode);
    				System.out.println(s);
			return s;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LogisticCode;
    }
}
