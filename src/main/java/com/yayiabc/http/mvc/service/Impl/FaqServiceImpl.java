package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.FaqDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.FaqAnswer;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.FaqService;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.VideoManageService;

import redis.clients.jedis.Jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqDao faqDao;

    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private VideoManageService videoManageService;

    @Autowired
    private RedisService redisService;

    @Override
    public DataWrapper<FaqQuestion> addQuestion(String token, FaqQuestion faqQuestion) {
        DataWrapper<FaqQuestion> dataWrapper=new DataWrapper<FaqQuestion>();
        //获取userId
        String userId=utilsDao.getUserID(token);
        faqQuestion.setUserId(userId);
        //保存进数据库
        int reflectRows=faqDao.addQuestion(faqQuestion);
        if(reflectRows==0){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            return dataWrapper;
        }
        dataWrapper.setData(faqQuestion);
        return dataWrapper;
    }

    @Override
    public DataWrapper<FaqAnswer> addAnswer(String token, FaqAnswer faqAnswer,Integer faqQuestionId) {
        DataWrapper<FaqAnswer> dataWrapper=new DataWrapper<FaqAnswer>();
        User user=utilsDao.getUserByToken(token);
        faqAnswer.setUserId(user.getUserId());
        faqAnswer.setUserName(user.getTrueName());
        faqAnswer.setUserPic(user.getUserPic());
        //根据提供问题id 去找提问者userId
        String questionUid=faqDao.getQuestionUid(faqQuestionId);
        if(!questionUid.equals(user.getUserId())){
            redisService.LISTS.rpush("评论消息推送"+questionUid,user.getTrueName()+"回答了你的问题,问答:"+faqQuestionId);
        }
        
        //保存进数据库
        int reflectRows=faqDao.addAnswer(faqAnswer,faqQuestionId);
        faqAnswer.setFaqAnswerTime(new Date());
        if(reflectRows==0){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            return dataWrapper;
        }
        dataWrapper.setData(faqAnswer);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Object> list(Integer faqQuestionType, Integer order,Integer currentPage,Integer numberPerPage,String keyWord) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int totalNumber=faqDao.getFaqQuestionTotalNumber(faqQuestionType,keyWord);
        dataWrapper.setPage(page,totalNumber);
        List<FaqQuestion> faqQuestionList=faqDao.getFaqQuestionList(faqQuestionType,order,page.getCurrentNumber(),numberPerPage,keyWord);
        dataWrapper.setData(faqQuestionList);
        return dataWrapper;
    }

    @Override
    public DataWrapper<FaqQuestion> questionDetail(String token,Integer faqQuestionId, Integer currentPage, Integer numberPerPage) {
        DataWrapper<FaqQuestion> dataWrapper=new DataWrapper<FaqQuestion>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int totalNumber=faqDao.getAnswerTotalNum(faqQuestionId);
        dataWrapper.setPage(page,totalNumber);
        FaqQuestion faqQuestion=faqDao.questionDetail(faqQuestionId);
        List<FaqAnswer> faqAnswerList=faqDao.questionAnswerList(faqQuestionId,page.getCurrentNumber(),numberPerPage);
        faqQuestion.setFaqAnswerList(faqAnswerList);
        //判断是否已收藏
        if(token!=null){
            String userId=utilsDao.getUserID(token);
            if(redisService.SETS.sismember(userId+"问答收藏列表",faqQuestionId+"")){
                faqQuestion.setIsStar(1);
            }
        }
        dataWrapper.setData(faqQuestion);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<FaqQuestion>> myQuestion(String token, Integer currentPage, Integer numberPerPage) {
        DataWrapper<List<FaqQuestion>> dataWrapper=new DataWrapper<List<FaqQuestion>>();
        if(token==null) {
            return dataWrapper;
        }
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        String userId=utilsDao.getUserID(token);
        int totalNumber=faqDao.getMyQuestionTotalNum(userId);
        dataWrapper.setPage(page,totalNumber);
        List<FaqQuestion> faqQuestionList=faqDao.getMyQuestionList(userId,page.getCurrentNumber(),numberPerPage);
        dataWrapper.setData(faqQuestionList);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<FaqQuestion>> myAnswer(String token, Integer currentPage, Integer numberPerPage) {
        DataWrapper<List<FaqQuestion>> dataWrapper=new DataWrapper<List<FaqQuestion>>();
        if(token==null) {
            return dataWrapper;
        }
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        String userId=utilsDao.getUserID(token);
        //获取的是问题的总条数
        int totalNumber=faqDao.getAnswerNumGroupByQuestionId(userId);
        dataWrapper.setPage(page,totalNumber);
        List<Integer> questionIdList=faqDao.getFaqQuestionIdList(userId);
        List<FaqQuestion> faqQuestionList=null;
        if(questionIdList!=null&&questionIdList.size()!=0){
            faqQuestionList=faqDao.getMyAnswerList(questionIdList,page.getCurrentNumber(),numberPerPage,userId);
        }
        dataWrapper.setData(faqQuestionList);
        return dataWrapper;
    }






}
