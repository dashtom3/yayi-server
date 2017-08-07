package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class ExcelEntry {
	private Integer benefitNum;
	private Date updated;
	private String benefitCode;
	public Integer getBenefitNum() {
		return benefitNum;
	}
	public void setBenefitNum(Integer benefitNum) {
		this.benefitNum = benefitNum;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getBenefitCode() {
		return benefitCode;
	}
	public void setBenefitCode(String benefitCode) {
		this.benefitCode = benefitCode;
	}
	public ExcelEntry(Integer benefitNum, Date updated, String benefitCode) {
		super();
		this.benefitNum = benefitNum;
		this.updated = updated;
		this.benefitCode = benefitCode;
	}
	public ExcelEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ExcelEntry [benefitNum=" + benefitNum + ", updated=" + updated
				+ ", benefitCode=" + benefitCode + "]";
	}
	
	
	
}
