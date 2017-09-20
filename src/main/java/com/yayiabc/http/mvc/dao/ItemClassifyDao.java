package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.pojo.model.Search;

import java.util.List;

public interface ItemClassifyDao {
 List<Classify> showsTreeClassify();

 List<ItemInfo> queryItemSearch(Search search);

int getCount(Search search);


List<ItemInfo> getAllRecommendItemList();
}
