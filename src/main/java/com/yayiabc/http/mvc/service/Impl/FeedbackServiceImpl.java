package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.FeedbackDao;
import com.yayiabc.http.mvc.pojo.jpa.Feedback;
import com.yayiabc.http.mvc.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    FeedbackDao feedbackDao;

    @Override
    public DataWrapper<Void> addFeed(String message, String phone) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        int sign=feedbackDao.addFeed(message,phone);
        if(sign > 0){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> updateState(int feedId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        int sign=feedbackDao.updateState(feedId);
        if(sign > 0){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Feedback>> feedList(String phone, Integer state, Integer currentPage, Integer numberPerPage) {
        DataWrapper<List<Feedback>> dataWrapper=new DataWrapper<List<Feedback>>();
        Page page = new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int totalNumber=feedbackDao.getCount(phone,state);
        dataWrapper.setPage(page, totalNumber);
        List<Feedback> list=feedbackDao.feedList(phone,state,page);
        dataWrapper.setData(list);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> deleteFeed(int feedId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        int sign=feedbackDao.deleteFeed(feedId);
        if(sign > 0){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }
}
