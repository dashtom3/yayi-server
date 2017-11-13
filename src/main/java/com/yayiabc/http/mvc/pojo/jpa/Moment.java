package com.yayiabc.http.mvc.pojo.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Moment {
    private Integer momentId; //牙医圈id
    private String userId; //发表者id
    private String userName; //发表者姓名
    private String userPic;//发表者的头像
    private String momentContent; //发表到牙医圈的文字内容
    private String momentPicture; //发表到牙医圈的图片
    private Integer momentContentId;//发表到朋友圈的内容的id,可能为空
    private String momentContentTitle;//发表内容
    private Integer momentType;//发表到朋友圈的内容的类型标识 (1.纯文字；2.文字+图；3.分享视频、4.病例、5.培训。5种情况)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date momentTime;//发表到朋友圈的时间
    private Integer zanNumber;//点赞数
    private List<SubComment> subCommentList;//评论列表

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

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

    public String getMomentContent() {
        return momentContent;
    }

    public void setMomentContent(String momentContent) {
        this.momentContent = momentContent;
    }

    public String getMomentPicture() {
        return momentPicture;
    }

    public void setMomentPicture(String momentPicture) {
        this.momentPicture = momentPicture;
    }

    public Integer getMomentContentId() {
        return momentContentId;
    }

    public void setMomentContentId(Integer momentContentId) {
        this.momentContentId = momentContentId;
    }

    public String getMomentContentTitle() {
        return momentContentTitle;
    }

    public void setMomentContentTitle(String momentContentTitle) {
        this.momentContentTitle = momentContentTitle;
    }

    public Integer getMomentType() {
        return momentType;
    }

    public void setMomentType(Integer momentType) {
        this.momentType = momentType;
    }

    public Date getMomentTime() {
        return momentTime;
    }

    public void setMomentTime(Date momentTime) {
        this.momentTime = momentTime;
    }

    public Integer getZanNumber() {
        return zanNumber;
    }

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }

    public List<SubComment> getSubCommentList() {
        return subCommentList;
    }

    public void setSubCommentList(List<SubComment> subCommentList) {
        this.subCommentList = subCommentList;
    }

    public Moment() {
    }

    public Moment(Integer momentId, String userId, String userName, String userPic, String momentContent, String momentPicture, Integer momentContentId, String momentContentTitle, Integer momentType, Date momentTime, Integer zanNumber, List<SubComment> subCommentList) {
        this.momentId = momentId;
        this.userId = userId;
        this.userName = userName;
        this.userPic = userPic;
        this.momentContent = momentContent;
        this.momentPicture = momentPicture;
        this.momentContentId = momentContentId;
        this.momentContentTitle = momentContentTitle;
        this.momentType = momentType;
        this.momentTime = momentTime;
        this.zanNumber = zanNumber;
        this.subCommentList = subCommentList;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "momentId=" + momentId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPic='" + userPic + '\'' +
                ", momentContent='" + momentContent + '\'' +
                ", momentPicture='" + momentPicture + '\'' +
                ", momentContentId=" + momentContentId +
                ", momentContentTitle='" + momentContentTitle + '\'' +
                ", momentType=" + momentType +
                ", momentTime=" + momentTime +
                ", zanNumber=" + zanNumber +
                ", subCommentList=" + subCommentList +
                '}';
    }
}
