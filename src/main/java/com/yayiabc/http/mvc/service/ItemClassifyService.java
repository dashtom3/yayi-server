package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.pojo.model.SysResult;

import java.util.List;

public interface ItemClassifyService {
	DataWrapper<List<Classify>> showsTreeClassify();

//	DataWrapper<SysResult> getAllClassifyAndBrand();

	DataWrapper<List<ItemInfo>> queryItemSearch(String oneClassify,
			String twoClassify, String threeClassify, String itemBrandName,
			Integer rule,Integer currentPage,Integer numberPerPage);

	DataWrapper<List<ItemInfo>> queryItemSearchGet(String oneClassify,
			String twoClassify, String threeClassify, String itemBrandName,
			Integer rule, Integer currentPage, Integer numberPerPage);

	DataWrapper<List<ItemInfo>> getAllRecommendItemList();

    DataWrapper<SysResult> getAllClassifyAndBrand();
}
