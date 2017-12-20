package com.yayiabc.http.mvc.pojo.model;

public class Invite {
	//邀请注册实体类
	private Integer userId;
	private Integer id;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Invite [userId=" + userId + ", id=" + id + "]";
	}
	
	
}
