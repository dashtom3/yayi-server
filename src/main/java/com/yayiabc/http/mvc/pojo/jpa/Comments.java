package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 
 * @author xiaojiang 评论信息表
 */
public class Comments extends BasePojo {
	private Integer commentId;
	
	private String userName;
	
	private String userPhone;

	private String userId;
	
	private String orderId;

	private ItemInfo itemInfo;

	private Ordera ordera;

	private Integer commentGrade;

	private String commentContent;

	private String replyContent;
	
	private String recoveryState;

	
	
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public Ordera getOrdera() {
		return ordera;
	}

	public void setOrdera(Ordera ordera) {
		this.ordera = ordera;
	}

	public Integer getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(Integer commentGrade) {
		this.commentGrade = commentGrade;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getRecoveryState() {
		return recoveryState;
	}

	public void setRecoveryState(String recoveryState) {
		this.recoveryState = recoveryState;
	}

	public Comments(Integer commentId, String userName, String userPhone,
			String userId, String orderId, ItemInfo itemInfo, Ordera ordera,
			Integer commentGrade, String commentContent, String replyContent,
			String recoveryState) {
		super();
		this.commentId = commentId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userId = userId;
		this.orderId = orderId;
		this.itemInfo = itemInfo;
		this.ordera = ordera;
		this.commentGrade = commentGrade;
		this.commentContent = commentContent;
		this.replyContent = replyContent;
		this.recoveryState = recoveryState;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", userId=" + userId
				+ ", orderId=" + orderId + ", itemInfo=" + itemInfo
				+ ", ordera=" + ordera + ", commentGrade=" + commentGrade
				+ ", commentContent=" + commentContent + ", replyContent="
				+ replyContent + ", recoveryState=" + recoveryState + "]";
	}

	
	
	
}