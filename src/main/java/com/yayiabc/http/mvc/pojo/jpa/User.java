package com.yayiabc.http.mvc.pojo.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaojiang 用户表
 */
public class User extends BasePojo implements Serializable {
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

	private Integer qbBalance;//下单赠送的乾币（可提现）
    private Integer qbNotwtih;//除下单赠送的乾币之外的免费乾币（不可提现）   这个wtih有点坑
	private String reflect;

	private Certification certification;
	
	private CottomsPost cottomsPost;
	// private List<Refund> refundList;

	private SaleInfo saleinfo;

	private List<Ordera> orderaList;

	private List<QbRecord> qbRecordList;
	private int aQb;
	private int cQb;
	
	private Integer id;
	
	private List<UserWith> userWithList;
	
	private Integer day;
	
	private Integer week;

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQbNotwtih() {
		return qbNotwtih;  
	}

	public void setQbNotwtih(Integer qbNotwtih) {
		this.qbNotwtih = qbNotwtih;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserWith> getUserWithList() {
		return userWithList;
	}

	public void setUserWithList(List<UserWith> userWithList) {
		this.userWithList = userWithList;
	}

	public int getaQb() {
		return aQb;
	}

	public void setaQb(int aQb) {
		this.aQb = aQb;
	}


	public int getcQb() {
		return cQb;
	}

	public void setcQb(int cQb) {
		this.cQb = cQb;
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
		this.pwd = pwd;
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
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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

	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", saleId=" + saleId + ", phone=" + phone + ", pwd=" + pwd + ", identity="
				+ identity + ", trueName=" + trueName + ", sex=" + sex + ", userPic=" + userPic + ", birthday="
				+ birthday + ", qq=" + qq + ", qbBalance=" + qbBalance + ", qbNotwtih=" + qbNotwtih + ", reflect="
				+ reflect + ", certification=" + certification + ", cottomsPost=" + cottomsPost + ", saleinfo="
				+ saleinfo + ", orderaList=" + orderaList + ", qbRecordList=" + qbRecordList + ", aQb=" + aQb + ", cQb="
				+ cQb + ", id=" + id + ", userWithList=" + userWithList + ", day=" + day + ", week=" + week + "]";
	}

	public CottomsPost getCottomsPost() {
		return cottomsPost;
	}

	public void setCottomsPost(CottomsPost cottomsPost) {
		this.cottomsPost = cottomsPost;
	}

	

	



}