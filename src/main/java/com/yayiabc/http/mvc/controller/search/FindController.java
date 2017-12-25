package com.yayiabc.http.mvc.controller.search;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/search")
public class FindController {

    @Autowired
    private FindService findService;

    //发现页面的搜索，按照名称进行模糊匹配
    @RequestMapping(value="findList",method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Object> findList(
            @RequestParam(value="keyWord",required = true) String keyWord,
            @RequestParam(value="type",required = false,defaultValue = "1") Integer type,//1.病例 2.视频 3.问答
            @RequestParam(value="classify",required = false)Integer classify, //1.外科2.内科3.修复4.种植5.正畸
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
    ){
        System.out.println("keyWord"+keyWord);
        System.out.println("classify"+classify);
        return findService.findList(keyWord,type,classify,currentPage,numberPerPage);
    }
}
