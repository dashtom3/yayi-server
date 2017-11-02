package com.yayiabc.http.mvc.pojo.jpa;

public class MomentCommentModel {
    private Integer momentId;
    private String momentCommentContent;
    private Integer momentCommentPreviousId;
    private String usrId;
    private String userName;
    private Integer replyUserId;
    private String replyUserName;

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public String getMomentCommentContent() {
        return momentCommentContent;
    }

    public void setMomentCommentContent(String momentCommentContent) {
        this.momentCommentContent = momentCommentContent;
    }

    public Integer getMomentCommentPreviousId() {
        return momentCommentPreviousId;
    }

    public void setMomentCommentPreviousId(Integer momentCommentPreviousId) {
        this.momentCommentPreviousId = momentCommentPreviousId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public MomentCommentModel() {
    }

    public MomentCommentModel(Integer momentId, String momentCommentContent, Integer momentCommentPreviousId, String usrId, String userName, Integer replyUserId, String replyUserName) {
        this.momentId = momentId;
        this.momentCommentContent = momentCommentContent;
        this.momentCommentPreviousId = momentCommentPreviousId;
        this.usrId = usrId;
        this.userName = userName;
        this.replyUserId = replyUserId;
        this.replyUserName = replyUserName;
    }

    @Override
    public String toString() {
        return "MomentComentModel{" +
                "momentId=" + momentId +
                ", momentCommentContent='" + momentCommentContent + '\'' +
                ", momentCommentPreviousId=" + momentCommentPreviousId +
                ", usrId=" + usrId +
                ", userName='" + userName + '\'' +
                ", replyUserId=" + replyUserId +
                ", replyUserName='" + replyUserName + '\'' +
                '}';
    }
}
