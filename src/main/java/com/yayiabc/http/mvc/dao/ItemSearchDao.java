package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Search;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemSearchDao {

	List<ItemInfo> itemSearch(Search search);

	int getCount(Search search);

    int getSearchCount(Search search);

	List<ItemInfo> search(Search search);
}
