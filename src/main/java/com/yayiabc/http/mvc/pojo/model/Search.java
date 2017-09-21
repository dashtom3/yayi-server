package com.yayiabc.http.mvc.pojo.model;

public class Search {
	private String oneClassify; 
	private String twoClassify;
	private String threeClassify;
	private String keyWord;
	private String itemBrandName;
	private Integer rule;
	private Integer itemBrandId;
	private Integer currentPage;
	private Integer numberPerPage;
	private Integer currentNumber;

	public String getOneClassify() {
		return oneClassify;
	}

	public void setOneClassify(String oneClassify) {
		this.oneClassify = oneClassify;
	}

	public String getTwoClassify() {
		return twoClassify;
	}

	public void setTwoClassify(String twoClassify) {
		this.twoClassify = twoClassify;
	}

	public String getThreeClassify() {
		return threeClassify;
	}

	public void setThreeClassify(String threeClassify) {
		this.threeClassify = threeClassify;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
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
		this.currentPage = currentPage;
	}

	public Integer getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(Integer numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public Search() {
	}

	public Search(String oneClassify, String twoClassify, String threeClassify, String keyWord, String itemBrandName, Integer rule, Integer itemBrandId, Integer currentPage, Integer numberPerPage, Integer currentNumber) {
		this.oneClassify = oneClassify;
		this.twoClassify = twoClassify;
		this.threeClassify = threeClassify;
		this.keyWord = keyWord;
		this.itemBrandName = itemBrandName;
		this.rule = rule;
		this.itemBrandId = itemBrandId;
		this.currentPage = currentPage;
		this.numberPerPage = numberPerPage;
		this.currentNumber = currentNumber;
	}
}
