package com.yayiabc.http.mvc.controller.zan;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.ZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/zan")
public class ZanController {

    @Autowired
    private ZanService zanService;




    //点赞或者取消点赞
    @RequestMapping("upvote")
    @ResponseBody
    public DataWrapper<Void> upvote(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) Integer type,
            @RequestParam(value="typeId",required = true) Integer typeId,
            @RequestParam(value="parentId",required =false) Integer parentId
    ){
        return zanService.upvote(token,type,typeId,parentId);
    }



}
