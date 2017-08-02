package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class UserLog {
	private Integer userLogId;
	private String userId;
	private String phone;
	private String userName;
	private String operate;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public UserLog(Integer userLogId, String userId, String phone,
			String userName, String operate, Date created) {
		super();
		this.userLogId = userLogId;
		this.userId = userId;
		this.phone = phone;
		this.userName = userName;
		this.operate = operate;
		this.created = created;
	}
	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserLog [userLogId=" + userLogId + ", userId=" + userId
				+ ", phone=" + phone + ", userName=" + userName + ", operate="
				+ operate + ", created=" + created + "]";
	}
	
	
	
	
	
	
}
