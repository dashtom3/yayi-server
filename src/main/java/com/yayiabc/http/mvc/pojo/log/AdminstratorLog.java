package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class AdminstratorLog {
	private Integer adminstratorLogId;
	private String adminstratorId;
	private String operate;
	private String arguments;
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
	public AdminstratorLog(Integer adminstratorLogId, String adminstratorId,
			String operate, String arguments, Date created) {
		super();
		this.adminstratorLogId = adminstratorLogId;
		this.adminstratorId = adminstratorId;
		this.operate = operate;
		this.arguments = arguments;
		this.created = created;
	}
	public AdminstratorLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminstratorLog [adminstratorLogId=" + adminstratorLogId
				+ ", adminstratorId=" + adminstratorId + ", operate=" + operate
				+ ", arguments=" + arguments + ", created=" + created + "]";
	}
	
	
}
