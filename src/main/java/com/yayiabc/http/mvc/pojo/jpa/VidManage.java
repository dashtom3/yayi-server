package com.yayiabc.http.mvc.pojo.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VidManage implements Serializable{
	private Integer viId;
	private  String vidName;
	private String videoType;
	private String vidRoute;
	private Integer videoCategory;
	private Integer vedioCommentNumber;
	private String vedioPic;
	@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
	private Date vedioTime;
	private List<VedioComment> vedioCommentList;

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

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getVidRoute() {
		return vidRoute;
	}

	public void setVidRoute(String vidRoute) {
		this.vidRoute = vidRoute;
	}

	public Integer getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(Integer videoCategory) {
		this.videoCategory = videoCategory;
	}

	public Integer getVedioCommentNumber() {
		return vedioCommentNumber;
	}

	public void setVedioCommentNumber(Integer vedioCommentNumber) {
		this.vedioCommentNumber = vedioCommentNumber;
	}

	public String getVedioPic() {
		return vedioPic;
	}

	public void setVedioPic(String vedioPic) {
		this.vedioPic = vedioPic;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getVedioTime() {
		return vedioTime;
	}

	public void setVedioTime(Date vedioTime) {
		this.vedioTime = vedioTime;
	}

	public List<VedioComment> getVedioCommentList() {
		return vedioCommentList;
	}

	public void setVedioCommentList(List<VedioComment> vedioCommentList) {
		this.vedioCommentList = vedioCommentList;
	}

	public VidManage() {
	}

	public VidManage(Integer viId, String vidName, String videoType, String vidRoute, Integer videoCategory, Integer vedioCommentNumber, String vedioPic, Date vedioTime, List<VedioComment> vedioCommentList) {
		this.viId = viId;
		this.vidName = vidName;
		this.videoType = videoType;
		this.vidRoute = vidRoute;
		this.videoCategory = videoCategory;
		this.vedioCommentNumber = vedioCommentNumber;
		this.vedioPic = vedioPic;
		this.vedioTime = vedioTime;
		this.vedioCommentList = vedioCommentList;
	}

	@Override
	public String toString() {
		return "VidManage{" +
				"viId=" + viId +
				", vidName='" + vidName + '\'' +
				", videoType='" + videoType + '\'' +
				", vidRoute='" + vidRoute + '\'' +
				", videoCategory=" + videoCategory +
				", vedioCommentNumber=" + vedioCommentNumber +
				", vedioPic='" + vedioPic + '\'' +
				", vedioTime=" + vedioTime +
				", vedioCommentList=" + vedioCommentList +
				'}';
	}
}
