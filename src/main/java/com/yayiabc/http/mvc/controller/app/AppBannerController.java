package com.yayiabc.http.mvc.controller.app;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.AppBanner;
import com.yayiabc.http.mvc.service.AppBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 小月亮 on 2017/9/5.
 */
@Controller
@RequestMapping("api/appBanner")
public class AppBannerController {

    @Autowired
    private AppBannerService appBannerService;
    /**
     * 添加app轮播图
     */
    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> add(
            @ModelAttribute AppBanner appBanner,
            @RequestHeader(value="adminToken",required = true)String adminToken
            ){
        return appBannerService.add(appBanner);
    }

    /**
     * 删除app轮播图
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> delete(
            @RequestParam(value="appBannerId",required = true)Integer appBannerId,
            @RequestHeader(value="adminToken",required = true)String adminToken
    ){
        return appBannerService.delete(appBannerId);
    }

    /**
     * 修改app轮播图
     */
    @RequestMapping(value="update",method = RequestMethod.POST)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> update(
            @ModelAttribute AppBanner appBanner,
            @RequestHeader(value="adminToken",required = true)String adminToken
    ){
        return appBannerService.update(appBanner);
    }

    /**
     * 查询app轮播图
     */
    @RequestMapping("query")
    @ResponseBody
    public DataWrapper<List<AppBanner>> query(){
        return appBannerService.query();
    }
}
