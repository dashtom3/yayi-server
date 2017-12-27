package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    void addComment(@Param("userId")String userId,@Param("comment") Comment comment,@Param("beCommentedId") Integer beCommentedId,@Param("num") int num);

    void addSubComment(@Param("userId")String userId,@Param("comment") Comment comment,@Param("parentId") Integer parentId);

    User getUserByPostId(Integer beCommentedId);

    User getUserByTopCommentId(Integer parentId);

    int getCommentTotalNum(@Param("numberType")int numberType,@Param("beCommentedId") Integer beCommentedId);


    List<Comment> getCommentList(@Param("numberType")int numberType,@Param("beCommentedId") Integer beCommentedId,@Param("page") Page page,@Param("order")Integer order);

    void deleteYayiCom(Integer parentId);

    void deleteSubComment(Integer presentId);

    void deleteComment(Integer parentId);

    void deleteWithSubComment(Integer parentId);

    int getCommentNum(@Param("becommentedId")String becommentedId,@Param("type")Integer type);

    void updateCommentReplyNum(Integer parentId);


    void addTopCommentZanNum(Integer parentId);

    void addSubCommentZanNum(Integer presentId);

    void delSubCommentZanNum(Integer presentId);

    void delTopCommentZanNum(Integer parentId);

    void addCommentNumberToVideo(Integer beCommentedId);

    void addCommentNumberToCottom(Integer beCommentedId);

    void delCommentNumberToVideo(String beCommentedId);

    void delCommentNumberToCottom(String beCommentedId);

    void delCommentReplyNum(Integer parentId);
}
