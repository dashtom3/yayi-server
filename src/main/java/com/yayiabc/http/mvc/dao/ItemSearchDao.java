package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Search;
@Repository
public interface ItemSearchDao {

	List<ItemInfo> itemSearch(Search search);

	int getCount(Search search);

}
