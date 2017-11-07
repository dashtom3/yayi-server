package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class See {
private String trueName;
private String phone;
private String qbRget;
private String qbRout; 
@DateTimeFormat(pattern="yy-MM-dd HH:mm:ss")
private Date qbTime;
private String remark;
public String getTrueName() {
	return trueName;
}
public void setTrueName(String trueName) {
	this.trueName = trueName;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}


public String getQbRget() {
	return qbRget;
}
public void setQbRget(String qbRget) {
	this.qbRget = qbRget;
}
public String getQbRout() {
	return qbRout;
}
public void setQbRout(String qbRout) {
	this.qbRout = qbRout;
}
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
public Date getQbTime() {
	return qbTime;
}
public void setQbTime(Date qbTime) {
	this.qbTime = qbTime;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}

}
