package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.MomentComment;
import com.yayiabc.http.mvc.pojo.jpa.MomentCommentModel;
import com.yayiabc.http.mvc.pojo.jpa.SubMomentComment;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentManageDao {

    void addLower(Moment moment);

    void addHigh(Moment moment);

    void deleteMoment(Integer momentId);

    void deleteMomentComment(Integer momentId);

    int getMomentTotalNumber();

    List<Moment> queryList(Page page);

    int addComment(MomentCommentModel momentCommentModel);

    void deleteComment(Integer momentCommentId);

    List<SubMomentComment> querySubCommentList(Integer momentCommentId);


    int upvote(Integer momentCommentId);
}
