package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.VideoManageService;

@Controller
@RequestMapping("api/vid")
public class VideoManageController {
     @Autowired
     private VideoManageService videoManageService;
    
     //show
     @RequestMapping("showVid")
     @ResponseBody
     @AdminTokenValidate(description="显示视屏管理列表")
     public DataWrapper<List<VidManage>> showVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken
    		 ){
     	return videoManageService.showVid();
     }
     
   //update
     @RequestMapping("updateVid")
     @ResponseBody
     @AdminTokenValidate(description="更改视屏管理列表")
     public DataWrapper<Void> updateVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
     		@ModelAttribute VidManage vidManage,
     		@RequestParam(value="viId",required=true) Integer viId
     		){
    	 vidManage.setViId(viId);
     	return videoManageService.updateVid(vidManage);
     }
     
     //insert
     @RequestMapping("insertVid")
     @ResponseBody
     @AdminTokenValidate(description="插入视屏到管理列表")
     public DataWrapper<Void> insertVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
    		 @ModelAttribute VidManage vidManage
     		){
     	return videoManageService.insertVid(vidManage);
     }
     
   //delete
     @RequestMapping("deleteVid")
     @ResponseBody
     @AdminTokenValidate(description="删除视屏管理列表")
     public DataWrapper<Void> deleteVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
     		@RequestParam(value="viId") Integer viId
     		){
     	return videoManageService.deleteVid(viId);
     }
}
