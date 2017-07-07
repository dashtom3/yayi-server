package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 
 * @author xiaojiang 商品详情表
 */
public class ItemDetail extends BasePojo {
	private String itemId;

	private String video;

	private String itemPica;

	private String itemPicb;

	private String itemPicc;

	private String itemPicd;

	private String itemPice;

	private Integer commission;

	private Integer isQbBuy;

	private Integer qbNum;

	private String storeItemId;

	private String apparatusType;

	private String unit;

	private String producePompany;

	private String registerId;

	private Date registerDate;

	private String itemPacking;

	private String itemLevels;

	private String itemRange;

	private String remark;

	private String itemDesc;

	private String itemUse;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemUse() {
		return itemUse;
	}

	public void setItemUse(String itemUse) {
		this.itemUse = itemUse;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video == null ? null : video.trim();
	}

	public String getItemPica() {
		return itemPica;
	}

	public void setItemPica(String itemPica) {
		this.itemPica = itemPica == null ? null : itemPica.trim();
	}

	public String getItemPicb() {
		return itemPicb;
	}

	public void setItemPicb(String itemPicb) {
		this.itemPicb = itemPicb == null ? null : itemPicb.trim();
	}

	public String getItemPicc() {
		return itemPicc;
	}

	public void setItemPicc(String itemPicc) {
		this.itemPicc = itemPicc == null ? null : itemPicc.trim();
	}

	public String getItemPicd() {
		return itemPicd;
	}

	public void setItemPicd(String itemPicd) {
		this.itemPicd = itemPicd == null ? null : itemPicd.trim();
	}

	public String getItemPice() {
		return itemPice;
	}

	public void setItemPice(String itemPice) {
		this.itemPice = itemPice == null ? null : itemPice.trim();
	}

	public Integer getCommission() {
		return commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public Integer getIsQbBuy() {
		return isQbBuy;
	}

	public void setIsQbBuy(Integer isQbBuy) {
		this.isQbBuy = isQbBuy;
	}

	public Integer getQbNum() {
		return qbNum;
	}

	public void setQbNum(Integer qbNum) {
		this.qbNum = qbNum;
	}

	public String getStoreItemId() {
		return storeItemId;
	}

	public void setStoreItemId(String storeItemId) {
		this.storeItemId = storeItemId == null ? null : storeItemId.trim();
	}

	public String getApparatusType() {
		return apparatusType;
	}

	public void setApparatusType(String apparatusType) {
		this.apparatusType = apparatusType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getProducePompany() {
		return producePompany;
	}

	public void setProducePompany(String producePompany) {
		this.producePompany = producePompany == null ? null : producePompany
				.trim();
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId == null ? null : registerId.trim();
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getItemPacking() {
		return itemPacking;
	}

	public void setItemPacking(String itemPacking) {
		this.itemPacking = itemPacking == null ? null : itemPacking.trim();
	}

	public String getItemLevels() {
		return itemLevels;
	}

	public void setItemLevels(String itemLevels) {
		this.itemLevels = itemLevels == null ? null : itemLevels.trim();
	}

	public String getItemRange() {
		return itemRange;
	}

	public void setItemRange(String ietmRange) {
		this.itemRange = ietmRange == null ? null : ietmRange.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public ItemDetail() {
		super();
	}

	public ItemDetail(String itemId, String video, String itemPica,
			String itemPicb, String itemPicc, String itemPicd, String itemPice,
			Integer commission, Integer isQbBuy, Integer qbNum,
			String storeItemId, String apparatusType, String unit,
			String producePompany, String registerId, Date registerDate,
			String itemPacking, String itemLevels, String itemRange,
			String remark, String itemDesc, String itemUse) {
		super();
		this.itemId = itemId;
		this.video = video;
		this.itemPica = itemPica;
		this.itemPicb = itemPicb;
		this.itemPicc = itemPicc;
		this.itemPicd = itemPicd;
		this.itemPice = itemPice;
		this.commission = commission;
		this.isQbBuy = isQbBuy;
		this.qbNum = qbNum;
		this.storeItemId = storeItemId;
		this.apparatusType = apparatusType;
		this.unit = unit;
		this.producePompany = producePompany;
		this.registerId = registerId;
		this.registerDate = registerDate;
		this.itemPacking = itemPacking;
		this.itemLevels = itemLevels;
		this.itemRange = itemRange;
		this.remark = remark;
		this.itemDesc = itemDesc;
		this.itemUse = itemUse;
	}

	@Override
	public String toString() {
		return "ItemDetail [itemId=" + itemId + ", video=" + video
				+ ", itemPica=" + itemPica + ", itemPicb=" + itemPicb
				+ ", itemPicc=" + itemPicc + ", itemPicd=" + itemPicd
				+ ", itemPice=" + itemPice + ", commission=" + commission
				+ ", isQbBuy=" + isQbBuy + ", qbNum=" + qbNum
				+ ", storeItemId=" + storeItemId + ", apparatusType="
				+ apparatusType + ", unit=" + unit + ", producePompany="
				+ producePompany + ", registerId=" + registerId
				+ ", registerDate=" + registerDate + ", itemPacking="
				+ itemPacking + ", itemLevels=" + itemLevels + ", itemRange="
				+ itemRange + ", remark=" + remark + ", itemDesc=" + itemDesc
				+ ", itemUse=" + itemUse + "]";
	}

}