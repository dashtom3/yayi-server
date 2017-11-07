package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;

import java.util.List;

public interface CommentService {




    DataWrapper<Void> addCom(String token, String type,Integer beCommentedId, Comment comment);

    DataWrapper<List<Comment>> queryCom(String type,Integer beCommentedId);

    DataWrapper<Void> addSubCom(String token, Long preCommentId, SubComment subComment);

    DataWrapper<List<SubComment>> querySubCom(Long preCommentId);

    DataWrapper<Void> zan(String type, Integer beCommentedId, Integer category, Long commentId);
}
