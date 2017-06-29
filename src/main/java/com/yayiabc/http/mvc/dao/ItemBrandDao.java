package com.yayiabc.http.mvc.dao;



import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.ItemShow;
import com.yayiabc.http.mvc.pojo.model.Search;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ItemBrandDao{

    public List<ItemBrand> brandList();

    public List<ItemInfo> brandItemList(Search search);

    public ItemInfo itemDetailDes(String itemId);

	public int getCount(Search search);

	public List<ItemBrand> queryItemBrand(ItemBrand itemBrand);

	public int deleteItemBrand(Integer itemBrandId);

	public String queryItemIdByItemBrandId(Integer itemBrandId);

	public void deleteItemDetailByItemId(String itemId);

	public void deleteItemInfoByItemId(String itemId);

	public List<ItemShow> itemShow();

	public void updateItemBrand(ItemBrand itemBrand);

	public List<String> queryItemIdByUserId(String userId);

	public String getUserIdByToken(String token);

	public String getItemIdByUserId(String userId);

	public void addItemBrand(ItemBrand itemBrand);
}

