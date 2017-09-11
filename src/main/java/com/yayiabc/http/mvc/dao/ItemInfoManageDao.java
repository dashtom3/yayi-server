package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemInfoManageDao {

	List<ItemInfo> itemInfoList(ItemInfo itemInfo);

	void up(String itemId);

	void down(@Param("itemId")String itemId);

	void deleteItemInfo(String itemId);

	void deleteItemDetail(String itemId);

	void deleteItemValue(String itemId);

	Double getMinPriceFromItemValue(String itemId);

	List<ItemInfo> itemInfoListOne(ItemInfo itemInfo);


	Integer getCountOne(ItemInfo itemInfo);

	void addItemInfo(ItemInfo itemInfo);

	void addItemDetail(ItemInfo itemInfo);

	void addItemValue(List<ItemValue> itemValueList);

	Integer getCountOneClassify(String itemClassify);
}
