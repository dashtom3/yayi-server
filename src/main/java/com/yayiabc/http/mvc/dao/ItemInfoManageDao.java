package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.ItemDetail;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
@Repository
public interface ItemInfoManageDao {

	List<ItemInfo> itemInfoList(ItemInfo itemInfo);

	void up(String itemId);

	void down(String itemId);

	void deleteItemInfo(String itemId);

	void deleteItemDetail(String itemId);

	void deleteItemValue(String itemId);

	void deleteItemStock(String itemId);


	void insertItemInfo(ItemInfo itemInfo);

	void insertItemDetail(ItemDetail itemDetail);

	void updateItemInfo(ItemInfo itemInfo);

	void updateItemDetail(ItemDetail itemDetail);

	void updateItemValue(ItemValue itemValue);

	Double getMinPriceFromItemValue(String itemId);


	void deleteComments(String itemId);

	Integer queryItemNum(String itemSKU);

	void insertItemValue(ItemValue itemValue);

	String getItemBrandNameByItemId(Integer itemBrandId);

	int getItemClassifyGradeByName(String itemClassify);

	int getItemClassifyGrade(String itemClassify);

	List<ItemInfo> itemInfoListOne(ItemInfo itemInfo);

	List<ItemInfo> itemInfoListTwo(ItemInfo itemInfo);

	List<ItemInfo> itemInfoListThree(ItemInfo itemInfo);

	Integer getCountOne(ItemInfo itemInfo);

	Integer getCountTwo(ItemInfo itemInfo);

	Integer getCountThree(ItemInfo itemInfo);

	Integer getCountFour(ItemInfo itemInfo);

	List<ItemInfo> itemInfoListFour(ItemInfo itemInfo);

	void deleteCart(String itemId);










}
