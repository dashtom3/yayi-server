package com.yayiabc.http.mvc.controller.zan;

import com.yayiabc.common.annotation.UserTokenValidate;
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


    /**
     * 点赞或者取消点赞
     * @param token                     用户的身份标识
     * @param type                      视频/病例/牙医圈
     * @param typeId                    内容的id
     * @param parentId                  父评论的id
     * @param presentId                 子评论的id
     * @return
     */
    @RequestMapping("upvote")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> upvote(
            @RequestHeader(value="token",required = true) String token,
            @RequestParam(value="type",required = true) String type,
            @RequestParam(value="typeId",required = true) Integer typeId,
            @RequestParam(value="parentId",required =false) Integer parentId,
            @RequestParam(value="presentId",required = false)Integer presentId
    ){
        return zanService.upvote(token,type,typeId,parentId,presentId);
    }



}
