package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 退货信息表
 */
public class Refund extends BasePojo {
	private String refundId;

	private String userId;

	private String itemId;

	private Integer refundNum;

	private String refundReason;

	private Integer refundMoney;

	private String refundPic;

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

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
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

	public String getRefundPic() {
		return refundPic;
	}

	public void setRefundPic(String refundPic) {
		this.refundPic = refundPic == null ? null : refundPic.trim();
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

	public Refund() {
		super();
	}

	public Refund(String refundId, String userId, String itemId,
			Integer refundNum, String refundReason, Integer refundMoney,
			String refundPic, Integer state, String failReason) {
		super();
		this.refundId = refundId;
		this.userId = userId;
		this.itemId = itemId;
		this.refundNum = refundNum;
		this.refundReason = refundReason;
		this.refundMoney = refundMoney;
		this.refundPic = refundPic;
		this.state = state;
		this.failReason = failReason;
	}

	@Override
	public String toString() {
		return "Refund [refundId=" + refundId + ", userId=" + userId
				+ ", itemId=" + itemId + ", refundNum=" + refundNum
				+ ", refundReason=" + refundReason + ", refundMoney="
				+ refundMoney + ", refundPic=" + refundPic + ", state=" + state
				+ ", failReason=" + failReason + "]";
	}

}