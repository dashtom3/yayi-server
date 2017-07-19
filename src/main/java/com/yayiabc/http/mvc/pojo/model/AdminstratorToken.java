package com.yayiabc.http.mvc.pojo.model;

public class AdminstratorToken {
	private Integer adminstratorId;
	private String adminstratorToken;
	public Integer getAdminstratorId() {
		return adminstratorId;
	}
	public void setAdminstratorId(Integer adminstratorId) {
		this.adminstratorId = adminstratorId;
	}
	public String getAdminstratorToken() {
		return adminstratorToken;
	}
	public void setAdminstratorToken(String adminstratorToken) {
		this.adminstratorToken = adminstratorToken;
	}
	public AdminstratorToken(Integer adminstratorId, String adminstratorToken) {
		super();
		this.adminstratorId = adminstratorId;
		this.adminstratorToken = adminstratorToken;
	}
	public AdminstratorToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminstratorToken [adminstratorId=" + adminstratorId
				+ ", adminstratorToken=" + adminstratorToken + "]";
	}
	
}
