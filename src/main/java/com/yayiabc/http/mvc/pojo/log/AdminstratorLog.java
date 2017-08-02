package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class AdminstratorLog {
	private Integer adminstratorLogId;
	private String adminstratorId;
	private String phone;
	private String userName;
	private String operate;
	private Date created;
	public Integer getAdminstratorLogId() {
		return adminstratorLogId;
	}
	public void setAdminstratorLogId(Integer adminstratorLogId) {
		this.adminstratorLogId = adminstratorLogId;
	}
	public String getAdminstratorId() {
		return adminstratorId;
	}
	public void setAdminstratorId(String adminstratorId) {
		this.adminstratorId = adminstratorId;
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
	public AdminstratorLog(Integer adminstratorLogId, String adminstratorId,
			String phone, String userName, String operate, Date created) {
		super();
		this.adminstratorLogId = adminstratorLogId;
		this.adminstratorId = adminstratorId;
		this.phone = phone;
		this.userName = userName;
		this.operate = operate;
		this.created = created;
	}
	public AdminstratorLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminstratorLog [adminstratorLogId=" + adminstratorLogId
				+ ", adminstratorId=" + adminstratorId + ", phone=" + phone
				+ ", userName=" + userName + ", operate=" + operate
				+ ", created=" + created + "]";
	}
	
	
	
	
}
