package com.yayiabc.http.mvc.controller.zan;

import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.service.CottomsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏
 */
@Controller
@RequestMapping("api/collect")
public class CollectController {


    @Autowired
    private CottomsPostService cottomsPostService;

    @RequestMapping(value="star")
    @ResponseBody
    @UserTokenValidate
    public DataWrapper<Void> star(
           @RequestHeader(value="token",required = true) String token,
           @RequestParam(value="postId",required = true)Integer postId,
           @RequestParam(value="type",required = true) String type) {
        return cottomsPostService.collect(token, postId, type);
    }
}
