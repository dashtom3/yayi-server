package com.yayiabc.http.mvc.controller.manager;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Feedback;
import com.yayiabc.http.mvc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    /**
     *新增意见反馈
     */
	 @RequestMapping(value="add",method=RequestMethod.POST)
	 @ResponseBody
    public DataWrapper<Void> add(
            @RequestParam(value = "message",required = true)String message,
            @RequestParam(value = "phone",required = true)String phone
    ){
        return feedbackService.addFeed(message,phone);
    }

    /**
     * 修改状态
     */
    @RequestMapping(value = "updateState",method = RequestMethod.POST)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> updateState(
            @RequestParam(value = "feedId",required = true)int feedId,
            @RequestHeader(value = "adminToken",required = true)String adminToken
    ){
            return feedbackService.updateState(feedId);
    }

    /**
     * 查询意见反馈列表
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<List<Feedback>> list(
            @RequestParam(value = "phone",required = false)String phone,
            @RequestParam(value = "state",required = false)Integer state,
            @RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
            @RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
            @RequestHeader(value="adminToken",required=true)String adminToken
    ){
        return feedbackService.feedList(phone, state, currentPage, numberPerPage);
    }

    /**
     * 删除意见反馈信息
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    @ResponseBody
    @AdminTokenValidate
    public DataWrapper<Void> delete(
            @RequestParam(value = "feedId",required = true)int feedId,
            @RequestHeader(value = "adminToken",required = true)String adminToken
    ){
                return feedbackService.deleteFeed(feedId);
    }
}
