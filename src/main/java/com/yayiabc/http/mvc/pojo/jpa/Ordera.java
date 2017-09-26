package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author xiaojiang 订单表
 */
public class Ordera extends BasePojo {
	private String orderId;
	private String userId;
	private Double actualPay;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	private Integer payType;

	private String postFee;

	private Date paymentTime;

	private Date consignTime;

	private Date endTime;

	private Date closeTime;

	private String buyerMessage;

	private String buyerNick;

	private Integer buyerRate;

	private Integer state;

	private String shippingName;

	private String shippingCode;

	private String invoiceHand;

	private Integer isRegister;

	private Integer giveQb;

	private String refundInfo;

	private Double totalFee;
	private  Integer qbDed;
	private  Refund   refund;
	
	private  Receiver receiver;
	private  Integer receiverId;

	private User user;
    private double supplies_sumprice;
	private double tooldevices_sumprice;

	private List<OrderItem> orderitemList;

	private List<Comments> commentList;
    private String orderNums;
    
    private String qbDes;
    
    private int createState;  //0  购物车       1 立即购买


	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public int getCreateState() {
		return createState;
	}

	public void setCreateState(int createState) {
		this.createState = createState;
	}

	public String getQbDes() {
		return qbDes;
	}

	public void setQbDes(String qbDes) {
		this.qbDes = qbDes;
	}

	public String getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(String orderNums) {
		this.orderNums = orderNums;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreated() {
		return created;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getQbDed() {
		return qbDed;
	}

	public void setQbDed(Integer qbDed) {
		this.qbDed = qbDed;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public List<OrderItem> getOrderitemList() {
		return orderitemList;
	}

	public String getRefundInfo() {
		return refundInfo;
	}

	public void setRefundInfo(String refundInfo) {
		this.refundInfo = refundInfo;
	}

	public void setOrderitemList(List<OrderItem> orderitemList) {
		this.orderitemList = orderitemList;
	}

	public List<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getActualPay() {
		return actualPay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setActualPay(Double actualPay) {
		this.actualPay = actualPay;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPostFee() {
		return postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee == null ? null : postFee.trim();
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public Date getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick == null ? null : buyerNick.trim();
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName == null ? null : shippingName.trim();
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode == null ? null : shippingCode.trim();
	}

	public String getInvoiceHand() {
		return invoiceHand;
	}

	public void setInvoiceHand(String invoiceHand) {
		this.invoiceHand = invoiceHand == null ? null : invoiceHand.trim();
	}

	public Integer getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}

	public Integer getGiveQb() {
		return giveQb;
	}

	public void setGiveQb(Integer giveQb) {
		this.giveQb = giveQb;
	}

	public double getSupplies_sumprice() {
		return supplies_sumprice;
	}

	public void setSupplies_sumprice(double supplies_sumprice) {
		this.supplies_sumprice = supplies_sumprice;
	}

	public double getTooldevices_sumprice() {
		return tooldevices_sumprice;
	}

	public void setTooldevices_sumprice(double tooldevices_sumprice) {
		this.tooldevices_sumprice = tooldevices_sumprice;
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	public Ordera() {
		this.buyerMessage = "该用户未留言";
		this.state = 1;
	}

	@Override
	public String toString() {
		return "Ordera [orderId=" + orderId + ", userId=" + userId + ", actualPay=" + actualPay + ", created=" + created
				+ ", payType=" + payType + ", postFee=" + postFee + ", paymentTime=" + paymentTime + ", consignTime="
				+ consignTime + ", endTime=" + endTime + ", closeTime=" + closeTime + ", buyerMessage=" + buyerMessage
				+ ", buyerNick=" + buyerNick + ", buyerRate=" + buyerRate + ", state=" + state + ", shippingName="
				+ shippingName + ", shippingCode=" + shippingCode + ", invoiceHand=" + invoiceHand + ", isRegister="
				+ isRegister + ", giveQb=" + giveQb + ", refundInfo=" + refundInfo + ", totalFee=" + totalFee
				+ ", qbDed=" + qbDed + ", refund=" + refund + ", receiver=" + receiver + ", receiverId=" + receiverId
				+ ", user=" + user + ", supplies_sumprice=" + supplies_sumprice + ", tooldevices_sumprice="
				+ tooldevices_sumprice + ", orderitemList=" + orderitemList + ", commentList=" + commentList
				+ ", orderNums=" + orderNums + "]";
	}
	
}