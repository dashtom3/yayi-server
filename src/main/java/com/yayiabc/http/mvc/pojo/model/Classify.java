package com.yayiabc.http.mvc.pojo.model;


import java.util.List;



public class Classify {
	private	Integer oneId; 
	private String oneClassify;
	private  List<ClassifyTwo> classifyTwoList;
	
	
	public Integer getOneId() {
		return oneId;
	}

	public void setOneId(Integer oneId) {
		this.oneId = oneId;
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
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Classify(Integer oneId, String oneClassify,
			List<ClassifyTwo> classifyTwoList) {
		super();
		this.oneId = oneId;
		this.oneClassify = oneClassify;
		this.classifyTwoList = classifyTwoList;
	}
	@Override
	public String toString() {
		return "Classify [oneId=" + oneId + ", oneClassify=" + oneClassify
				+ ", classifyTwoList=" + classifyTwoList + "]";
	}
	
}
