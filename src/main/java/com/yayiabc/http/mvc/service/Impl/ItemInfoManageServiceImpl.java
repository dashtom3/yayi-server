package com.yayiabc.http.mvc.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.ItemInfoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemDetail;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.service.ItemInfoManageService;


@Service
public class ItemInfoManageServiceImpl implements ItemInfoManageService{
	
	@Autowired
	private ItemInfoManageDao itemInfoManageDao;

	@Override
	public DataWrapper<List<ItemInfo>> itemInfoList(String itemId,
			String itemName, String itemClassify, String itemBrandName,
			Integer state) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		ItemInfo itemInfo =new ItemInfo();
		itemInfo.setItemId(itemId);
		itemInfo.setItemName(itemName);
		itemInfo.setOneClassify(itemClassify);
		/*ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandName(itemBrandName);
		itemInfo.setItemBrand(itemBrand);*/
		itemInfo.setTwoClassify(itemBrandName);
		itemInfo.setState(state);
		List<ItemInfo> itemInfoList=itemInfoManageDao.itemInfoList(itemInfo);
		dataWrapper.setData(itemInfoList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> up(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.up(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> down(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.down(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.deleteItemValue(itemId);
		itemInfoManageDao.deleteItemStock(itemId);
		itemInfoManageDao.deleteItemDetail(itemId);
		itemInfoManageDao.deleteItemInfo(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	

	



	@Override
	public DataWrapper<Void> insert(String itemId,String itemName,Integer itemBrandId,
			String oneClassify, String itemLevels, String twoClassify,
			String threeClassify, String itemPica, String itemPicb,
			String itemPicc, String itemPicd, String itemPice,Integer isThrow, String video,
			String itemDesc, String itemUse, String itemRange,String remark,
			String registerId, String storeItemId, String apparatusType,
			String unit, String producePompany, Date registerDate,
			String itemPacking, String itemBrandName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
			ItemInfo itemInfo=new ItemInfo();
			itemInfo.setItemId(itemId);
			itemInfo.setItemName(itemName);
			itemInfo.setOneClassify(oneClassify);
			itemInfo.setTwoClassify(twoClassify);
			itemInfo.setThreeClassify(threeClassify);
			itemInfo.setIsThrow(isThrow);
			ItemBrand itemBrand =new ItemBrand();
			itemBrand.setItemBrandName(itemBrandName);
			itemBrand.setItemBrandId(itemBrandId);
			ItemDetail itemDetail =new ItemDetail();
			itemDetail.setItemId(itemId);
			itemDetail.setItemDesc(itemDesc);
			itemDetail.setItemUse(itemUse);
			itemDetail.setVideo(video);
			itemDetail.setItemPica(itemPica);
			itemDetail.setItemPicb(itemPicb);
			itemDetail.setItemPicc(itemPicc);
			itemDetail.setItemPicd(itemPicd);
			itemDetail.setItemPice(itemPice);
			itemDetail.setStoreItemId(storeItemId);
			itemDetail.setApparatusType(apparatusType);
			itemDetail.setUnit(unit);
			itemDetail.setProducePompany(producePompany);
			itemDetail.setRegisterId(registerId);
			itemDetail.setRegisterDate(registerDate);
			itemDetail.setItemPacking(itemPacking);
			itemDetail.setItemLevels(itemLevels);
			itemDetail.setItemRange(itemRange);
			itemDetail.setRemark(remark);
			itemDetail.setCreated(new Date());
			itemDetail.setUpdated(new Date());
			itemInfo.setItemDetail(itemDetail);
			itemInfo.setItemBrand(itemBrand);
			Integer itemPrice =itemInfoManageDao.getMinPriceFromItemValue(itemId);
			itemInfo.setItemPrice(itemPrice);
			itemInfoManageDao.insertItemInfo(itemInfo);
			itemInfoManageDao.insertItemDetail(itemDetail);
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			return dataWrapper;
	}
	@Override
	public DataWrapper<Void> insertItemValue(List<ItemValue> itemValueList) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		for (ItemValue itemValue : itemValueList) {
			itemInfoManageDao.insertItemValue(itemValue);
		}
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update(String itemId, String itemName,
			Integer itemBrandId, String oneClassify, String itemLevels,
			String twoClassify, String threeClassify, String itemPica,
			String itemPicb, String itemPicc, String itemPicd, String itemPice,Integer isThrow,
			String video, String itemDesc, String itemUse, String itemRange,
			String registerId, String storeItemId, String apparatusType,
			String unit,String producePompany, Date registerDate,
			String itemPacking,String itemBrandName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemInfo itemInfo=new ItemInfo();
		itemInfo.setItemId(itemId);
		itemInfo.setItemName(itemName);
		itemInfo.setOneClassify(oneClassify);
		itemInfo.setTwoClassify(twoClassify);
		itemInfo.setThreeClassify(threeClassify);
		itemInfo.setIsThrow(isThrow);
		ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandName(itemBrandName);
		itemBrand.setItemBrandId(itemBrandId);
		ItemDetail itemDetail =new ItemDetail();
		itemDetail.setItemId(itemId);
		itemDetail.setItemDesc(itemDesc);
		itemDetail.setItemUse(itemUse);
		itemDetail.setVideo(video);
		itemDetail.setItemPica(itemPica);
		itemDetail.setItemPicb(itemPicb);
		itemDetail.setItemPicc(itemPicc);
		itemDetail.setItemPicd(itemPicd);
		itemDetail.setItemPice(itemPice);
		itemDetail.setStoreItemId(storeItemId);
		itemDetail.setApparatusType(apparatusType);
		itemDetail.setUnit(unit);
		itemDetail.setProducePompany(producePompany);
		itemDetail.setRegisterId(registerId);
		itemDetail.setRegisterDate(registerDate);
		itemDetail.setItemPacking(itemPacking);
		itemDetail.setItemLevels(itemLevels);
		itemDetail.setItemRange(itemRange);
		itemDetail.setCreated(new Date());
		itemDetail.setUpdated(new Date());
		itemInfo.setItemDetail(itemDetail);
		itemInfo.setItemBrand(itemBrand);
		Integer itemPrice =itemInfoManageDao.getMinPriceFromItemValue(itemId);
		itemInfo.setItemPrice(itemPrice);
		itemInfoManageDao.updateItemInfo(itemInfo);
		itemInfoManageDao.updateItemDetail(itemDetail);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}


	

	@Override
	public DataWrapper<Void> updateItemValue(List<ItemValue> itemValueList) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		for (ItemValue itemValue : itemValueList) {
			itemInfoManageDao.updateItemValue(itemValue);
		}
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	

	
	
	

	

}
