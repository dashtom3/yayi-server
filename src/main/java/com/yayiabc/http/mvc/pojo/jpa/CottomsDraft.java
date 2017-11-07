package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CottomsDraft {
	private int postId;
	private String headline;//标题
	private String classify;//分类
	private String fees;//收费类型
	private Date draftTime;//保存时间
	private String freeContent;//免费内容
	private String chargeContent;//收费内容
	private String writer;//作者
	private int readNumber;//阅读数
	private int commentNumber;//评论数
	private int postFavour;//回复数
	private String cover;//封面
	private int userId;//用户
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public Date getDraftTime() {
		return draftTime;
	}
	public void setDraftTime(Date draftTime) {
		this.draftTime = draftTime;
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
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getPostFavour() {
		return postFavour;
	}
	public void setPostFavour(int postFavour) {
		this.postFavour = postFavour;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CottomsDraft [postId=" + postId + ", headline=" + headline + ", classify=" + classify + ", fees=" + fees
				+ ", draftTime=" + draftTime + ", freeContent=" + freeContent + ", chargeContent=" + chargeContent
				+ ", writer=" + writer + ", readNumber=" + readNumber + ", commentNumber=" + commentNumber
				+ ", postFavour=" + postFavour + ", cover=" + cover + ", userId=" + userId + "]";
	}
	public CottomsDraft(int postId, String headline, String classify, String fees, Date draftTime, String freeContent,
			String chargeContent, String writer, int readNumber, int commentNumber, int postFavour, String cover,
			int userId) {
		super();
		this.postId = postId;
		this.headline = headline;
		this.classify = classify;
		this.fees = fees;
		this.draftTime = draftTime;
		this.freeContent = freeContent;
		this.chargeContent = chargeContent;
		this.writer = writer;
		this.readNumber = readNumber;
		this.commentNumber = commentNumber;
		this.postFavour = postFavour;
		this.cover = cover;
		this.userId = userId;
	}
	public CottomsDraft() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
