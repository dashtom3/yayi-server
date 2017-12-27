package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;

/**
 * 
 * @author xiaojiang 商品详情表
 */
public class ItemDetail extends BasePojo implements Serializable{
	private String itemId;

	private VidManage vidManage;

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

	private String registerDate;

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
		if(storeItemId==null||"".equals(storeItemId)){
			storeItemId="暂无";
		}
		return storeItemId;
	}

	public void setStoreItemId(String storeItemId) {
		this.storeItemId = storeItemId == null ? null : storeItemId.trim();
	}

	public String getApparatusType() {
		if(apparatusType==null||"".equals(apparatusType)){
			apparatusType="暂无";
		}
		return apparatusType;
	}

	public void setApparatusType(String apparatusType) {
		this.apparatusType = apparatusType;
	}

	public String getUnit() {
		if(unit==null||"".equals(unit)){
			unit="暂无";
		}
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getProducePompany() {
		if(producePompany==null||"".equals(producePompany)){
			producePompany="暂无";
		}
		return producePompany;
	}

	public void setProducePompany(String producePompany) {
		this.producePompany = producePompany == null ? null : producePompany
				.trim();
	}

	public String getRegisterId() {
		if(registerId==null||"".equals(registerId)){
			registerId="暂无";
		}
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId == null ? null : registerId.trim();
	}

	public String getRegisterDate() {
		if(registerDate==null||"".equals(registerDate)){
			registerDate="暂无";
		}
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getItemPacking() {
		if(itemPacking==null||"".equals(itemPacking)){
			itemPacking="暂无";
		}
		return itemPacking;
	}

	public void setItemPacking(String itemPacking) {
		this.itemPacking = itemPacking == null ? null : itemPacking.trim();
	}

	public String getItemLevels() {
		if(itemLevels==null||"".equals(itemLevels)){
			itemLevels="暂无";
		}
		return itemLevels;
	}

	public void setItemLevels(String itemLevels) {
		this.itemLevels = itemLevels == null ? null : itemLevels.trim();
	}

	public String getItemRange() {
		if(itemRange==null||"".equals(itemRange)){
			itemRange="暂无";
		}
		return itemRange;
	}

	public void setItemRange(String ietmRange) {
		this.itemRange = ietmRange == null ? null : ietmRange.trim();
	}

	public String getRemark() {
		if(remark==null||"".equals(remark)){
			remark="暂无";
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public VidManage getVidManage() {
		return vidManage;
	}

	public void setVidManage(VidManage vidManage) {
		this.vidManage = vidManage;
	}

	@Override
	public String toString() {
		return "ItemDetail{" +
				"itemId='" + itemId + '\'' +
				", vidManage=" + vidManage +
				", itemPica='" + itemPica + '\'' +
				", itemPicb='" + itemPicb + '\'' +
				", itemPicc='" + itemPicc + '\'' +
				", itemPicd='" + itemPicd + '\'' +
				", itemPice='" + itemPice + '\'' +
				", commission=" + commission +
				", isQbBuy=" + isQbBuy +
				", qbNum=" + qbNum +
				", storeItemId='" + storeItemId + '\'' +
				", apparatusType='" + apparatusType + '\'' +
				", unit='" + unit + '\'' +
				", producePompany='" + producePompany + '\'' +
				", registerId='" + registerId + '\'' +
				", registerDate='" + registerDate + '\'' +
				", itemPacking='" + itemPacking + '\'' +
				", itemLevels='" + itemLevels + '\'' +
				", itemRange='" + itemRange + '\'' +
				", remark='" + remark + '\'' +
				", itemDesc='" + itemDesc + '\'' +
				", itemUse='" + itemUse + '\'' +
				'}';
	}
}