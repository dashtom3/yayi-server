package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class UserLog {
	private Integer userLogId;
	private String userId;
	private String operate;
	private String arguments;
	private Date created;
	public Integer getUserLogId() {
		return userLogId;
	}
	public void setUserLogId(Integer userLogId) {
		this.userLogId = userLogId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getArguments() {
		return arguments;
	}
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public UserLog(Integer userLogId, String userId, String operate,
			String arguments, Date created) {
		super();
		this.userLogId = userLogId;
		this.userId = userId;
		this.operate = operate;
		this.arguments = arguments;
		this.created = created;
	}
	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserLog [userLogId=" + userLogId + ", userId=" + userId
				+ ", operate=" + operate + ", arguments=" + arguments
				+ ", created=" + created + "]";
	}
	
	
	
}
