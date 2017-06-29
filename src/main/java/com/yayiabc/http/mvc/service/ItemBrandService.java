package com.yayiabc.http.mvc.service;


import java.util.List;


import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.ItemShow;

public interface ItemBrandService{

    public  DataWrapper<List<ItemBrand>> brandList();

    public  DataWrapper<List<ItemInfo>> brandItemList(Integer itemBrandId,Integer currentPage,Integer numberPerPage,Integer rule);

    public  DataWrapper<ItemInfo> itemDetailDes(String itemId,String token);

	public DataWrapper<List<ItemShow>> itemShow();
}

