package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 运费表
 */
public class PostFee extends BasePojo {
	private String postFeeId;

	private String postCity;

	private Integer isPost;

	private Integer firstNum;

	private Integer firstMoney;

	private Integer addNum;

	private Integer addMoney;

	public String getPostFeeId() {
		return postFeeId;
	}

	public void setPostFeeId(String postFeeId) {
		this.postFeeId = postFeeId == null ? null : postFeeId.trim();
	}

	public String getPostCity() {
		return postCity;
	}

	public void setPostCity(String postCity) {
		this.postCity = postCity == null ? null : postCity.trim();
	}

	public Integer getIsPost() {
		return isPost;
	}

	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}

	public Integer getFirstMoney() {
		return firstMoney;
	}

	public void setFirstMoney(Integer firstMoney) {
		this.firstMoney = firstMoney;
	}

	public Integer getAddNum() {
		return addNum;
	}

	public void setAddNum(Integer addNum) {
		this.addNum = addNum;
	}

	public Integer getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(Integer addMoney) {
		this.addMoney = addMoney;
	}

	public PostFee() {
		super();
	}

	public PostFee(String postFeeId, String postCity, Integer isPost,
			Integer firstNum, Integer firstMoney, Integer addNum,
			Integer addMoney) {
		super();
		this.postFeeId = postFeeId;
		this.postCity = postCity;
		this.isPost = isPost;
		this.firstNum = firstNum;
		this.firstMoney = firstMoney;
		this.addNum = addNum;
		this.addMoney = addMoney;
	}

	@Override
	public String toString() {
		return "PostFee [postFeeId=" + postFeeId + ", postCity=" + postCity
				+ ", isPost=" + isPost + ", firstNum=" + firstNum
				+ ", firstMoney=" + firstMoney + ", addNum=" + addNum
				+ ", addMoney=" + addMoney + "]";
	}

}