package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;

public class UserToken {
	private String token;
	private Integer userId;
	private Date loginDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public UserToken() {
	}

	public UserToken(String token, Integer userId, Date loginDate) {
		this.token = token;
		this.userId = userId;
		this.loginDate = loginDate;
	}
}
