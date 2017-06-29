package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
@Repository
public interface ItemInfoManageDao {

	List<ItemInfo> itemInfoList(ItemInfo itemInfo);

	void up(String itemId);

	void down(String itemId);

	void deleteItemInfo(String itemId);

	void deleteItemDetail(String itemId);

	void deleteItemValue(String itemId);

	void insertItemValue(ItemInfo itemInfo);

	void insertItemInfo(ItemInfo itemInfo);

	void insertItemDetail(ItemInfo itemInfo);

	void insertItemPropertyd(HashMap<String, Object> map);




}
