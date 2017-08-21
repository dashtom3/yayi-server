package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author xiaojiang 用户表
 */
public class User extends BasePojo {
	private String userId;

    private String saleId;
	private String phone;

	private String pwd;

	private Integer identity;

	private String trueName;

	private Integer sex;

	private String userPic;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String qq;

	private Integer qbBalance;

	private String reflect;

	private Certification certification;

	// private List<Refund> refundList;

	private SaleInfo saleinfo;

	private List<Ordera> orderaList;

	private List<QbRecord> qbRecordList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getReflect() {
		return reflect;
	}

	public void setReflect(String reflect) {
		this.reflect = reflect;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public String getTrueName() {
		return trueName;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName == null ? null : trueName.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic == null ? null : userPic.trim();
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
		this.qq = qq == null ? null : qq.trim();
	}

	public List<Ordera> getOrderaList() {
		return orderaList;
	}

	public void setOrderaList(List<Ordera> orderaList) {
		this.orderaList = orderaList;
	}

	public Integer getQbBalance() {
		return qbBalance;
	}

	public void setQbBalance(Integer qbBalance) {
		this.qbBalance = qbBalance;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public SaleInfo getSaleinfo() {
		return saleinfo;
	}

	public void setSaleinfo(SaleInfo saleinfo) {
		this.saleinfo = saleinfo;
	}

	public List<QbRecord> getQbRecordList() {
		return qbRecordList;
	}

	public void setQbRecordList(List<QbRecord> qbRecordList) {
		this.qbRecordList = qbRecordList;
	}

	public User() {
		super();
	}

	public User(String userId, String phone, String pwd, Integer identity,
			String trueName, Integer sex, String userPic, Date birthday,
			String qq, Integer qbBalance, String reflect,
			Certification certification, SaleInfo saleinfo,
			List<Ordera> orderaList, List<QbRecord> qbRecordList) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.pwd = pwd;
		this.identity = identity;
		this.trueName = trueName;
		this.sex = sex;
		this.userPic = userPic;
		this.birthday = birthday;
		this.qq = qq;
		this.qbBalance = qbBalance;
		this.reflect = reflect;
		this.certification = certification;
		this.saleinfo = saleinfo;
		this.orderaList = orderaList;
		this.qbRecordList = qbRecordList;
	}

	public User(String phone, String trueName, Integer sex, String userPic,
			Date birthday, String qq) {
		super();
		this.phone = phone;
		this.trueName = trueName;
		this.sex = sex;
		this.userPic = userPic;
		this.birthday = birthday;
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", phone=" + phone + ", pwd=" + pwd
				+ ", identity=" + identity + ", trueName=" + trueName
				+ ", sex=" + sex + ", userPic=" + userPic + ", birthday="
				+ birthday + ", qq=" + qq + ", qbBalance=" + qbBalance
				+ ", reflect=" + reflect + ", certification=" + certification
				+ ", saleinfo=" + saleinfo + ", orderaList=" + orderaList + "]";
	}

}