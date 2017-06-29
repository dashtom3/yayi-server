package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
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
     public DataWrapper<List<VidManage>> showVid(){
     	return videoManageService.showVid();
     }
     
   //update
     @RequestMapping("updateVid")
     @ResponseBody
     public DataWrapper<Void> updateVid(
     		@ModelAttribute VidManage vidManage,
     		@RequestParam(value="viId",required=true) Integer viId
     		){
    	 vidManage.setViId(viId);
     	return videoManageService.updateVid(vidManage);
     }
     
     //insert
     @RequestMapping("insertVid")
     @ResponseBody
     public DataWrapper<Void> insertVid(
    		 @ModelAttribute VidManage vidManage
     		){
     	return videoManageService.insertVid(vidManage);
     }
     
   //delete
     @RequestMapping("deleteVid")
     @ResponseBody
     public DataWrapper<Void> deleteVid(
     		@RequestParam(value="viId") Integer viId
     		){
     	return videoManageService.deleteVid(viId);
     }
}
