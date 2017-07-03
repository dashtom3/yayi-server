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
    public String queryLogMain(
    		@RequestParam(value="phone",required=true)String  phone,
    		@RequestParam(value="itemId",required=true)String  itemId
    		//@RequestParam(value="ShipperCode",required=false) String  ShipperCode,//快递公司编码
    		//@RequestParam(value="LogisticCode",required=false) String  LogisticCode,//物流编号
    		){
    	try {
    		System.out.println(phone+itemId);
    		HashMap<String,Ordera> map=logisticsService.queryLog(phone, itemId);
    	     if(!map.isEmpty()){
    		String s=new LogisticsMain().getOrderTracesByJson(map.get(1).getShippingName(), map.get(1).getShippingCode());
    				System.out.println(s);
			return s;
    	   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "null";
    }
}
