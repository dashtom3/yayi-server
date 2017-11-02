package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

public class MomentComment {
    private Integer momentCommentId; //牙医圈评论id
    private String momentCommentContent; //牙医圈评论内容
    private String commentUserId; //评论者id
    private String commentUserName; //评论者的用户名
    private Date momentCommentTime;//评论动态的时间
    private Integer upvote;//评论动态的点赞数
    private List<SubMomentComment> subMomentCommentList; //子评论

    public Integer getMomentCommentId() {
        return momentCommentId;
    }

    public void setMomentCommentId(Integer momentCommentId) {
        this.momentCommentId = momentCommentId;
    }

    public String getMomentCommentContent() {
        return momentCommentContent;
    }

    public void setMomentCommentContent(String momentCommentContent) {
        this.momentCommentContent = momentCommentContent;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public Date getMomentCommentTime() {
        return momentCommentTime;
    }

    public void setMomentCommentTime(Date momentCommentTime) {
        this.momentCommentTime = momentCommentTime;
    }

    public Integer getUpvote() {
        return upvote;
    }

    public void setUpvote(Integer upvote) {
        this.upvote = upvote;
    }

    public List<SubMomentComment> getSubMomentCommentList() {
        return subMomentCommentList;
    }

    public void setSubMomentCommentList(List<SubMomentComment> subMomentCommentList) {
        this.subMomentCommentList = subMomentCommentList;
    }

    public MomentComment() {
    }

    public MomentComment(Integer momentCommentId, String momentCommentContent, String commentUserId, String commentUserName, Date momentCommentTime, Integer upvote, List<SubMomentComment> subMomentCommentList) {
        this.momentCommentId = momentCommentId;
        this.momentCommentContent = momentCommentContent;
        this.commentUserId = commentUserId;
        this.commentUserName = commentUserName;
        this.momentCommentTime = momentCommentTime;
        this.upvote = upvote;
        this.subMomentCommentList = subMomentCommentList;
    }
}
