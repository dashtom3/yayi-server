package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Train  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainId;
	private String trainPic;
	private String trainName;
	private String trainPrice;
	private int trainNum;
	private Date created;
	private String teacherName;
	private String trainCtime;
	private String  classly;
	private String trainEtime;

	private TrainDetail trainDetail;


	public TrainDetail getTrainDetail() {
		return trainDetail;
	}


	public void setTrainDetail(TrainDetail trainDetail) {
		this.trainDetail = trainDetail;
	}


	public String getClassly() {
		return classly;
	}


	public void setClassly(String classly) {
		this.classly = classly;
	}


	public String getTrainCtime() {
		return trainCtime;
	}


	public void setTrainCtime(String trainCtime) {
		this.trainCtime = trainCtime;
	}


	public String getTrainEtime() {
		return trainEtime;
	}


	public void setTrainEtime(String trainEtime) {
		this.trainEtime = trainEtime;
	}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public String getTrainPic() {
		return trainPic;
	}


	public void setTrainPic(String trainPic) {
		this.trainPic = trainPic;
	}







	public int getTrainNum() {
		return trainNum;
	}
	public void setTrainNum(int trainNum) {
		this.trainNum = trainNum;
	}
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainPrice() {
		return trainPrice;
	}
	public void setTrainPrice(String trainPrice) {
		this.trainPrice = trainPrice;
	}//yyyyMMddHHmmss
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreated() {
		return created;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public void setCreated(Date created) {
		this.created = created;
	}


	@Override
	public String toString() {
		return "Train [trainId=" + trainId + ", trainPic=" + trainPic + ", trainName=" + trainName + ", trainPrice="
				+ trainPrice + ", trainNum=" + trainNum + ", created=" + created + ", teacherName=" + teacherName
				+ ", trainCtime=" + trainCtime + ", classly=" + classly + ", trainEtime=" + trainEtime
				+ ", trainDetail=" + trainDetail + "]";
	}





}
