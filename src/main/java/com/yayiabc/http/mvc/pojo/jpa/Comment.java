package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

public class Comment {
    private String userId;
    private String userName;
    private String userPic;
    private long commentId;
    private String commentContent;
    private Date commentTime;
    private int zan=0;//点赞数
    private int replyNumber=0;
    private int isZan=0;
    private List<SubComment> subCommentList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(int replyNumber) {
        this.replyNumber = replyNumber;
    }

    public int getIsZan() {
        return isZan;
    }

    public void setIsZan(int isZan) {
        this.isZan = isZan;
    }

    public List<SubComment> getSubCommentList() {
        return subCommentList;
    }

    public void setSubCommentList(List<SubComment> subCommentList) {
        this.subCommentList = subCommentList;
    }

    public Comment() {
    }

    public Comment(String userId, String userName, String userPic, long commentId, String commentContent, Date commentTime, int zan, int replyNumber, int isZan, List<SubComment> subCommentList) {
        this.userId = userId;
        this.userName = userName;
        this.userPic = userPic;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.zan = zan;
        this.replyNumber = replyNumber;
        this.isZan = isZan;
        this.subCommentList = subCommentList;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPic='" + userPic + '\'' +
                ", commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", zan=" + zan +
                ", replyNumber=" + replyNumber +
                ", isZan=" + isZan +
                ", subCommentList=" + subCommentList +
                '}';
    }
}
