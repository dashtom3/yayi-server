package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class ManagerLog {
	private Integer managerLogId;
	private String administrator;
	private String userName;
	private String operate;
	private String arguments;
	private Date created;
	public ManagerLog(Integer managerLogId, String administrator,
			String userName, String operate, String arguments, Date created) {
		super();
		this.managerLogId = managerLogId;
		this.administrator = administrator;
		this.userName = userName;
		this.operate = operate;
		this.arguments = arguments;
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
				+ operate + ", arguments=" + arguments + ", created=" + created
				+ "]";
	}
	
}
