package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class ManagerLog {
	private Integer managerLogId;
	private String administrator;
	private String userName;
	private String operate;
	private Date created;
	public Integer getManagerLogId() {
		return managerLogId;
	}
	public void setManagerLogId(Integer managerLogId) {
		this.managerLogId = managerLogId;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public ManagerLog(Integer managerLogId, String administrator,
			String userName, String operate, Date created) {
		super();
		this.managerLogId = managerLogId;
		this.administrator = administrator;
		this.userName = userName;
		this.operate = operate;
		this.created = created;
	}
	public ManagerLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ManagerLog [managerLogId=" + managerLogId + ", administrator="
				+ administrator + ", userName=" + userName + ", operate="
				+ operate + ", created=" + created + "]";
	}
	
}
