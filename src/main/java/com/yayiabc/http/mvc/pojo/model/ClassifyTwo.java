package com.yayiabc.http.mvc.pojo.model;

import java.util.List;


public class ClassifyTwo {
	private Integer twoId;  
	private String classifyTwoName;
	private List<ClassifyThree> classifyThreeList;
	
	public Integer getTwoId() {
		return twoId;
	}
	public void setTwoId(Integer twoId) {
		this.twoId = twoId;
	}
	public String getClassifyTwoName() {
		return classifyTwoName;
	}
	public void setClassifyTwoName(String classifyTwoName) {
		this.classifyTwoName = classifyTwoName;
	}
	public List<ClassifyThree> getClassifyThreeList() {
		return classifyThreeList;
	}
	public void setClassifyThreeList(List<ClassifyThree> classifyThreeList) {
		this.classifyThreeList = classifyThreeList;
	}
	public ClassifyTwo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClassifyTwo(Integer twoId, String classifyTwoName,
			List<ClassifyThree> classifyThreeList) {
		super();
		this.twoId = twoId;
		this.classifyTwoName = classifyTwoName;
		this.classifyThreeList = classifyThreeList;
	}
	@Override
	public String toString() {
		return "ClassifyTwo [twoId=" + twoId + ", classifyTwoName="
				+ classifyTwoName + ", classifyThreeList=" + classifyThreeList
				+ "]";
	}
	
	
}
