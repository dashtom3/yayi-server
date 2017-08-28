package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.model.Search;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;






@Repository
public interface ItemManageDao {
	
	List<ItemProperty> queryProperty(Search search);

	void deleteProperty(Integer itemPropertyId);

	void deletePropertyd(Integer itemPropertyId);

	void deletePropertydBySKU(String itemSKU);

	void updateProperty(ItemProperty itemProperty);

	List<ItemClassify> showItemClassify(Search search);

	void addProperty(String itemPropertyName);

	void addItemClassify(ItemClassify itemClassify);

	void addPropertyd(Map<String,Object> map);

	Integer queryItemPropertyIdByName(String itemPropertyName);

	void updateItemClassify(ItemClassify itemClassify);

	void addToPropertyd(@Param("itemPid")Integer itemPid,@Param("itemPparam") String itemPparam);

	int getCountByItemPropertyName(String itemPropertyName);

	Integer getCount(ItemClassify itemClassify);

	Integer getCountProperty(@Param("itemPropertyName")String itemPropertyName);

	void deletePro(Integer itemPropertyId);

	Integer getCountItemClassify(@Param("itemClassifyName")String itemClassifyName);


    void deleteItemClassifyById(@Param("itemClassifyId")Integer itemClassifyId);
}
