package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class VedioComment {
    private Integer vedioCommentId;
    private String vedioCommentContent;
    private String vedioCommentUserId;
    private String vedioCommentUserName;
    private String vedioCommentReplyUserId;
    private String vedioCommentReplyUserName;
    private Date vedioCommentTime;
    private Integer vedioUpvote;
    private Integer vedioCommentPreviousId;

    public Integer getVedioCommentId() {
        return vedioCommentId;
    }

    public void setVedioCommentId(Integer vedioCommentId) {
        this.vedioCommentId = vedioCommentId;
    }

    public String getVedioCommentContent() {
        return vedioCommentContent;
    }

    public void setVedioCommentContent(String vedioCommentContent) {
        this.vedioCommentContent = vedioCommentContent;
    }

    public String getVedioCommentUserId() {
        return vedioCommentUserId;
    }

    public void setVedioCommentUserId(String vedioCommentUserId) {
        this.vedioCommentUserId = vedioCommentUserId;
    }

    public String getVedioCommentUserName() {
        return vedioCommentUserName;
    }

    public void setVedioCommentUserName(String vedioCommentUserName) {
        this.vedioCommentUserName = vedioCommentUserName;
    }

    public String getVedioCommentReplyUserId() {
        return vedioCommentReplyUserId;
    }

    public void setVedioCommentReplyUserId(String vedioCommentReplyUserId) {
        this.vedioCommentReplyUserId = vedioCommentReplyUserId;
    }

    public String getVedioCommentReplyUserName() {
        return vedioCommentReplyUserName;
    }

    public void setVedioCommentReplyUserName(String vedioCommentReplyUserName) {
        this.vedioCommentReplyUserName = vedioCommentReplyUserName;
    }

    public Date getVedioCommentTime() {
        return vedioCommentTime;
    }

    public void setVedioCommentTime(Date vedioCommentTime) {
        this.vedioCommentTime = vedioCommentTime;
    }

    public Integer getVedioUpvote() {
        return vedioUpvote;
    }

    public void setVedioUpvote(Integer vedioUpvote) {
        this.vedioUpvote = vedioUpvote;
    }

    public Integer getVedioCommentPreviousId() {
        return vedioCommentPreviousId;
    }

    public void setVedioCommentPreviousId(Integer vedioCommentPreviousId) {
        this.vedioCommentPreviousId = vedioCommentPreviousId;
    }

    public VedioComment() {
    }

    public VedioComment(Integer vedioCommentId, String vedioCommentContent, String vedioCommentUserId, String vedioCommentUserName, String vedioCommentReplyUserId, String vedioCommentReplyUserName, Date vedioCommentTime, Integer vedioUpvote, Integer vedioCommentPreviousId) {
        this.vedioCommentId = vedioCommentId;
        this.vedioCommentContent = vedioCommentContent;
        this.vedioCommentUserId = vedioCommentUserId;
        this.vedioCommentUserName = vedioCommentUserName;
        this.vedioCommentReplyUserId = vedioCommentReplyUserId;
        this.vedioCommentReplyUserName = vedioCommentReplyUserName;
        this.vedioCommentTime = vedioCommentTime;
        this.vedioUpvote = vedioUpvote;
        this.vedioCommentPreviousId = vedioCommentPreviousId;
    }

    @Override
    public String toString() {
        return "VedioComment{" +
                "vedioCommentId=" + vedioCommentId +
                ", vedioCommentContent='" + vedioCommentContent + '\'' +
                ", vedioCommentUserId='" + vedioCommentUserId + '\'' +
                ", vedioCommentUserName='" + vedioCommentUserName + '\'' +
                ", vedioCommentReplyUserId='" + vedioCommentReplyUserId + '\'' +
                ", vedioCommentReplyUserName='" + vedioCommentReplyUserName + '\'' +
                ", vedioCommentTime=" + vedioCommentTime +
                ", vedioUpvote=" + vedioUpvote +
                ", vedioCommentPreviousId=" + vedioCommentPreviousId +
                '}';
    }
}
