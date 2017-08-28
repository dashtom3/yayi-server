package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;

import java.util.List;

public interface ItemManageService {

	DataWrapper<List<ItemBrand>> queryItemBrand(String itemBrandName,
			String itemBrandHome,Integer currentPage,Integer numberPerPage);

	DataWrapper<Void> deleteItemBrand(Integer itemBrandId);

	DataWrapper<Void> updateItemBrand(Integer itemBrandId,String itemBrandName,
			String itemBrandLogo, String itemBrandHome);

	DataWrapper<List<ItemProperty>> queryProperty(String itemPropertyName,Integer currentPage,Integer numberPerPage);

	DataWrapper<Void> deleteProperty(Integer itemPropertyId);

	DataWrapper<Void> deletePropertyd(String itemSKU);

	DataWrapper<Void> deletePropertydBySKU(String itemSKU);



	DataWrapper<Void> updateProperty(Integer itemPropertyId,String itemPropertyName,
			List<String> itemPparamList);

	DataWrapper<List<ItemClassify>> showItemClassify(String itemClassifyName,
			String itemPreviousClassify,Integer currentPage,Integer numberPerPage);

//	DataWrapper<Void> deleteItemClassify(Integer itemClassifyId,String itemClassifyName,Integer itemClassifyGrade);
	DataWrapper<Void> deleteItemClassify(ItemClassify itemClassify);

	/*DataWrapper<Void> updateItemClassify(Integer itemClassifyId,String itemClassifyName,
			String itemOldName ,Integer itemClassifyGrade);*/
	DataWrapper<Void> updateItemClassify(ItemClassify itemClassify);

	DataWrapper<Void> addItemBrand(String itemBrandName, String itemBrandHome,
			String itemBrandLogo);

	DataWrapper<Void> addProperty(String itemPropertyName);


	/*DataWrapper<Void> addItemClassify(String itemClassifyName,
			String itemPreviousClassify,Integer itemClassifyGrade);*/
	DataWrapper<Void> addItemClassify(ItemClassify itemClassify);

	DataWrapper<Void> addPropertyAndPropertyName(String itemPropertyName,
			List<String> itemPparamList);

	DataWrapper<Void> addToPropertyd(Integer itemPid, String itemPparam);

	

}
