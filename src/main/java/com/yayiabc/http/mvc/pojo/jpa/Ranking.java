package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;

/**
 * 月排名表
 * 
 * @author me
 * 
 */
public class Ranking extends BasePojo implements Serializable {
	private String rankingId;
	private String saleId;		//销售员ID
	private String phone; // 销售员手机号
	private Integer bindUserNum; // 客户数
	private Integer orderCount; // 订单总数
	private double saleMoney; // 销售额
	private Integer rowNum; // 排名

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getRankingId() {
		return rankingId;
	}

	public void setRankingId(String rankingId) {
		this.rankingId = rankingId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBindUserNum() {
		return bindUserNum;
	}

	public void setBindUserNum(Integer bindUserNum) {
		this.bindUserNum = bindUserNum;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public double getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(double saleMoney) {
		this.saleMoney = saleMoney;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Ranking() {
		super();
	}

	public Ranking(String rankingId,String saleId, String phone, Integer bindUserNum,
			Integer orderCount, double saleMoney, Integer rowNum) {
		super();
		this.rankingId = rankingId;
		this.saleId=saleId;
		this.phone = phone;
		this.bindUserNum = bindUserNum;
		this.orderCount = orderCount;
		this.saleMoney = saleMoney;
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "Ranking{" +
				"rankingId='" + rankingId + '\'' +
				", saleId='" + saleId + '\'' +
				", phone='" + phone + '\'' +
				", bindUserNum=" + bindUserNum +
				", orderCount=" + orderCount +
				", saleMoney=" + saleMoney +
				", rowNum=" + rowNum +
				'}';
	}
}
