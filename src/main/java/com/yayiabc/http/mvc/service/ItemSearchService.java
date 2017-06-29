package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;

public interface ItemSearchService {

	DataWrapper<List<ItemInfo>> itemSearch(String keyWord,Integer currentPage,Integer numberPerPage,Integer rule);

}
