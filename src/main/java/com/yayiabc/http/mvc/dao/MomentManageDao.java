package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.pojo.jpa.User;
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

    Moment getMomentByMomentId(Integer momentId);

    User getUserByMomentId(Integer beCommentedId);

    User getUserBySubMomentId(Integer parentId);

    int addComment(@Param("beCommentedId")Integer beCommentedId,@Param("coment") Comment coment,@Param("parentId") Integer parentId,@Param("userId")String userId);

    List<SubComment> getMomentCommentList(Integer momentId);

    Map<String,String> getMomentTitleByFaq(Integer momentContentId);

    void addMomentZanNum(Integer typeId);

    void addMomentFirstZanNum(Integer parentId);

    void addMomentSecondZanNum(Integer presentId);

    void delMomentSecondZanNum(Integer presentId);

    void delMomentFirstZanNum(Integer parentId);

    void delMomentZanNum(Integer typeId);

    void deleteMomentComment(Integer momentId);

    void deleteMomentZan(@Param("momentId")Integer momentId,@Param("type")String type);
}
