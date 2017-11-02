package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.MomentComment;
import com.yayiabc.http.mvc.pojo.jpa.MomentCommentModel;
import com.yayiabc.http.mvc.pojo.jpa.SubMomentComment;

import java.util.List;

public interface MomentManageService {

    DataWrapper<Void> add(Moment moment,String token);

    DataWrapper<Void> delete(Integer momentId);

    DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage);


    DataWrapper<Void> addComment(MomentCommentModel momentCommentModel, String token);

    DataWrapper<Void> deleteComment(Integer momentCommentId);

    DataWrapper<List<SubMomentComment>> querySubCommentList(Integer momentCommentId);

    DataWrapper<Void> upvote(Integer momentCommentId);
}
