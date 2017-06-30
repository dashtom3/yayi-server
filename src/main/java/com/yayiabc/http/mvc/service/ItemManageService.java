package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;

public interface ItemManageService {

	DataWrapper<List<ItemBrand>> queryItemBrand(String itemBrandName,
			String itemBrandHome);

	DataWrapper<Void> deleteItemBrand(Integer itemBrandId);

	DataWrapper<Void> updateItemBrand(Integer itemBrandId,String itemBrandName,
			String itemBrandLogo, String itemBrandHome);

	DataWrapper<List<ItemProperty>> queryProperty(String itemPropertyName);

	DataWrapper<Void> deleteProperty(Integer itemPropertyId);

	DataWrapper<Void> deletePropertyd(String itemSKU);

	DataWrapper<Void> deletePropertydBySKU(String itemSKU);

	DataWrapper<Void> updatePropertyd(String itemSKU, String itemPparam);

	DataWrapper<Void> updateProperty(Integer itemPropertyId,
			String itemPropertyName);

	DataWrapper<List<ItemClassify>> showItemClassify(String itemClassifyName,
			String itemPreviousClassify);

	DataWrapper<Void> deleteItemClassify(Integer itemClassifyId);

	DataWrapper<Void> updateItemClassify(Integer itemClassifyId,
			String itemClassifyName, String itemPreviousClassify);

	DataWrapper<Void> addItemBrand(String itemBrandName, String itemBrandHome,
			String itemBrandLogo);

	DataWrapper<Void> addProperty(String itemPropertyName);

	DataWrapper<Void> addPropertyd(Integer itemPropertyId, String itemPparam);

	DataWrapper<Void> addItemClassify(String itemClassifyName,
			String itemPreviousClassify,Integer itemClassifyGrade);

	DataWrapper<Void> addPropertyAndPropertyName(String itemPropertyName,
			List<String> itemPparamList);

	

}
