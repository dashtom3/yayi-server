package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.ItemInfoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemDetail;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.service.ItemInfoManageService;
@Service
public class ItemInfoManageServiceImpl implements ItemInfoManageService{
	
	@Autowired
	private ItemInfoManageDao itemInfoManageDao;

	@Override
	public DataWrapper<List<ItemInfo>> itemInfoList(String itemId,
			String itemName, String itemClassify, String itemBrandName,
			Integer state) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		ItemInfo itemInfo =new ItemInfo();
		itemInfo.setItemId(itemId);
		itemInfo.setItemName(itemName);
		itemInfo.setOneClassify(itemClassify);
		/*ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandName(itemBrandName);
		itemInfo.setItemBrand(itemBrand);*/
		itemInfo.setTwoClassify(itemBrandName);
		itemInfo.setState(state);
		List<ItemInfo> itemInfoList=itemInfoManageDao.itemInfoList(itemInfo);
		dataWrapper.setData(itemInfoList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> up(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.up(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> down(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.down(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(String itemId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemInfoManageDao.deleteItemInfo(itemId);
		itemInfoManageDao.deleteItemDetail(itemId);
		itemInfoManageDao.deleteItemValue(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public DataWrapper<Void> getItemSku(String itemId) {
		
		return null;
	}

	@Override
	public DataWrapper<Void> insert(ItemInfo itemInfo) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("itemId", itemInfo.getItemId());
		map.put("itemValueList", itemInfo.getItemValueList());
		itemInfoManageDao.insertItemPropertyd(map);
		itemInfoManageDao.insertItemDetail(itemInfo);
		itemInfoManageDao.insertItemValue(itemInfo);
		itemInfoManageDao.insertItemInfo(itemInfo);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		
		return dataWrapper;
	}

	

}
