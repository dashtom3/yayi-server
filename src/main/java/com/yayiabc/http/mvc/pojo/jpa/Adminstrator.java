package com.yayiabc.http.mvc.pojo.jpa;

public class Adminstrator {
	private Integer adminstratorId;
	private String phone;
	private String adminstratorPwd;
	private String trueName;
	
	public Integer getAdminstratorId() {
		return adminstratorId;
	}
	public void setAdminstratorId(Integer adminstratorId) {
		this.adminstratorId = adminstratorId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdminstratorPwd() {
		return adminstratorPwd;
	}
	public void setAdminstratorPwd(String adminstratorPwd) {
		this.adminstratorPwd = adminstratorPwd;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public Adminstrator(Integer adminstratorId, String phone,
			String adminstratorPwd, String trueName) {
		super();
		this.adminstratorId = adminstratorId;
		this.phone = phone;
		this.adminstratorPwd = adminstratorPwd;
		this.trueName = trueName;
	}
	public Adminstrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Adminstrator [adminstratorId=" + adminstratorId + ", phone="
				+ phone + ", adminstratorPwd=" + adminstratorPwd
				+ ", trueName=" + trueName + "]";
	}
	
}
