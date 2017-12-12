package com.yayiabc.http.mvc.pojo.model;

import java.util.Arrays;

public class DetailInfo {
    private String tag;
    private String detail_url;
    private String type;
    private String price;
    private String service_rating;
    private String technology_rating;
    private String image_num;
    private String comment_num;
    private String navi_location;
    private String shop_hours;
    private String description;
    private diReviewKeyword[] di_review_keyword;
    
    
	public diReviewKeyword[] getDi_review_keyword() {
		return di_review_keyword;
	}
	public void setDi_review_keyword(diReviewKeyword[] di_review_keyword) {
		this.di_review_keyword = di_review_keyword;
	}
	public String getShop_hours() {
		return shop_hours;
	}
	public void setShop_hours(String shop_hours) {
		this.shop_hours = shop_hours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNavi_location() {
		return navi_location;
	}
	public void setNavi_location(String navi_location) {
		this.navi_location = navi_location;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getService_rating() {
		return service_rating;
	}
	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}
	public String getTechnology_rating() {
		return technology_rating;
	}
	public void setTechnology_rating(String technology_rating) {
		this.technology_rating = technology_rating;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	@Override
	public String toString() {
		return "DetailInfo [tag=" + tag + ", detail_url=" + detail_url + ", type=" + type + ", price=" + price
				+ ", service_rating=" + service_rating + ", technology_rating=" + technology_rating + ", image_num="
				+ image_num + ", comment_num=" + comment_num + ", navi_location=" + navi_location + ", shop_hours="
				+ shop_hours + ", description=" + description + ", di_review_keyword="
				+ Arrays.toString(di_review_keyword) + "]";
	}
	
}
