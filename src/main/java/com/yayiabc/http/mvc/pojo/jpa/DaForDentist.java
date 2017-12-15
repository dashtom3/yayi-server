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
	private String context;
	private String firstClassify;
	private String secondClassify;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
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
		return "DaForDentist [id=" + id + ", title=" + title + ", context=" + context + ", firstClassify="
				+ firstClassify + ", secondClassify=" + secondClassify + "]";
	}
	
   
}
