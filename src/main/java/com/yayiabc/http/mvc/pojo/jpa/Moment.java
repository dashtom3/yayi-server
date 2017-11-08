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
    private Integer momentType;//发表到朋友圈的内容的类型标识 (1.纯文字；2.纯文字+1张图；3.纯文字+2张图；4.纯文字+3张（4、5、6）；5.纯文字+6（7、8）张图；6.分享视频、7.病例、8.培训。8种情况)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date momentTime;//发表到朋友圈的时间
    private List<Comment> commentList;

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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Moment() {
    }

    public Moment(Integer momentId, String userId, String userName, String momentContent, String momentPicture, Integer momentContentId,  Integer momentType, Date momentTime, List<Comment> commentList) {
        this.momentId = momentId;
        this.userId = userId;
        this.userName = userName;
        this.momentContent = momentContent;
        this.momentPicture = momentPicture;
        this.momentContentId = momentContentId;
        this.momentType = momentType;
        this.momentTime = momentTime;
        this.commentList = commentList;
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
                ", momentType=" + momentType +
                ", momentTime=" + momentTime +
                ", commentList=" + commentList +
                '}';
    }
}
