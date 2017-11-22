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
	private Integer starNumber;//视频收藏数
	@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
	private Date vedioTime;
	private int isStar=0;

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

	public Integer getStarNumber() {
		return starNumber;
	}

	public void setStarNumber(Integer starNumber) {
		this.starNumber = starNumber;
	}

	public Date getVedioTime() {
		return vedioTime;
	}

	public void setVedioTime(Date vedioTime) {
		this.vedioTime = vedioTime;
	}

	public int getIsStar() {
		return isStar;
	}

	public void setIsStar(int isStar) {
		this.isStar = isStar;
	}

	public VidManage() {
	}

	public VidManage(Integer viId, String vidName, String videoType, String vidRoute, Integer videoCategory, Integer vedioCommentNumber, String vedioPic, Integer starNumber, Date vedioTime, int isStar) {
		this.viId = viId;
		this.vidName = vidName;
		this.videoType = videoType;
		this.vidRoute = vidRoute;
		this.videoCategory = videoCategory;
		this.vedioCommentNumber = vedioCommentNumber;
		this.vedioPic = vedioPic;
		this.starNumber = starNumber;
		this.vedioTime = vedioTime;
		this.isStar = isStar;
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
				", starNumber=" + starNumber +
				", vedioTime=" + vedioTime +
				", isStar=" + isStar +
				'}';
	}
}
