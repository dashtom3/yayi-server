package com.yayiabc.http.mvc.pojo.model;

public class Search {
	private String oneClassify; 
	private String twoClassify;
	private String threeClassify;
	private String itemBrandName;
	private Integer rule;
	private Integer itemBrandId;
	private Integer currentPage;
	private Integer numberPerPage;
	private Integer currentNumber;
	
	
	
	public Integer getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Integer getItemBrandId() {
		return itemBrandId;
	}
	public void setItemBrandId(Integer itemBrandId) {
		this.itemBrandId = itemBrandId;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null||"".equals(currentPage)){
			currentPage=1;
		}{
			this.currentPage = currentPage;
		}
	}
	public Integer getNumberPerPage() {
		return numberPerPage;
	}
	public void setNumberPerPage(Integer numberPerPage) {
		if(numberPerPage==null||"".equals(numberPerPage)){
			numberPerPage=20;
		}else{
			this.numberPerPage = numberPerPage;
		}
	}
	public String getOneClassify() {
		return oneClassify;
	}
	public void setOneClassify(String oneClassify) {
		if(oneClassify==null||"".equals(oneClassify)){
			this.oneClassify = null;
		}else{
			this.oneClassify = oneClassify;
		}
	}
	public String getTwoClassify() {
		return twoClassify;
	}
	public void setTwoClassify(String twoClassify) {
		if(twoClassify==null||"".equals(twoClassify)){
			this.twoClassify = null;
		}else{
			this.twoClassify = twoClassify;
		}
	}
	public String getThreeClassify() {
		return threeClassify;
	}
	public void setThreeClassify(String threeClassify) {
		if(threeClassify==null||"".equals(threeClassify)){
			this.threeClassify = null;
		}else{
			this.threeClassify = threeClassify;
		}
	}
	public String getItemBrandName() {
		return itemBrandName;
	}
	public void setItemBrandName(String itemBrandName) {
		if(itemBrandName==null||"".equals(itemBrandName)){
			this.itemBrandName = null;
		}else {
			this.itemBrandName = itemBrandName;
		}
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		if(rule==null||"".equals(rule)){
			this.rule = null;
		}else {
			this.rule = rule;
		}
	}
	public Search(String oneClassify, String twoClassify, String threeClassify,
			String itemBrandName, Integer rule, Integer itemBrandId,
			Integer currentPage, Integer numberPerPage, Integer currentNumber) {
		super();
		this.oneClassify = oneClassify;
		this.twoClassify = twoClassify;
		this.threeClassify = threeClassify;
		this.itemBrandName = itemBrandName;
		this.rule = rule;
		this.itemBrandId = itemBrandId;
		this.currentPage = currentPage;
		this.numberPerPage = numberPerPage;
		this.currentNumber = currentNumber;
	}
	public Search() {
		super();
	}
	@Override
	public String toString() {
		return "Search [oneClassify=" + oneClassify + ", twoClassify="
				+ twoClassify + ", threeClassify=" + threeClassify
				+ ", itemBrandName=" + itemBrandName + ", rule=" + rule
				+ ", itemBrandId=" + itemBrandId + ", currentPage="
				+ currentPage + ", numberPerPage=" + numberPerPage
				+ ", currentNumber=" + currentNumber + "]";
	}
	
	
}
