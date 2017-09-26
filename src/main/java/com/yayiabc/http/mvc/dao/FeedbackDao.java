package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackDao {
    int addFeed(@Param("message")String message,@Param("phone")String phone);

    int updateState(@Param("feedId")int feedId);

    List<Feedback> feedList(@Param("phone")String phone, @Param("state")Integer state,@Param("page")Page page);

    int getCount(@Param("phone")String phone, @Param("state")Integer state);

    int deleteFeed(@Param("feedId")int feedId);
}
