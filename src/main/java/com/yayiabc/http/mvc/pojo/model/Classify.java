package com.yayiabc.http.mvc.pojo.model;


import java.util.List;



public class Classify {
	private	Integer oneId;
	private Integer grade;
	private String oneClassify;
	private  List<ClassifyTwo> classifyTwoList;

	public Integer getOneId() {
		return oneId;
	}

	public void setOneId(Integer oneId) {
		this.oneId = oneId;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getOneClassify() {
		return oneClassify;
	}

	public void setOneClassify(String oneClassify) {
		this.oneClassify = oneClassify;
	}

	public List<ClassifyTwo> getClassifyTwoList() {
		return classifyTwoList;
	}

	public void setClassifyTwoList(List<ClassifyTwo> classifyTwoList) {
		this.classifyTwoList = classifyTwoList;
	}

	public Classify() {
	}

	public Classify(Integer oneId, Integer grade, String oneClassify, List<ClassifyTwo> classifyTwoList) {
		this.oneId = oneId;
		this.grade = grade;
		this.oneClassify = oneClassify;
		this.classifyTwoList = classifyTwoList;
	}

	@Override
	public String toString() {
		return "Classify{" +
				"oneId=" + oneId +
				", grade=" + grade +
				", oneClassify='" + oneClassify + '\'' +
				", classifyTwoList=" + classifyTwoList +
				'}';
	}
}
