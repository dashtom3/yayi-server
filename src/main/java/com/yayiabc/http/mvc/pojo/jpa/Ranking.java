package com.yayiabc.http.mvc.pojo.jpa;
//排名表
public class Ranking extends BasePojo {
	private String rankingId;
	private String phone;	//销售员手机号
	private Integer bindUserNum;	//客户数
	private Integer orderCount;		//订单总数
	private double saleMoney;		//销售额
	private Integer rowNum;			//排名

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

	public Ranking(String rankingId, String phone, Integer bindUserNum,
			Integer orderCount, double saleMoney, Integer rowNum) {
		super();
		this.rankingId = rankingId;
		this.phone = phone;
		this.bindUserNum = bindUserNum;
		this.orderCount = orderCount;
		this.saleMoney = saleMoney;
		this.rowNum = rowNum;
	}

	@Override
	public String toString() {
		return "Ranking [rankingId=" + rankingId + ", phone=" + phone
				+ ", bindUserNum=" + bindUserNum + ", orderCount=" + orderCount
				+ ", saleMoney=" + saleMoney + ", rowNum=" + rowNum + "]";
	}

}
