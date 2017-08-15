package com.yayiabc.http.mvc.pojo.jpa;

public class AdvChart {
	private int advId;
    private String advName;
    private String advImg;
    private String advUrl;
    private String advType;
	public int getAdvId() {
		return advId;
	}
	public void setAdvId(int advId) {
		this.advId = advId;
	}
	public String getAdvName() {
		return advName;
	}
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	public String getAdvImg() {
		return advImg;
	}
	public void setAdvImg(String advImg) {
		this.advImg = advImg;
	}
	public String getAdvUrl() {
		return advUrl;
	}
	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}
	public String getAdvType() {
		return advType;
	}
	public void setAdvType(String advType) {
		this.advType = advType;
	}
	@Override
	public String toString() {
		return "AdvChart [advId=" + advId + ", advName=" + advName
				+ ", advImg=" + advImg + ", advUrl=" + advUrl + ", advType="
				+ advType + "]";
	}
	public AdvChart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
