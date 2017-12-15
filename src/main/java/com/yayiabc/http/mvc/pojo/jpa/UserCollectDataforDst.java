package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class UserCollectDataforDst {
   private String ucdId;
   private String userId;
   private String id;
   private Date collectTime;
public String getUcdId() {
	return ucdId;
}
public void setUcdId(String ucdId) {
	this.ucdId = ucdId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Date getCollectTime() {
	return collectTime;
}
public void setCollectTime(Date collectTime) {
	this.collectTime = collectTime;
}

@Override
public String toString() {
	return "UserCollectDataforDst [ucdId=" + ucdId + ", userId=" + userId + ", id=" + id + ", collectTime="
			+ collectTime + "]";
}
   
}
