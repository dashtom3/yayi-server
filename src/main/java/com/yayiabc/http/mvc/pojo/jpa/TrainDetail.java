package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;

public class TrainDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainDetailsId;
	private int trainId;
	private String setUpETime;
	private String trainType;
	private String trainContent;
	private String teacherIntroduce;
	private String sponsorTwoCode;
	private int share;
	private int collection;
	public int getTrainDetailsId() {
		return trainDetailsId;
	}
	public void setTrainDetailsId(int trainDetailsId) {
		this.trainDetailsId = trainDetailsId;
	}
	public String getSetUpETime() {
		return setUpETime;
	}
	public void setSetUpETime(String setUpETime) {
		this.setUpETime = setUpETime;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getTrainContent() {
		return trainContent;
	}
	
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}
	public String getTeacherIntroduce() {
		return teacherIntroduce;
	}
	public void setTeacherIntroduce(String teacherIntroduce) {
		this.teacherIntroduce = teacherIntroduce;
	}
	public String getSponsorTwoCode() {
		return sponsorTwoCode;
	}
	public void setSponsorTwoCode(String sponsorTwoCode) {
		this.sponsorTwoCode = sponsorTwoCode;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setShareAddOne(){
		this.share = share+1;
	}
	public void setCollectionAddOne(){
		this.collection = collection+1;
	}
	@Override
	public String toString() {
		return "TrainDetail [trainDetailsId=" + trainDetailsId + ", trainId=" + trainId + ", setUpETime=" + setUpETime
				+ ", trainType=" + trainType + ", trainContent=" + trainContent + ", teacherIntroduce="
				+ teacherIntroduce + ", sponsorTwoCode=" + sponsorTwoCode + ", share=" + share + ", collection="
				+ collection + "]";
	}
	
}
