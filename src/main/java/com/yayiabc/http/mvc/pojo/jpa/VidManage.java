package com.yayiabc.http.mvc.pojo.jpa;

public class VidManage {
	private Integer viId;
	private  String vidName;
	private String vidType;
	private String vidRoute;
	
	
	public Integer getViId() {
		return viId;
	}
	public void setViId(Integer viId) {
		this.viId = viId;
	}
	public String getVidName() {
		return vidName;
	}
	public void setVidName(String vidName) {
		this.vidName = vidName;
	}
	public String getVidType() {
		return vidType;
	}
	public void setVidType(String vidType) {
		this.vidType = vidType;
	}
	public String getVidRoute() {
		return vidRoute;
	}
	public void setVidRoute(String vidRoute) {
		this.vidRoute = vidRoute;
	}
	@Override
	public String toString() {
		return "VidManage [viId=" + viId + ", vidName=" + vidName
				+ ", vidType=" + vidType + ", vidRoute=" + vidRoute + "]";
	}
	
	

}
