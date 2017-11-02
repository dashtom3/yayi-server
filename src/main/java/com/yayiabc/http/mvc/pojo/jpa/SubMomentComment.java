package com.yayiabc.http.mvc.pojo.jpa;


import java.util.Date;

public class SubMomentComment {
    private Integer subMomentCommentId; //牙医圈评论id
    private String subMomentCommentContent; //牙医圈评论内容
    private String subUserId; //评论者id
    private String subUserName; //评论者的用户名
    private String subReplyUserId;//回复的人的id
    private String subReplyUserName;//回复的人的姓名
    private Date subMomentCommentTime;//回复的时间

    public Integer getSubMomentCommentId() {
        return subMomentCommentId;
    }

    public void setSubMomentCommentId(Integer subMomentCommentId) {
        this.subMomentCommentId = subMomentCommentId;
    }

    public String getSubMomentCommentContent() {
        return subMomentCommentContent;
    }

    public void setSubMomentCommentContent(String subMomentCommentContent) {
        this.subMomentCommentContent = subMomentCommentContent;
    }

    public String getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(String subUserId) {
        this.subUserId = subUserId;
    }

    public String getSubUserName() {
        return subUserName;
    }

    public void setSubUserName(String subUserName) {
        this.subUserName = subUserName;
    }

    public String getSubReplyUserId() {
        return subReplyUserId;
    }

    public void setSubReplyUserId(String subReplyUserId) {
        this.subReplyUserId = subReplyUserId;
    }

    public String getSubReplyUserName() {
        return subReplyUserName;
    }

    public void setSubReplyUserName(String subReplyUserName) {
        this.subReplyUserName = subReplyUserName;
    }

    public Date getSubMomentCommentTime() {
        return subMomentCommentTime;
    }

    public void setSubMomentCommentTime(Date subMomentCommentTime) {
        this.subMomentCommentTime = subMomentCommentTime;
    }

    public SubMomentComment() {
    }

    public SubMomentComment(Integer subMomentCommentId, String subMomentCommentContent, String subUserId, String subUserName, String subReplyUserId, String subReplyUserName, Date subMomentCommentTime) {
        this.subMomentCommentId = subMomentCommentId;
        this.subMomentCommentContent = subMomentCommentContent;
        this.subUserId = subUserId;
        this.subUserName = subUserName;
        this.subReplyUserId = subReplyUserId;
        this.subReplyUserName = subReplyUserName;
        this.subMomentCommentTime = subMomentCommentTime;
    }

    @Override
    public String toString() {
        return "SubMomentComment{" +
                "subMomentCommentId=" + subMomentCommentId +
                ", subMomentCommentContent='" + subMomentCommentContent + '\'' +
                ", subUserId='" + subUserId + '\'' +
                ", subUserName='" + subUserName + '\'' +
                ", subReplyUserId='" + subReplyUserId + '\'' +
                ", subReplyUserName='" + subReplyUserName + '\'' +
                ", subMomentCommentTime=" + subMomentCommentTime +
                '}';
    }
}
