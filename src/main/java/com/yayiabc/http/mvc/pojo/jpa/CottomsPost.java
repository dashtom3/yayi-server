package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CottomsPost {
	private String userId;
	private int postId;
	private String headline;
	private String classify;
	private String freeContent;
	private String chargeContent;
	@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
	private Date postTime;
	private String writer;
	private int readNumber;
	private int commentNumber;
	private int postFavour;
	private List<Comments> commentsList;
	private String cover;
	private String printUrl;
	private Date replyTime;
	private String token;
	private Integer postStater;
	private Integer chargeNumber;
	private String userPic;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	@Override
	public String toString() {
		return "CottomsPost [userId=" + userId + ", postId=" + postId + ", headline=" + headline + ", classify="
				+ classify + ", freeContent=" + freeContent + ", chargeContent=" + chargeContent + ", postTime="
				+ postTime + ", writer=" + writer + ", readNumber=" + readNumber + ", commentNumber=" + commentNumber
				+ ", postFavour=" + postFavour + ", commentsList=" + commentsList + ", cover=" + cover + ", printUrl="
				+ printUrl + ", replyTime=" + replyTime + ", token=" + token + ", postStater=" + postStater
				+ ", chargeNumber=" + chargeNumber + ", userPic=" + userPic + "]";
	}
	public CottomsPost(String userId, int postId, String headline, String classify, String freeContent,
			String chargeContent, Date postTime, String writer, int readNumber, int commentNumber, int postFavour,
			List<Comments> commentsList, String cover, String printUrl, Date replyTime, String token,
			Integer postStater, Integer chargeNumber, String userPic) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.headline = headline;
		this.classify = classify;
		this.freeContent = freeContent;
		this.chargeContent = chargeContent;
		this.postTime = postTime;
		this.writer = writer;
		this.readNumber = readNumber;
		this.commentNumber = commentNumber;
		this.postFavour = postFavour;
		this.commentsList = commentsList;
		this.cover = cover;
		this.printUrl = printUrl;
		this.replyTime = replyTime;
		this.token = token;
		this.postStater = postStater;
		this.chargeNumber = chargeNumber;
		this.userPic = userPic;
	}
	public CottomsPost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}