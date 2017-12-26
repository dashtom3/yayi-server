package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class SubComment {
    private String userId;
    private String userName;
    private String userPic;
    private long subCommentId;
    private String commentContent;
    private Date commentTime;
    private String replyUserId;
    private String replyUserName;
    private int zan=0;
    private int isZan=0;

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

    public long getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(long subCommentId) {
        this.subCommentId = subCommentId;
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

    public int getIsZan() {
        return isZan;
    }

    public void setIsZan(int isZan) {
        this.isZan = isZan;
    }

    public SubComment() {
    }

    public SubComment(String userId, String userName, String userPic, long subCommentId, String commentContent, Date commentTime, String replyUserId, String replyUserName, int zan, int isZan) {
        this.userId = userId;
        this.userName = userName;
        this.userPic = userPic;
        this.subCommentId = subCommentId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.replyUserId = replyUserId;
        this.replyUserName = replyUserName;
        this.zan = zan;
        this.isZan = isZan;
    }

    @Override
    public String toString() {
        return "SubComment{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPic='" + userPic + '\'' +
                ", subCommentId=" + subCommentId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", replyUserId='" + replyUserId + '\'' +
                ", replyUserName='" + replyUserName + '\'' +
                ", zan=" + zan +
                ", isZan=" + isZan +
                '}';
    }
}
