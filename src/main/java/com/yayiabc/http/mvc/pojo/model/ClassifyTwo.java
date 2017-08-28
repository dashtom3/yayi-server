package com.yayiabc.http.mvc.pojo.model;

public class ClassifyTwo {
	private Integer twoId;
	private Integer classifyGrade;
	private String classifyTwoName;

	public Integer getTwoId() {
		return twoId;
	}

	public void setTwoId(Integer twoId) {
		this.twoId = twoId;
	}

	public Integer getClassifyGrade() {
		return classifyGrade;
	}

	public void setClassifyGrade(Integer classifyGrade) {
		this.classifyGrade = classifyGrade;
	}

	public String getClassifyTwoName() {
		return classifyTwoName;
	}

	public void setClassifyTwoName(String classifyTwoName) {
		this.classifyTwoName = classifyTwoName;
	}

	public ClassifyTwo() {
	}

	public ClassifyTwo(Integer twoId, Integer classifyGrade, String classifyTwoName) {
		this.twoId = twoId;
		this.classifyGrade = classifyGrade;
		this.classifyTwoName = classifyTwoName;
	}

	@Override
	public String toString() {
		return "ClassifyTwo{" +
				"twoId=" + twoId +
				", classifyGrade=" + classifyGrade +
				", classifyTwoName='" + classifyTwoName + '\'' +
				'}';
	}
}
