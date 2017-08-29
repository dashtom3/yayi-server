package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserWith {

	private  int withId;
	private String userId;
	private double aType;
	private double bType;
	private double cType;
	private double giveType;
	private String trueName;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",style="GMT+8")
	private Date created;
	private int sign;

	public int getWithId() {
		return withId;
	}

	public void setWithId(int withId) {
		this.withId = withId;
	}

	public String getUserId() {
		return userId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getaType() {
		return aType;
	}
	public void setaType(double aType) {
		this.aType = aType;
	}
	public double getbType() {
		return bType;
	}
	public void setbType(double bType) {
		this.bType = bType;
	}
	public double getcType() {
		return cType;
	}
	public void setcType(double cType) {
		this.cType = cType;
	}
	public double getGiveType() {
		return giveType;
	}
	public void setGiveType(double giveType) {
		this.giveType = giveType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
}
