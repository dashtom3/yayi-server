package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;

import java.util.List;

public interface CommentService {




    DataWrapper<Void> addCom(String token, String type,Integer beCommentedId, Comment comment);

    List<Comment> queryCom(String type,Integer beCommentedId,Integer currentPage,Integer numberPerPage);

    DataWrapper<Void> addSubCom(String token, Long preCommentId, SubComment subComment,String type);

    List<SubComment> querySubCom(Long preCommentId);

}
