package com.yayiabc.http.mvc.pojo.model;

import java.util.Arrays;

public class diReviewKeyword {
   private String keyword_category_name;
   private String keyword_tag;
   private String keyword_desc;
   private String keyword_num;
   private String keyword_category_seq;
   private String keyword_type;
   private String[] keyword_comment_ids;
   private String keyword;
public String getKeyword_category_name() {
	return keyword_category_name;
}
public void setKeyword_category_name(String keyword_category_name) {
	this.keyword_category_name = keyword_category_name;
}
public String getKeyword_tag() {
	return keyword_tag;
}
public void setKeyword_tag(String keyword_tag) {
	this.keyword_tag = keyword_tag;
}
public String getKeyword_desc() {
	return keyword_desc;
}
public void setKeyword_desc(String keyword_desc) {
	this.keyword_desc = keyword_desc;
}
public String getKeyword_num() {
	return keyword_num;
}
public void setKeyword_num(String keyword_num) {
	this.keyword_num = keyword_num;
}
public String getKeyword_category_seq() {
	return keyword_category_seq;
}
public void setKeyword_category_seq(String keyword_category_seq) {
	this.keyword_category_seq = keyword_category_seq;
}
public String getKeyword_type() {
	return keyword_type;
}
public void setKeyword_type(String keyword_type) {
	this.keyword_type = keyword_type;
}
public String[] getKeyword_comment_ids() {
	return keyword_comment_ids;
}
public void setKeyword_comment_ids(String[] keyword_comment_ids) {
	this.keyword_comment_ids = keyword_comment_ids;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
@Override
public String toString() {
	return "diReviewKeyword [keyword_category_name=" + keyword_category_name + ", keyword_tag=" + keyword_tag
			+ ", keyword_desc=" + keyword_desc + ", keyword_num=" + keyword_num + ", keyword_category_seq="
			+ keyword_category_seq + ", keyword_type=" + keyword_type + ", keyword_comment_ids="
			+ Arrays.toString(keyword_comment_ids) + ", keyword=" + keyword + "]";
}
   
}
