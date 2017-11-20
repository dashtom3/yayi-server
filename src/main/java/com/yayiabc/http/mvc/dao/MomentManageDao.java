package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MomentManageDao {

    void addLower(Moment moment);

    void addHigh(Moment moment);

    void deleteMoment(Integer momentId);


    int getMomentTotalNumber(@Param("userId")String userId,@Param("type") Integer type);

    List<Moment> queryList(@Param("page")Page page,@Param("userId")String userId,@Param("type")Integer type);


    Map<String,String> getMomentTitleByVedio(Integer momentContentId);

    Map<String,String> getMomentTitleByPost(Integer momentContentId);

    Map<String,String> getMomentTitleByTrain(Integer momentContentId);

    String getUserIdByMomentId(Integer beCommentedId);
}
