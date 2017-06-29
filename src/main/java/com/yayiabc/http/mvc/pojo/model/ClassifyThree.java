package com.yayiabc.http.mvc.pojo.model;


public class ClassifyThree {
	private Integer threeId;
	private String classifyThreeName;
	public Integer getThreeId() {
		return threeId;
	}
	public void setThreeId(Integer threeId) {
		this.threeId = threeId;
	}
	public String getClassifyThreeName() {
		return classifyThreeName;
	}
	public void setClassifyThreeName(String classifyThreeName) {
		this.classifyThreeName = classifyThreeName;
	}
	public ClassifyThree(Integer threeId, String classifyThreeName) {
		super();
		this.threeId = threeId;
		this.classifyThreeName = classifyThreeName;
	}
	public ClassifyThree() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ClassifyThree [threeId=" + threeId + ", classifyThreeName="
				+ classifyThreeName + "]";
	}
	
}
