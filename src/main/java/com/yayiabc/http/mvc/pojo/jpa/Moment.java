package com.yayiabc.http.mvc.pojo.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Moment {
    private Integer momentId; //牙医圈id
    private String userId; //发表者id
    private String userName; //发表者姓名
    private String momentContent; //发表到牙医圈的文字内容
    private String momentPicture; //发表到牙医圈的图片
    private Integer momentContentId;//发表到朋友圈的内容的id,可能为空
    private String momentContentTitle;//发表到朋友圈的内容的标题
    private Integer momentType;//发表到朋友圈的内容的类型标识 (1.纯文字；2.纯文字+1张图；3.纯文字+2张图；4.纯文字+3张（4、5、6）；5.纯文字+6（7、8）张图；6.分享视频、7.病例、8.培训。8种情况)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date momentTime;//发表到朋友圈的时间
    private List<MomentComment> momentCommentList;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getMomentTime() {
        return momentTime;
    }

    public void setMomentTime(Date momentTime) {
        this.momentTime = momentTime;
    }

    public List<MomentComment> getMomentCommentList() {
        return momentCommentList;
    }

    public void setMomentCommentList(List<MomentComment> momentCommentList) {
        this.momentCommentList = momentCommentList;
    }

    public Moment() {
    }

    public Moment(Integer momentId, String userId, String userName, String momentContent, String momentPicture, Integer momentContentId, String momentContentTitle, Integer momentType, Date momentTime, List<MomentComment> momentCommentList) {
        this.momentId = momentId;
        this.userId = userId;
        this.userName = userName;
        this.momentContent = momentContent;
        this.momentPicture = momentPicture;
        this.momentContentId = momentContentId;
        this.momentContentTitle = momentContentTitle;
        this.momentType = momentType;
        this.momentTime = momentTime;
        this.momentCommentList = momentCommentList;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "momentId=" + momentId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", momentContent='" + momentContent + '\'' +
                ", momentPicture='" + momentPicture + '\'' +
                ", momentContentId=" + momentContentId +
                ", momentContentTitle='" + momentContentTitle + '\'' +
                ", momentType=" + momentType +
                ", momentTime=" + momentTime +
                ", momentCommentList=" + momentCommentList +
                '}';
    }
}
