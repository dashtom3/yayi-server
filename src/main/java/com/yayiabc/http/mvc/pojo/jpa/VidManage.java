package com.yayiabc.http.mvc.pojo.jpa;

public class VidManage {
	private Integer viId;
	private  String vidName;
	private String videoType;
	private String vidRoute;
	
	public String getVideoType() {
		return videoType;
	}
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
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
	@Override
	public String toString() {
		return "VidManage [viId=" + viId + ", vidName=" + vidName
				+ ", videoType=" + videoType + "]";
	}
	public String getVidRoute() {
		return vidRoute;
	}
	public void setVidRoute(String vidRoute) {
		this.vidRoute = vidRoute;
	}
	

}
