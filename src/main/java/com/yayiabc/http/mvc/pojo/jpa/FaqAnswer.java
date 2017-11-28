package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 问答的回答实例
 */
public class FaqAnswer {
    private String userId;//回答者的id
    private String userName;//回答者的姓名
    private String userPic;//回答者的头像
    private Integer faqAnswerId;//回答记录的id
    private String faqAnswerContent;//回答的内容
    private Date faqAnswerTime;//回答的时间

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

    public Integer getFaqAnswerId() {
        return faqAnswerId;
    }

    public void setFaqAnswerId(Integer faqAnswerId) {
        this.faqAnswerId = faqAnswerId;
    }

    public String getFaqAnswerContent() {
        return faqAnswerContent;
    }

    public void setFaqAnswerContent(String faqAnswerContent) {
        this.faqAnswerContent = faqAnswerContent;
    }

    public Date getFaqAnswerTime() {
        return faqAnswerTime;
    }

    public void setFaqAnswerTime(Date faqAnswerTime) {
        this.faqAnswerTime = faqAnswerTime;
    }

    public FaqAnswer() {
    }

    public FaqAnswer(String userId, String userName, String userPic, Integer faqAnswerId, String faqAnswerContent, Date faqAnswerTime) {
        this.userId = userId;
        this.userName = userName;
        this.userPic = userPic;
        this.faqAnswerId = faqAnswerId;
        this.faqAnswerContent = faqAnswerContent;
        this.faqAnswerTime = faqAnswerTime;
    }

    @Override
    public String toString() {
        return "FaqAnswer{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPic='" + userPic + '\'' +
                ", faqAnswerId=" + faqAnswerId +
                ", faqAnswerContent='" + faqAnswerContent + '\'' +
                ", faqAnswerTime=" + faqAnswerTime +
                '}';
    }
}
