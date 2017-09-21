package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Search;

import java.util.List;

public interface ItemSearchService {

	DataWrapper<List<ItemInfo>> itemSearch(String keyWord,Integer currentPage,Integer numberPerPage,Integer rule);

    DataWrapper<List<ItemInfo>> search(Search search);

//    DataWrapper<List<ItemInfo>> search(String oneClassify, String twoClassify, String itemBrandName, String keyWord, Integer rule, Integer currentPage, Integer numberPerPage);
    
}
