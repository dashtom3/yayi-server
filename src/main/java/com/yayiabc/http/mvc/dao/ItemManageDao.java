package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemPropertyd;
@Repository
public interface ItemManageDao {
	
	List<ItemProperty> queryProperty(String itemPropertyName);

	void deleteProperty(Integer itemPropertyId);

	void deletePropertyd(Integer itemPropertyId);

	void deletePropertydBySKU(String itemSKU);

	void updatePropertyd(ItemPropertyd itemPropertyd);

	void updateProperty(ItemProperty itemProperty);

	List<ItemClassify> showItemClassify(ItemClassify itemClassify);

	void deleteItemClassify(String itemClassifyName);
	//查询该商品分类是否为父类
	int queryItemClassify(String itemClassifyName);

	String queryItemClassifyName(Integer itemClassifyId);

	void updateItemClassify(ItemClassify itemClassify);

	void addProperty(String itemPropertyName);

	void addPropertyd(ItemProperty itemProperty);

	void addItemClassify(ItemClassify itemClassify);

	



	

	

}
