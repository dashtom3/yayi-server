package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 退货信息表
 */
public class Refund extends BasePojo {
	private String refundId;

	private String userId;

	private String itemId;


	private String refundReason;

	private Integer refundMoney;

   private Double refund_money_haocai;
   private Double refund_money_gongju;
	private Integer state;

	private String failReason;
    private Integer dedQb;
    private Integer returnQb;
    private Integer returnMoney;
    
	public Integer getDedQb() {
		return dedQb;
	}

	public void setDedQb(Integer dedQb) {
		this.dedQb = dedQb;
	}

	public Integer getReturnQb() {
		return returnQb;
	}

	public void setReturnQb(Integer returnQb) {
		this.returnQb = returnQb;
	}

	public Integer getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Integer returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId == null ? null : refundId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason == null ? null : refundReason.trim();
	}

	public Integer getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Integer refundMoney) {
		this.refundMoney = refundMoney;
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason == null ? null : failReason.trim();
	}

	public Double getRefund_money_haocai() {
		return refund_money_haocai;
	}

	public void setRefund_money_haocai(Double refund_money_haocai) {
		this.refund_money_haocai = refund_money_haocai;
	}

	public Double getRefund_money_gongju() {
		return refund_money_gongju;
	}

	public void setRefund_money_gongju(Double refund_money_gongju) {
		this.refund_money_gongju = refund_money_gongju;
	}


}