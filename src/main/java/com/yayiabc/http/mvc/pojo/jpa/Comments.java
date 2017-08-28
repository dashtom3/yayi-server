package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaojiang 评论信息表
 */
public class Comments extends BasePojo implements Serializable{
	private Integer commentId;
	
	private String sku;
	
	private String describey;
	
	private String commentContent;
	
	private Integer commentGrade;
	
	private String orderId;
	
	private Integer state;
	
	private String replyContent;
	
	private Date created;
	
	private Date updated;
	
	private String userName;
	
	private String userPhone;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescribey() {
		return describey;
	}

	public void setDescribey(String describey) {
		this.describey = describey;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(Integer commentGrade) {
		this.commentGrade = commentGrade;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Comments(Integer commentId, String sku, String describey,
			String commentContent, Integer commentGrade, String orderId,
			Integer state, String replyContent, Date created, Date updated,
			String userName, String userPhone) {
		super();
		this.commentId = commentId;
		this.sku = sku;
		this.describey = describey;
		this.commentContent = commentContent;
		this.commentGrade = commentGrade;
		this.orderId = orderId;
		this.state = state;
		this.replyContent = replyContent;
		this.created = created;
		this.updated = updated;
		this.userName = userName;
		this.userPhone = userPhone;
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
		return "Comments [commentId=" + commentId + ", sku=" + sku
				+ ", describey=" + describey + ", commentContent="
				+ commentContent + ", commentGrade=" + commentGrade
				+ ", orderId=" + orderId + ", state=" + state
				+ ", replyContent=" + replyContent + ", created=" + created
				+ ", updated=" + updated + ", userName=" + userName
				+ ", userPhone=" + userPhone + "]";
	}

	
	
}