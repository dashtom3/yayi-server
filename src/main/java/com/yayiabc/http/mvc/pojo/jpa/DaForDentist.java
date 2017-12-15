/**
 * 
 */
package com.yayiabc.http.mvc.pojo.jpa;

/**
 * @author Administrator
 *
 */
public class DaForDentist {
	private int id;
	private String title;
	private String content;
	private String firstClassify;
	private String secondClassify;
	private String browseNumber;
	private String isCollect; //1是收藏  0是未收藏
	
	
	public String getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}
	public String getBrowseNumber() {
		return browseNumber;
	}
	public void setBrowseNumber(String browseNumber) {
		this.browseNumber = browseNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFirstClassify() {
		return firstClassify;
	}
	public void setFirstClassify(String firstClassify) {
		this.firstClassify = firstClassify;
	}
	public String getSecondClassify() {
		return secondClassify;
	}
	public void setSecondClassify(String secondClassify) {
		this.secondClassify = secondClassify;
	}
	@Override
	public String toString() {
		return "DaForDentist [id=" + id + ", title=" + title + ", content=" + content + ", firstClassify="
				+ firstClassify + ", secondClassify=" + secondClassify + ", browseNumber=" + browseNumber
				+ ", isCollect=" + isCollect + "]";
	}
	
	
}
