package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class SubComment {
    private String userId;
    private String userName;
    private Long commentId;
    private String commentContent;
    private Date commentTime;
    private String replyUserId;
    private String replyUserName;
    private int zan=0;

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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
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

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public SubComment() {
    }

    public SubComment(String userId, String userName, Long commentId, String commentContent, Date commentTime, String replyUserId, String replyUserName, int zan) {
        this.userId = userId;
        this.userName = userName;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.replyUserId = replyUserId;
        this.replyUserName = replyUserName;
        this.zan = zan;
    }

    @Override
    public String toString() {
        return "SubComment{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", replyUserId='" + replyUserId + '\'' +
                ", replyUserName='" + replyUserName + '\'' +
                ", zan=" + zan +
                '}';
    }
}
