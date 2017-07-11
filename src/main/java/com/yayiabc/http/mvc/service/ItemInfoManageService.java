package com.yayiabc.http.mvc.service;

import java.util.Date;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;

public interface ItemInfoManageService {

	DataWrapper<List<ItemInfo>> itemInfoList(String itemId, String itemName,
			String itemClassify, String itemBrandName, Integer state);

	DataWrapper<Void> up(String itemId);

	DataWrapper<Void> down(String itemId);

	DataWrapper<Void> delete(String itemId);

	DataWrapper<Void> update(String itemId, String itemName,
			Integer itemBrandId, String oneClassify, String itemLevels,
			String twoClassify, String threeClassify, String itemPica,
			String itemPicb, String itemPicc, String itemPicd, String itemPice,Integer isThrow,
			String video, String itemDesc, String itemUse, String itemRange,
			String registerId, String storeItemId, String apparatusType,
			String unit,String producePompany, Date registerDate,
			String itemPacking,String itemBrandName);

	DataWrapper<Void> insert(String itemId,String itemName,
			Integer itemBrandId, String oneClassify, String itemLevels,
			String twoClassify, String threeClassify, String itemPica,
			String itemPicb, String itemPicc, String itemPicd, String itemPice,Integer isThrow,
			String video, String itemDesc, String itemUse, String itemRange,String remark,
			String registerId, String storeItemId, String apparatusType,
			String unit, String producePompany, Date registerDate,
			String itemPacking, String itemBrandName);

	DataWrapper<Void> insertItemValue(List<ItemValue> itemValueList);

	DataWrapper<Void> updateItemValue(List<ItemValue> itemValueList);



	

}
