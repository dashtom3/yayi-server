package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CottomsPost {
	private String userId;
	private Integer postId;
	private String headline;
	private Integer classify;
	private String freeContent;
	private String chargeContent;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date postTime;
	private String writer;
	private int readNumber;
	private int commentNum;
	private int zanNum;
	private List<Comments> commentsList;
	private String cover;
	private String printUrl;
	private Date replyTime;
	private Integer postStater;
	private Integer chargeNumber;
	private int isPraise;//是否点赞
	private int isCollect;//是否收藏
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public Integer getClassify() {
		return classify;
	}
	public void setClassify(Integer classify) {
		this.classify = classify;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	public String getChargeContent() {
		return chargeContent;
	}
	public void setChargeContent(String chargeContent) {
		this.chargeContent = chargeContent;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getReadNumber() {
		return readNumber;
	}
	public void setReadNumber(int readNumber) {
		this.readNumber = readNumber;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public int getZanNum() {
		return zanNum;
	}
	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}
	public List<Comments> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPrintUrl() {
		return printUrl;
	}
	public void setPrintUrl(String printUrl) {
		this.printUrl = printUrl;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getPostStater() {
		return postStater;
	}
	public void setPostStater(Integer postStater) {
		this.postStater = postStater;
	}
	public Integer getChargeNumber() {
		return chargeNumber;
	}
	public void setChargeNumber(Integer chargeNumber) {
		this.chargeNumber = chargeNumber;
	}
	public int getIsPraise() {
		return isPraise;
	}
	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}
	public int getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}
	@Override
	public String toString() {
		return "CottomsPost [userId=" + userId + ", postId=" + postId + ", headline=" + headline + ", classify="
				+ classify + ", freeContent=" + freeContent + ", chargeContent=" + chargeContent + ", postTime="
				+ postTime + ", writer=" + writer + ", readNumber=" + readNumber + ", commentNum=" + commentNum
				+ ", zanNum=" + zanNum + ", commentsList=" + commentsList + ", cover=" + cover + ", printUrl="
				+ printUrl + ", replyTime=" + replyTime + ", postStater=" + postStater + ", chargeNumber="
				+ chargeNumber + ", isPraise=" + isPraise + ", isCollect=" + isCollect + ", user=" + user + "]";
	}
	
	
}
