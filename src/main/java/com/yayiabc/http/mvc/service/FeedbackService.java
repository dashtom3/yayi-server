package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Feedback;

import java.util.List;

public interface FeedbackService {
    DataWrapper<Void> addFeed(String message,String phone);

    DataWrapper<Void> updateState(int feedId);

    DataWrapper<List<Feedback>> feedList(String phone,Integer state,Integer currentPage, Integer numberPerPage);

    DataWrapper<Void> deleteFeed(int feedId);
}
