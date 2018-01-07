package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

/**
 * 提问的实例
 */
public class FaqQuestion {
    private Integer faqQuestionId;//提问id
    private Integer faqQuestionType;//提问类型(1外科2内科3修复4种植5正畸)
    private String faqQuestionTitle;//提问标题即内容
    private String faqQuestionContent;//提问的补充说明
    private String userId;//提问者的userId
    private String userName;//提问者的姓名
    private String userPic;//提问者的头像
    private Date faqQuestionTime;//提问时间
    private Integer faqAnswerNum;//回答次数，通过Mysql的触发器实现
    private int isStar;//是否点赞,1表示点赞0表示未点赞
    private int isCollect;
    private int collectNum;
    private int zanNum;
    private List<FaqAnswer> faqAnswerList;//回答的list

    public Integer getFaqQuestionId() {
        return faqQuestionId;
    }

    public void setFaqQuestionId(Integer faqQuestionId) {
        this.faqQuestionId = faqQuestionId;
    }

    public Integer getFaqQuestionType() {
        return faqQuestionType;
    }

    public void setFaqQuestionType(Integer faqQuestionType) {
        this.faqQuestionType = faqQuestionType;
    }

    public String getFaqQuestionTitle() {
        return faqQuestionTitle;
    }

    public void setFaqQuestionTitle(String faqQuestionTitle) {
        this.faqQuestionTitle = faqQuestionTitle;
    }

    public String getFaqQuestionContent() {
        return faqQuestionContent;
    }

    public void setFaqQuestionContent(String faqQuestionContent) {
        this.faqQuestionContent = faqQuestionContent;
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

    public Date getFaqQuestionTime() {
        return faqQuestionTime;
    }

    public void setFaqQuestionTime(Date faqQuestionTime) {
        this.faqQuestionTime = faqQuestionTime;
    }

    public Integer getFaqAnswerNum() {
        return faqAnswerNum;
    }

    public void setFaqAnswerNum(Integer faqAnswerNum) {
        this.faqAnswerNum = faqAnswerNum;
    }

    public int getIsStar() {
        return isStar;
    }

    public void setIsStar(int isStar) {
        this.isStar = isStar;
    }

    public List<FaqAnswer> getFaqAnswerList() {
        return faqAnswerList;
    }

    public void setFaqAnswerList(List<FaqAnswer> faqAnswerList) {
        this.faqAnswerList = faqAnswerList;
    }

    public FaqQuestion() {
    }
    

    public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}
	
	
	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getZanNum() {
		return zanNum;
	}

	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}
	
	
	public FaqQuestion(Integer faqQuestionId, Integer faqQuestionType, String faqQuestionTitle,
			String faqQuestionContent, String userId, String userName, String userPic, Date faqQuestionTime,
			Integer faqAnswerNum, int isStar, int isCollect, int collectNum, int zanNum,
			List<FaqAnswer> faqAnswerList) {
		super();
		this.faqQuestionId = faqQuestionId;
		this.faqQuestionType = faqQuestionType;
		this.faqQuestionTitle = faqQuestionTitle;
		this.faqQuestionContent = faqQuestionContent;
		this.userId = userId;
		this.userName = userName;
		this.userPic = userPic;
		this.faqQuestionTime = faqQuestionTime;
		this.faqAnswerNum = faqAnswerNum;
		this.isStar = isStar;
		this.isCollect = isCollect;
		this.collectNum = collectNum;
		this.zanNum = zanNum;
		this.faqAnswerList = faqAnswerList;
	}

	@Override
	public String toString() {
		return "FaqQuestion [faqQuestionId=" + faqQuestionId + ", faqQuestionType=" + faqQuestionType
				+ ", faqQuestionTitle=" + faqQuestionTitle + ", faqQuestionContent=" + faqQuestionContent + ", userId="
				+ userId + ", userName=" + userName + ", userPic=" + userPic + ", faqQuestionTime=" + faqQuestionTime
				+ ", faqAnswerNum=" + faqAnswerNum + ", isStar=" + isStar + ", isCollect=" + isCollect + ", collectNum="
				+ collectNum + ", zanNum=" + zanNum + ", faqAnswerList=" + faqAnswerList + "]";
	}

	

 
	
}
