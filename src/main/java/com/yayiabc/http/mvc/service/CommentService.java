package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;

import java.util.List;

public interface CommentService {




    DataWrapper<Object> addCom(String token, String type,Integer beCommentedId, Comment comment,Integer parentId);

    List<Comment> queryCom(String type, Integer beCommentedId,Integer currentPage,Integer numberPerPage);


    List<SubComment> querySubCom(String type, Integer beCommentedId,long preCommentId,Integer currentPage,Integer numberPerPage);

    DataWrapper<Void> delete(String type, String beCommentedId, Integer parentId, Integer presentId);
}
