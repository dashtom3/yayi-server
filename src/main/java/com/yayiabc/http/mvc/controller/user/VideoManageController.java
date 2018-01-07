package com.yayiabc.http.mvc.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.VideoManageService;

@Controller
@RequestMapping("api/vid")
public class VideoManageController {
     @Autowired
     private VideoManageService videoManageService;


    /**
     * 显示视频列表
     * @param rule                    排序规则  (1,最多播放2.最多评论,3.最新)
     * @param videoCategory           视频分类  (1.外科2.内科3.修复4.种植5.正畸)
     * @param currentPage             当前第几页(默认为 1)
     * @param numberPerPage           每页显示多少条(默认为 10)
     * @param token                    用户的身份标识，不传表示未登录
     * @return
     */
     @RequestMapping("showVid")
     @ResponseBody
     public DataWrapper<Object> showVid(
             @RequestParam(value="rule",required = false,defaultValue = "2") Integer rule,
             @RequestParam(value="videoCategory",required = false)Integer videoCategory,
             @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
             @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
             @RequestHeader(value="token",required = false) String token
    		 ){
     	return videoManageService.showVid(rule,videoCategory,currentPage,numberPerPage,null,token);
     }

    /**
     * 修改视频
     * @param adminToken     管理员身份凭证
     * @param vidManage      以一个视频对象接收视频的信息
     * @return
     */
     @RequestMapping("updateVid")
     @ResponseBody
     @AdminTokenValidate
     @AdminLog(description="更改视频管理列表")
     public DataWrapper<Void> updateVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
     		@ModelAttribute VidManage vidManage
     		){
     	return videoManageService.updateVid(vidManage);
     }

    /**
     * 管理员添加视频
     * @param adminToken        管理员身份凭证
     * @param vidManage         以一个视频对象接收视频的信息
     * @return
     */
     @RequestMapping("insertVid")
     @ResponseBody
     public DataWrapper<Void> insertVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
    		 @ModelAttribute VidManage vidManage
     		){
         return videoManageService.insertVid(vidManage);
     }

    /**
     * 删除视频
     * @param adminToken        管理员身份凭证
     * @param viId              要删除的视频id
     * @return
     */
     @RequestMapping("deleteVid")
     @ResponseBody
     @AdminTokenValidate
     @AdminLog(description="删除视频管理列表")
     public DataWrapper<Void> deleteVid(
    		 @RequestHeader(value="adminToken",required=true) String adminToken,
     		@RequestParam(value="viId") Integer viId
     		){
     	return videoManageService.deleteVid(viId);
     }

    /**
     * 视频播放
     * @param viId  视频Id
     * @return
     */
    @RequestMapping("play")
    @ResponseBody
    public DataWrapper<Void> play(
            @RequestParam(value="viId",required = true)Integer viId
    ){
    	return videoManageService.play(viId);
    }

    /**
     * 视频详情
     * @param viId      要查看的视频的id
     * @return
     */
    @RequestMapping("detail")
    @ResponseBody
    public DataWrapper<VidManage> detail(
            @RequestParam(value="viId",required = true) Integer viId,
            @RequestHeader(value="token",required = false) String token
    		){
            return videoManageService.detail(viId,token);
    }



    /**
     * 获取视频相关商品的信息
     * @param viId          视频id
     * @return
     */
    public DataWrapper<ItemInfo> videoItem(
            @RequestParam(value="viId",required = true) Integer viId
    ){
            return videoManageService.videoItem(viId);
    }



}
