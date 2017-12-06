package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.FaqAnswer;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqDao {
    int addQuestion(FaqQuestion faqQuestion);

    int addAnswer(@Param("faqAnswer")FaqAnswer faqAnswer,@Param("faqQuestionId") Integer faqQuestionId);

    List<FaqQuestion> getFaqQuestionList(@Param("faqQuestionType")Integer faqQuestionType,@Param("order") Integer order,@Param("currentNumber")Integer currentNumber,@Param("numberPerPage")Integer numberPerPage);

    int getFaqQuestionTotalNumber(@Param("faqQuestionType")Integer faqQuestionType);

    int getAnswerTotalNum(Integer faqQuestionId);

    FaqQuestion questionDetail(@Param("faqQuestionId")Integer faqQuestionId);

    List<FaqAnswer> questionAnswerList(@Param("faqQuestionId")Integer faqQuestionId,@Param("currentNumber") Integer currentNumber,@Param("numberPerPage") Integer numberPerPage);

    int getMyQuestionTotalNum(String userId);

    List<FaqQuestion> getMyQuestionList(@Param("userId")String userId,@Param("currentNumber") Integer currentNumber,@Param("numberPerPage") Integer numberPerPage);

    int getAnswerNumGroupByQuestionId(String userId);

    List<FaqQuestion> getMyAnswerList(@Param("questionIdList")List<Integer> questionIdList,@Param("currentNumber") Integer currentNumber,@Param("numberPerPage") Integer numberPerPage,@Param("userId")String userId);

    List<Integer> getFaqQuestionIdList(String userId);

    List<FaqQuestion> queryMyCollect(List<String> idList, Integer currentNumber, Integer numberPerPage);
}
