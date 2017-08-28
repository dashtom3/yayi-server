package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;

import java.util.List;

public interface ItemInfoManageService {

	DataWrapper<List<ItemInfo>> itemInfoList(String itemId, String itemName,
			String itemClassify, String itemBrandName, Integer state,Integer currentPage,Integer numberPerPage,Integer itemClassifyGrade);

	DataWrapper<Void> up(String itemId);

	DataWrapper<Void> down(String itemId);

	DataWrapper<Void> delete(String itemId);


    DataWrapper<Void> addItem(ItemInfo itemInfo);

    DataWrapper<Void> updateItem(ItemInfo itemInfo);
}
