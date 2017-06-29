package com.yayiabc.http.mvc.pojo.model;

public class UserToken {
	private String token;
	private String userId;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UserToken(String token, String userId) {
		super();
		this.token = token;
		this.userId = userId;
	}
	public UserToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserToken [token=" + token + ", userId=" + userId + "]";
	}
	
}
