package com.yayiabc.http.mvc.pojo.jpa;

public class refuseCauser {
	private String refuseCauser;
	private String postId;
	private Integer id;
	public String getRefuseCauser() {
		return refuseCauser;
	}
	public void setRefuseCauser(String refuseCauser) {
		this.refuseCauser = refuseCauser;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "refuseCauser [refuseCauser=" + refuseCauser + ", postId=" + postId + ", id=" + id + "]";
	}
	

}
