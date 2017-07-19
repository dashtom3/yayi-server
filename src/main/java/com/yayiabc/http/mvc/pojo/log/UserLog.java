package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class UserLog {
	private Integer userLogId;
	private String administrator;
	private String userName;
	private String operate;
	private String arguments;
	private Date created;
	
	public Integer getUserLogId() {
		return userLogId;
	}
	public void setUserLogId(Integer userLogId) {
		this.userLogId = userLogId;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
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
	public UserLog(Integer userLogId, String administrator, String userName,
			String operate, String arguments, Date created) {
		super();
		this.userLogId = userLogId;
		this.administrator = administrator;
		this.userName = userName;
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
		return "UserLog [userLogId=" + userLogId + ", administrator="
				+ administrator + ", userName=" + userName + ", operate="
				+ operate + ", arguments=" + arguments + ", created=" + created
				+ "]";
	}
	
}
