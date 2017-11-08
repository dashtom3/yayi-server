package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

public class Comment {
    private String userId;
    private String userName;
    private long commentId;
    private String commentContent;
    private Date commentTime;
    private int zan=0;
    private int replyNumber=0;

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

    public Comment() {
    }

    public Comment(String userId, String userName, long commentId, String commentContent, Date commentTime, int zan, int replyNumber) {
        this.userId = userId;
        this.userName = userName;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.zan = zan;
        this.replyNumber = replyNumber;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", zan=" + zan +
                ", replyNumber=" + replyNumber +
                '}';
    }
}
