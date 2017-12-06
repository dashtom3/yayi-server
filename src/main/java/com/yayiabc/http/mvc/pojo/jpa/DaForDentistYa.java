/**
 * 
 */
package com.yayiabc.http.mvc.pojo.jpa;

/**
 * @author Administrator
 *
 */
public class DaForDentistYa {
	private int id;
	private String title;
	private String context;
	private int classfy;
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
	public int getClassfy() {
		return classfy;
	}
	public void setClassfy(int classfy) {
		this.classfy = classfy;
	}
	@Override
	public String toString() {
		return "DaForDentistYa [id=" + id + ", title=" + title + ", context=" + context + ", classfy=" + classfy + "]";
	}
   
}
