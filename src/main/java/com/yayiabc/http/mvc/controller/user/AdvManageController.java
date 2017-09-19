package com.yayiabc.http.mvc.controller.user;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.service.AdvManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/adv")
public class AdvManageController {
    @Autowired
    private AdvManageService advManageService;
    //show
    @RequestMapping("showAdv")
    @ResponseBody
   // @AdminTokenValidate(description="显示广告设置内容")
   
    public DataWrapper<List<AdvChart>> showAdv(
    		//@RequestHeader(value="adminToken",required=true)String adminToken
    		){
    	return advManageService.showAdv();
    }
    
  //update
    @RequestMapping("updateAdv")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="更改广告设置")
    public DataWrapper<Void> updateAdv(
    		@RequestHeader(value="adminToken",required=true)String adminToken,
    		@ModelAttribute AdvChart advChart,
    		@RequestParam(value="advId",required=true) Integer advId
    		){
    	advChart.setAdvId(advId);
    	return advManageService.updateAdv(advChart);
    }
    
    //insert
    @RequestMapping("insertAdv")
    @ResponseBody
    @AdminTokenValidate
    @AdminLog(description="添加广告设置")
    public DataWrapper<Void> insertAdv(
    		@RequestHeader(value="adminToken",required=true)String adminToken,
    		@ModelAttribute AdvChart advChart
    		){
    	return advManageService.insertAdv(advChart);
    }
  //delete
    /**
     *  "SF",
        "LogisticCode": "118650888018"
     * @param advId
     * @return
     */
    @AdminTokenValidate
    @RequestMapping("deleteAdv")
    @ResponseBody
    @AdminLog(description="删除广告设置")
    public DataWrapper<Void> deleteAdv(
    		@RequestHeader(value="adminToken")String adminToken,
    		@RequestParam(value="advId") Integer advId
    		){
    	return advManageService.deleteAdv(advId);
    }
    @RequestMapping("testLogMain")
    @ResponseBody
    public String testLogMain(
    		@RequestHeader(value="adminToken")String adminToken,
    		@RequestParam(value="ShipperCode") String  ShipperCode,
    		@RequestParam(value="LogisticCode") String  LogisticCode
    		){
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
