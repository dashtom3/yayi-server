package com.yayiabc.http.mvc.pojo.jpa;

public class UserToPost {
	private int id;
	private int postId;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserToPost [id=" + id + ", postId=" + postId + ", userId=" + userId + "]";
	}
	

}
