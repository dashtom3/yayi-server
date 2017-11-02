package com.yayiabc.http.mvc.pojo.jpa;

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
	private Integer vedioPlayNumber;
	private Integer vedioCommentNumber;
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

	public Integer getVedioPlayNumber() {
		return vedioPlayNumber;
	}

	public void setVedioPlayNumber(Integer vedioPlayNumber) {
		this.vedioPlayNumber = vedioPlayNumber;
	}

	public Integer getVedioCommentNumber() {
		return vedioCommentNumber;
	}

	public void setVedioCommentNumber(Integer vedioCommentNumber) {
		this.vedioCommentNumber = vedioCommentNumber;
	}

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

	public VidManage(Integer viId, String vidName, String videoType, String vidRoute, Integer videoCategory, Integer vedioPlayNumber, Integer vedioCommentNumber, Date vedioTime, List<VedioComment> vedioCommentList) {
		this.viId = viId;
		this.vidName = vidName;
		this.videoType = videoType;
		this.vidRoute = vidRoute;
		this.videoCategory = videoCategory;
		this.vedioPlayNumber = vedioPlayNumber;
		this.vedioCommentNumber = vedioCommentNumber;
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
				", vedioPlayNumber=" + vedioPlayNumber +
				", vedioCommentNumber=" + vedioCommentNumber +
				", vedioTime=" + vedioTime +
				", vedioCommentList=" + vedioCommentList +
				'}';
	}
}
