package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CottomsComment {
	private int commentId;
	private String comment;
	private String byCritic;
	private Date commentTime;
	private String commentContent;
	private int commentFavour;
	private int postId;
	private List<CottomsReply> cottomsReplyList;
	private String userId;
	private String token;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getByCritic() {
		return byCritic;
	}
	public void setByCritic(String byCritic) {
		this.byCritic = byCritic;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getCommentFavour() {
		return commentFavour;
	}
	public void setCommentFavour(int commentFavour) {
		this.commentFavour = commentFavour;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public List<CottomsReply> getCottomsReplyList() {
		return cottomsReplyList;
	}
	public void setCottomsReplyList(List<CottomsReply> cottomsReplyList) {
		this.cottomsReplyList = cottomsReplyList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "CottomsComment [commentId=" + commentId + ", comment=" + comment + ", byCritic=" + byCritic
				+ ", commentTime=" + commentTime + ", commentContent=" + commentContent + ", commentFavour="
				+ commentFavour + ", postId=" + postId + ", cottomsReplyList=" + cottomsReplyList + ", userId=" + userId
				+ ", token=" + token + "]";
	}
	public CottomsComment(int commentId, String comment, String byCritic, Date commentTime, String commentContent,
			int commentFavour, int postId, List<CottomsReply> cottomsReplyList, String userId, String token) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.byCritic = byCritic;
		this.commentTime = commentTime;
		this.commentContent = commentContent;
		this.commentFavour = commentFavour;
		this.postId = postId;
		this.cottomsReplyList = cottomsReplyList;
		this.userId = userId;
		this.token = token;
	}
	public CottomsComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
