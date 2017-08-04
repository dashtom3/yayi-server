package com.yayiabc.http.mvc.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.ItemShow;
import com.yayiabc.http.mvc.pojo.model.Search;
@Repository
public interface ItemBrandDao{

    public List<ItemBrand> brandList();

    public List<ItemInfo> brandItemList(Search search);

    public ItemInfo itemDetailDes(String itemId);

	public int getCount(Search search);

	public List<ItemBrand> queryItemBrand(Search search);

	public int deleteItemBrand(Integer itemBrandId);

	public String queryItemIdByItemBrandId(Integer itemBrandId);

	public void deleteItemDetailByItemId(String itemId);

	public void deleteItemInfoByItemId(String itemId);

	public List<ItemShow> itemShow();

	public void updateItemBrand(ItemBrand itemBrand);

	public List<String> queryItemIdByUserId(String userId);

	public String getUserIdByToken(String token);

	public List<String> getItemIdByUserId(String userId);

	public void addItemBrand(ItemBrand itemBrand);

	public String getItemPropertyNameA(String itemId);

	public List<String> getItemPropertyInfoA(String itemId);

	public String getItemPropertyNameB(String itemId);

	public List<String> getItemPropertyInfoB(String itemId);

	public String getItemPropertyNameC(String itemId);

	public List<String> getItemPropertyInfoC(String itemId);

	public String getItemPropertyNameD(String itemId);

	public List<String> getItemPropertyInfoD(String itemId);

	public String getItemPropertyNameE(String itemId);

	public List<String> getItemPropertyInfoE(String itemId);

	public String getItemPropertyNameF(String itemId);

	public List<String> getItemPropertyInfoF(String itemId);

	public String getItemSKUByPrice(@Param("itemPrice") Double itemPrice,@Param("itemId") String itemId);

	public String getVideoNameByVideoRoute(String video);

	public Integer getCountOne(ItemBrand itemBrand);

	public Integer getCommentNumber(@Param("itemId")String itemId);

}

