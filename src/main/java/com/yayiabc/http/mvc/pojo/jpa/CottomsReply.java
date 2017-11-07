package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CottomsReply {
	private int replyId;
	private String replyer;
	private String byReply;
	private String replyContent;
	private Date replyTime;
	private int replyFavour;
	private int commentId;
	private String userId;
	private String token;
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getByReply() {
		return byReply;
	}
	public void setByReply(String byReply) {
		this.byReply = byReply;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public int getReplyFavour() {
		return replyFavour;
	}
	public void setReplyFavour(int replyFavour) {
		this.replyFavour = replyFavour;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
		return "CottomsReply [replyId=" + replyId + ", replyer=" + replyer + ", byReply=" + byReply + ", replyContent="
				+ replyContent + ", replyTime=" + replyTime + ", replyFavour=" + replyFavour + ", commentId="
				+ commentId + ", userId=" + userId + ", token=" + token + "]";
	}
	public CottomsReply(int replyId, String replyer, String byReply, String replyContent, Date replyTime,
			int replyFavour, int commentId, String userId, String token) {
		super();
		this.replyId = replyId;
		this.replyer = replyer;
		this.byReply = byReply;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyFavour = replyFavour;
		this.commentId = commentId;
		this.userId = userId;
		this.token = token;
	}
	public CottomsReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}