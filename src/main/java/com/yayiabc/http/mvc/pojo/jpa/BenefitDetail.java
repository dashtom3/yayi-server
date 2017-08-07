package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class BenefitDetail {
	private Integer benefitCodeId;
	private Integer benefitId;
	private String benefitCode;
	private Integer state;//1表示可用，2表示不可用
	private Date benefitTime;
	private String benefitPerson;
	public Integer getBenefitCodeId() {
		return benefitCodeId;
	}
	public void setBenefitCodeId(Integer benefitCodeId) {
		this.benefitCodeId = benefitCodeId;
	}
	public Integer getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Integer benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefitCode() {
		return benefitCode;
	}
	public void setBenefitCode(String benefitCode) {
		this.benefitCode = benefitCode;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getBenefitTime() {
		return benefitTime;
	}
	public void setBenefitTime(Date benefitTime) {
		this.benefitTime = benefitTime;
	}
	public String getBenefitPerson() {
		return benefitPerson;
	}
	public void setBenefitPerson(String benefitPerson) {
		this.benefitPerson = benefitPerson;
	}
	public BenefitDetail(Integer benefitCodeId, Integer benefitId,
			String benefitCode, Integer state, Date benefitTime,
			String benefitPerson) {
		super();
		this.benefitCodeId = benefitCodeId;
		this.benefitId = benefitId;
		this.benefitCode = benefitCode;
		this.state = state;
		this.benefitTime = benefitTime;
		this.benefitPerson = benefitPerson;
	}
	public BenefitDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BenefitDetail [benefitCodeId=" + benefitCodeId + ", benefitId="
				+ benefitId + ", benefitCode=" + benefitCode + ", state="
				+ state + ", benefitTime=" + benefitTime + ", benefitPerson="
				+ benefitPerson + "]";
	}
	
	
	
	
}
