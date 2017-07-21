package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

public class SaleDataStatistics {
	private double haocaiMoney; // 耗材销售额

	private double haocaiRefund; // 耗材已退款金额

	private double haocaiActual; // 耗材实际销售额

	private double gongjuMoney; // 工具销售额

	private double gongjuRefund; // 工具已退款金额

	private double gongjuActual; // 工具实际销售额

	private List<MyOrderVo> myOrderVoList;

	public double getHaocaiMoney() {
		return haocaiMoney;
	}

	public void setHaocaiMoney(double haocaiMoney) {
		this.haocaiMoney = haocaiMoney;
	}

	public double getHaocaiRefund() {
		return haocaiRefund;
	}

	public void setHaocaiRefund(double haocaiRefund) {
		this.haocaiRefund = haocaiRefund;
	}

	public double getHaocaiActual() {
		return haocaiActual;
	}

	public void setHaocaiActual(double haocaiActual) {
		this.haocaiActual = haocaiActual;
	}

	public double getGongjuMoney() {
		return gongjuMoney;
	}

	public void setGongjuMoney(double gongjuMoney) {
		this.gongjuMoney = gongjuMoney;
	}

	public double getGongjuRefund() {
		return gongjuRefund;
	}

	public void setGongjuRefund(double gongjuRefund) {
		this.gongjuRefund = gongjuRefund;
	}

	public double getGongjuActual() {
		return gongjuActual;
	}

	public void setGongjuActual(double gongjuActual) {
		this.gongjuActual = gongjuActual;
	}

	public void setGongjuActual(Integer gongjuActual) {
		this.gongjuActual = gongjuActual;
	}

	public List<MyOrderVo> getMyOrderVoList() {
		return myOrderVoList;
	}

	public void setMyOrderVoList(List<MyOrderVo> myOrderVoList) {
		this.myOrderVoList = myOrderVoList;
	}

	public SaleDataStatistics() {
		super();
	}

	public SaleDataStatistics(double haocaiMoney, double haocaiRefund,
			double haocaiActual, double gongjuMoney, double gongjuRefund,
			double gongjuActual, List<MyOrderVo> myOrderVoList) {
		super();
		this.haocaiMoney = haocaiMoney;
		this.haocaiRefund = haocaiRefund;
		this.haocaiActual = haocaiActual;
		this.gongjuMoney = gongjuMoney;
		this.gongjuRefund = gongjuRefund;
		this.gongjuActual = gongjuActual;
		this.myOrderVoList = myOrderVoList;
	}

	@Override
	public String toString() {
		return "SaleDataStatistics [haocaiMoney=" + haocaiMoney
				+ ", haocaiRefund=" + haocaiRefund + ", haocaiActual="
				+ haocaiActual + ", gongjuMoney=" + gongjuMoney
				+ ", gongjuRefund=" + gongjuRefund + ", gongjuActual="
				+ gongjuActual + ", myOrderVoList=" + myOrderVoList + "]";
	}

}
