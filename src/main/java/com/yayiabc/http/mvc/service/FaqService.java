package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.FaqAnswer;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;

import java.util.List;

public interface FaqService {
    DataWrapper<FaqQuestion> addQuestion(String token, FaqQuestion faqQuestion);

    DataWrapper<FaqAnswer> addAnswer(String token, FaqAnswer faqAnswer,Integer faqQuestionId);

    DataWrapper<List<FaqQuestion>> list(Integer faqQuestionType, Integer order,Integer currentPage,Integer numberPerPage);

    DataWrapper<FaqQuestion> questionDetail(String token,Integer faqQuestionId, Integer currentPage, Integer numberPerPage);

    DataWrapper<List<FaqQuestion>> myQuestion(String token, Integer currentPage, Integer numberPerPage);

    DataWrapper<List<FaqQuestion>> myAnswer(String token, Integer currentPage, Integer numberPerPage);

    DataWrapper<Void> star(String token, Integer faqQuestionId);
}
