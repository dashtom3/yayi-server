package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserPersonalInfo {
	private String userId;
	private String phone;
	private String trueName;
	private Integer sex;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String qq;
	private String userPic;
	private Integer type;
	private String companyName;
	private String part;
	private String workAddress;
	private String doctorPic;
	private Integer state;
	private String failReason;
	private Integer judge;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrueName() {
		return trueName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getDoctorPic() {
		return doctorPic;
	}

	public void setDoctorPic(String doctorPic) {
		this.doctorPic = doctorPic;
	}

	public Integer getJudge() {
		return judge;
	}

	public void setJudge(Integer judge) {
		this.judge = judge;
	}

	public UserPersonalInfo() {
		super();
	}

	public UserPersonalInfo(String userId, String phone, String trueName,
			Integer sex, Date birthday, String qq, String userPic,
			Integer type, String companyName, String part, String workAddress,
			String doctorPic, Integer state, String failReason, Integer judge) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.trueName = trueName;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.userPic = userPic;
		this.type = type;
		this.companyName = companyName;
		this.part = part;
		this.workAddress = workAddress;
		this.doctorPic = doctorPic;
		this.state = state;
		this.failReason = failReason;
		this.judge = judge;
	}

	@Override
	public String toString() {
		return "UserPersonalInfo [userId=" + userId + ", phone=" + phone
				+ ", trueName=" + trueName + ", sex=" + sex + ", birthday="
				+ birthday + ", qq=" + qq + ", userPic=" + userPic + ", type="
				+ type + ", companyName=" + companyName + ", part=" + part
				+ ", workAddress=" + workAddress + ", doctorPic=" + doctorPic
				+ ", state=" + state + ", failReason=" + failReason
				+ ", judge=" + judge + "]";
	}

}
