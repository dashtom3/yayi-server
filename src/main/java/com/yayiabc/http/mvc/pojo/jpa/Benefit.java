package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Benefit {
	private Integer benefitId;
	private String benefitName;
	private Integer benefitQb;//可兑换乾币数
	private Integer benefitNum;//优惠码总数
	private Integer benefitValueNum;
	private Date updated;
	private Integer enable;
	private Date created;
	public Integer getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Integer benefitId) {
		this.benefitId = benefitId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public Integer getBenefitQb() {
		return benefitQb;
	}
	public void setBenefitQb(Integer benefitQb) {
		this.benefitQb = benefitQb;
	}
	public Integer getBenefitNum() {
		return benefitNum;
	}
	public void setBenefitNum(Integer benefitNum) {
		this.benefitNum = benefitNum;
	}
	public Integer getBenefitValueNum() {
		return benefitValueNum;
	}
	public void setBenefitValueNum(Integer benefitValueNum) {
		this.benefitValueNum = benefitValueNum;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Benefit(Integer benefitId, String benefitName, Integer benefitQb,
			Integer benefitNum, Integer benefitValueNum, Date updated,
			Integer enable, Date created) {
		super();
		this.benefitId = benefitId;
		this.benefitName = benefitName;
		this.benefitQb = benefitQb;
		this.benefitNum = benefitNum;
		this.benefitValueNum = benefitValueNum;
		this.updated = updated;
		this.enable = enable;
		this.created = created;
	}
	public Benefit() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Benefit [benefitId=" + benefitId + ", benefitName="
				+ benefitName + ", benefitQb=" + benefitQb + ", benefitNum="
				+ benefitNum + ", benefitValueNum=" + benefitValueNum
				+ ", updated=" + updated + ", enable=" + enable + ", created="
				+ created + "]";
	}
	
	
	
}
