package com.yayiabc.http.mvc.controller.faq;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.FaqAnswer;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;
import com.yayiabc.http.mvc.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问答的管理
 */
@Controller
@RequestMapping("api/faq")
public class FaqController {
    @Autowired
    private FaqService faqService;

    //提问
    @RequestMapping("addQuestion")
    @ResponseBody
    public DataWrapper<FaqQuestion> addQuestion(
            @RequestHeader(value="token",required = true) String token,
            @ModelAttribute FaqQuestion faqQuestion
            ){
        System.out.println("开始执行controller");
        return faqService.addQuestion(token,faqQuestion);
    }

    //回答问题
    @RequestMapping("addAnswer")
    @ResponseBody
    public DataWrapper<FaqAnswer> addAnswer(
            @RequestHeader(value="token",required = true) String token,
            @ModelAttribute FaqAnswer faqAnswer,
            @RequestParam(value="faqQuestionId",required = true)Integer faqQuestionId
    ){
        return faqService.addAnswer(token,faqAnswer,faqQuestionId);
    }

    //问答列表
    @RequestMapping("list")
    @ResponseBody
    public DataWrapper<List<FaqQuestion>> list(
            @RequestParam(value="faqQuestionType",required = false) Integer faqQuestionType,//分类:1外科2内科3修复4种植5正畸不传为全部
            @RequestParam(value="order",required = false,defaultValue = "1") Integer order,//1最新（默认）  2最多回答
            @RequestParam(value="currentPage",required = false,defaultValue = "1")Integer currentPage,
            @RequestParam(value="numberPerPage",required = false,defaultValue = "10")Integer numberPerPage
    ){
        return faqService.list(faqQuestionType,order,currentPage,numberPerPage);
    }

    //问答详情
    @RequestMapping("detail")
    @ResponseBody
    public DataWrapper<FaqQuestion> detail(
            @RequestParam(value="faqQuestionId",required = true) Integer faqQuestionId,
            @RequestParam(value="currentPage",required = false,defaultValue = "1")Integer currentPage,
            @RequestParam(value="numberPerPage",required = false,defaultValue = "10")Integer numberPerPage
    ){
        return faqService.questionDetail(faqQuestionId,currentPage,numberPerPage);
    }

    //我的提问
    @RequestMapping("myQuestion")
    @ResponseBody
    public DataWrapper<List<FaqQuestion>> myQuestion(
            @RequestHeader(value="token",required = false) String token,
            @RequestParam(value="currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value="numberPerPage",required = false,defaultValue = "10")Integer numberPerPage
    ){
        return faqService.myQuestion(token,currentPage,numberPerPage);
    }

    //我的回答
    @RequestMapping("myAnswer")
    @ResponseBody
    public DataWrapper<List<FaqQuestion>> myAnswer(
            @RequestHeader(value="token",required = false) String token,
            @RequestParam(value="currentPage",required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(value="numberPerPage",required = false,defaultValue = "10")Integer numberPerPage
    ){
        return faqService.myAnswer(token,currentPage,numberPerPage);
    }


}
