package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.dao.ItemClassifyDao;
import com.yayiabc.http.mvc.dao.ItemManageDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.service.ItemManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemManageServiceImpl implements ItemManageService{
	@Autowired
	private ItemBrandDao itemBrandDao;
	
	@Autowired
	private ItemManageDao itemManageDao;
	
	@Autowired
    ItemClassifyDao itemClassifyDao;
	/**
	 * 品牌查询
	 */
	@Override
	public DataWrapper<List<ItemBrand>> queryItemBrand(String itemBrandName,
			String itemBrandHome,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<ItemBrand>> dataWrapper=new DataWrapper<List<ItemBrand>>();
		ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandName(itemBrandName);
		itemBrand.setItemBrandHome(itemBrandHome);
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		Integer totalNumber=itemBrandDao.getCountOne(itemBrand);
		dataWrapper.setPage(page, totalNumber);
		Search search=new Search();
		search.setItemBrandName(itemBrandName);
		search.setOneClassify(itemBrandHome);
		search.setCurrentNumber(page.getCurrentNumber());
		search.setNumberPerPage(numberPerPage);
		List<ItemBrand> itemBrandList=itemBrandDao.queryItemBrand(search);
		dataWrapper.setData(itemBrandList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}
	
	/**
	 * 品牌删除
	 */
	@Override
	public DataWrapper<Void> deleteItemBrand(Integer itemBrandId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		try {
			int num=itemBrandDao.deleteItemBrand(itemBrandId);
			if(num!=0){
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			}else{
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} catch (Exception e) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		String msg=dataWrapper.getErrorCode().getLabel();
		dataWrapper.setMsg(msg);
		
		return dataWrapper;
	}
	
	/**
	 * 修改商品品牌
	 */
	@Override
	public DataWrapper<Void> updateItemBrand(Integer itemBrandId,String itemBrandName,
			String itemBrandLogo, String itemBrandHome) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandId(itemBrandId);
		itemBrand.setItemBrandName(itemBrandName);
		itemBrand.setItemBrandLogo(itemBrandLogo);
		itemBrand.setItemBrandHome(itemBrandHome);
		itemBrandDao.updateItemBrand(itemBrand);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<ItemProperty>> queryProperty(String itemPropertyName,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<ItemProperty>> dataWrapper =new DataWrapper<List<ItemProperty>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		if("".equals(currentPage)){
			currentPage=null;
		}
		if("".equals(numberPerPage)){
			numberPerPage=null;
		}
		Integer totalNumber=itemManageDao.getCountProperty(itemPropertyName);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber=page.getCurrentNumber();
		if(itemPropertyName==null||"".equals(itemPropertyName)){
			itemPropertyName=null;
		}
		Search search =new Search();
		search.setCurrentNumber(currentNumber);
		search.setNumberPerPage(numberPerPage);
		search.setItemBrandName(itemPropertyName);
		List<ItemProperty> itemPropertyList=itemManageDao.queryProperty(search);
		dataWrapper.setData(itemPropertyList);//4
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteProperty(Integer itemPropertyId) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.deletePropertyd(itemPropertyId);
		itemManageDao.deleteProperty(itemPropertyId);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deletePropertyd(String itemSKU) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.deletePropertydBySKU(itemSKU);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deletePropertydBySKU(String itemSKU) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.deletePropertydBySKU(itemSKU);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}



	@Override
	public DataWrapper<Void> updateProperty(Integer itemPropertyId,String itemPropertyName,
			List<String> itemPparamList) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemProperty itemProperty =new ItemProperty();
		itemProperty.setItemPropertyId(itemPropertyId);
		itemProperty.setItemPropertyName(itemPropertyName);
		itemManageDao.updateProperty(itemProperty);
		itemManageDao.deletePro(itemPropertyId);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("itemPropertyId",itemPropertyId);
		map.put("itemPparamList", itemPparamList);
		itemManageDao.addPropertyd(map);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<ItemClassify>> showItemClassify(
			String itemClassifyName, String itemPreviousClassify,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<ItemClassify>> dataWrapper=new DataWrapper<List<ItemClassify>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		ItemClassify itemClassify =new ItemClassify();
		itemClassify.setItemClassifyName(itemClassifyName);
		itemClassify.setItemPreviousClassify(itemPreviousClassify);
		Integer totalNumber=itemManageDao.getCount(itemClassify);
		dataWrapper.setPage(page, totalNumber);
		Search search =new Search();
		search.setCurrentNumber(page.getCurrentNumber());
		search.setNumberPerPage(numberPerPage);
		search.setOneClassify(itemClassifyName);
		search.setTwoClassify(itemPreviousClassify);
		List<ItemClassify> itemClassifyList=itemManageDao.showItemClassify(search);
		dataWrapper.setData(itemClassifyList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteItemClassify(ItemClassify itemClassify) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Integer count =itemManageDao.getCountItemClassify(itemClassify.getItemClassifyName());
		if(count==0){
			itemManageDao.deleteItemClassifyById(itemClassify.getItemClassifyId());
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.ITEM_LEAVE);
		}
		dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateItemClassify(ItemClassify itemClassify) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.updateItemClassify(itemClassify);
		itemManageDao.updateItemSubClassify(itemClassify);
		if(itemClassify.getItemClassifyGrade()==1){
			itemManageDao.updateItemInfo(itemClassify);
		}else if(itemClassify.getItemClassifyGrade()==2){
			itemManageDao.updateItemClassifyTwo(itemClassify);
		}
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}






	@Override
	public DataWrapper<Void> addItemBrand(String itemBrandName,
			String itemBrandHome, String itemBrandLogo) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemBrand itemBrand =new ItemBrand();
		itemBrand.setItemBrandName(itemBrandName);
		itemBrand.setItemBrandHome(itemBrandHome);
		itemBrand.setItemBrandLogo(itemBrandLogo);
		itemBrandDao.addItemBrand(itemBrand);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addProperty(String itemPropertyName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.addProperty(itemPropertyName);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addItemClassify(ItemClassify itemClassify) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.addItemClassify(itemClassify);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}





	@Override
	public DataWrapper<Void> addPropertyAndPropertyName(
			String itemPropertyName, List<String> itemPparamList) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		int count=itemManageDao.getCountByItemPropertyName(itemPropertyName);
		if(count==0){
			itemManageDao.addProperty(itemPropertyName);
			Integer itemPropertyId =itemManageDao.queryItemPropertyIdByName(itemPropertyName);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("itemPropertyId",itemPropertyId);
			map.put("itemPparamList", itemPparamList);
			itemManageDao.addPropertyd(map);
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.PROPERTY_ALREADY_EXIST);
		}
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addToPropertyd(Integer itemPid, String itemPparam) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		itemManageDao.addToPropertyd(itemPid,itemPparam);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	
	
	
	

}
