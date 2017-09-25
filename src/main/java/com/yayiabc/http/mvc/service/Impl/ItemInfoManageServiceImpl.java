package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemInfoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.service.ItemInfoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemInfoManageServiceImpl implements ItemInfoManageService{
	
	@Autowired
	private ItemInfoManageDao itemInfoManageDao;

	@Override
	public DataWrapper<List<ItemInfo>> itemInfoList(String itemId,
			String itemName, String itemClassify, String itemBrandName,
			Integer state,Integer currentPage,Integer numberPerPage,Integer itemClassifyGrade) {
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		ItemInfo itemInfo =new ItemInfo();
		itemInfo.setItemId(itemId);
		itemInfo.setItemName(itemName);
		itemInfo.setOneClassify(itemClassify);
		itemInfo.setTwoClassify(itemBrandName);
		itemInfo.setState(state);
		List<ItemInfo> itemInfoList=null;
		Integer isThrow=0;
		if(itemClassify!=null&&!"".equals(itemClassify)){
			Integer count=itemInfoManageDao.getCountOneClassify(itemClassify);
			if(count!=0){
				isThrow=1;
			}else{
				isThrow=2;
			}
		}
		Integer totalNumber=0;
	    itemInfo.setIsThrow(isThrow);
		totalNumber=itemInfoManageDao.getCountOne(itemInfo);
		dataWrapper.setPage(page, totalNumber);
		itemInfo.setSales(page.getCurrentNumber());
		itemInfo.setItemStockNum(page.getNumberPerPage());
		itemInfoList=itemInfoManageDao.itemInfoListOne(itemInfo);
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
		itemInfoManageDao.deleteItemValue(itemId);
		itemInfoManageDao.deleteItemDetail(itemId);
		itemInfoManageDao.deleteItemInfo(itemId);
		itemInfoManageDao.deleteItemStar(itemId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addItem(ItemInfo itemInfo) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		itemInfo.getItemDetail().setItemId(itemInfo.getItemId());
		itemInfoManageDao.addItemDetail(itemInfo);
		for (ItemValue itemValue:itemInfo.getItemValueList()) {
			itemValue.setItemId(itemInfo.getItemId());
		}
		System.out.println(itemInfo.getItemValueList());
		itemInfoManageDao.addItemValue(itemInfo.getItemValueList());
		Double itemPrice =itemInfoManageDao.getMinPriceFromItemValue(itemInfo.getItemId());
		itemInfo.setItemPrice(itemPrice);
		itemInfoManageDao.addItemInfo(itemInfo);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateItem(ItemInfo itemInfo) {
		delete(itemInfo.getItemId());
		return addItem(itemInfo);
	}
}
