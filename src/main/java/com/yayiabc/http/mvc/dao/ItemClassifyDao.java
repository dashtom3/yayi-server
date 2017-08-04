package com.yayiabc.http.mvc.dao;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.pojo.model.Search;

public interface ItemClassifyDao {
 List<Classify> showsTreeClassify();

 List<ItemInfo> queryItemSearch(Search search);

int getCount(Search search);

int getCountGet(Search search);

List<ItemInfo> queryItemSearchGet(Search search);

List<ItemInfo> getAllRecommendItemList();
}
