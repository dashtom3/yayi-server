package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.dao.ItemManageDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemPropertyd;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.service.ItemManageService;
@Service
public class ItemManageServiceImpl implements ItemManageService{
	@Autowired
	private ItemBrandDao itemBrandDao;
	
	@Autowired
	private ItemManageDao itemManageDao;
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
		System.out.println(totalNumber);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber=page.getCurrentNumber();
		if(itemPropertyName==null||"".equals(itemPropertyName)){
			itemPropertyName=null;
		}
		Search search =new Search();
		search.setCurrentNumber(currentNumber);
		search.setNumberPerPage(numberPerPage);
		search.setItemBrandName(itemPropertyName);
		System.out.println(search.getNumberPerPage());//10
		System.out.println(search.getCurrentNumber());//0
		List<ItemProperty> itemPropertyList=itemManageDao.queryProperty(search);
		System.out.println(itemPropertyList.size());
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
	public DataWrapper<Void> updatePropertyd(String itemSKU, String itemPparam) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemPropertyd itemPropertyd=new ItemPropertyd();
		itemPropertyd.setItemSKU(itemSKU);
		itemPropertyd.setItemPparam(itemPparam);
		itemManageDao.updatePropertyd(itemPropertyd);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateProperty(Integer itemPropertyId,
			String itemPropertyName) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemProperty itemProperty =new ItemProperty();
		itemProperty.setItemPropertyId(itemPropertyId);
		itemProperty.setItemPropertyName(itemPropertyName);
		itemManageDao.updateProperty(itemProperty);
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
	public DataWrapper<Void> deleteItemClassify(Integer itemClassifyId,String itemClassifyName,Integer itemClassifyGrade) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemClassify itemClassify =new ItemClassify();
		itemClassify.setItemClassifyId(itemClassifyId);
		itemClassify.setItemClassifyName(itemClassifyName);
		itemClassify.setItemClassifyGrade(itemClassifyGrade);
		if(itemClassifyGrade==1){
			itemManageDao.deleteItemClassifyOne(itemClassify);
			String itemClassifyTwo=itemManageDao.queryItemClassifyByName(itemClassify);
			itemClassify.setItemClassifyName(itemClassifyTwo);
			itemManageDao.deleteItemClassifyTwoSon(itemClassify);
			itemManageDao.deleteItemClassifyOneSon(itemClassify);

		}else if(itemClassifyGrade==2){
			itemManageDao.deleteItemClassifyTwo(itemClassify);
			itemManageDao.deleteItemClassifyTwoSon(itemClassify);

		}else if(itemClassifyGrade==3){
			itemManageDao.deleteItemClassifyThree(itemClassify);

		}
		String itemClassifyNameA =itemManageDao.queryItemClassifyName(itemClassifyId);
		String itemClassifyNameB=itemManageDao.queryItemClassifySonName(itemClassifyNameA);
		itemManageDao.deleteItemClassify(itemClassifyNameB);
		itemManageDao.deleteItemClassify(itemClassifyNameA);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		String msg=dataWrapper.getErrorCode().getLabel();
		dataWrapper.setMsg(msg);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateItemClassify(Integer itemClassifyId,String itemClassifyName, 
			String itemOldName,Integer itemClassifyGrade) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemClassify itemClassify =new ItemClassify();
		itemClassify.setItemClassifyId(itemClassifyId);
		itemClassify.setItemPreviousClassify(itemOldName);
		itemClassify.setItemClassifyName(itemClassifyName);
		itemClassify.setItemClassifyGrade(itemClassifyGrade);
		if(itemClassifyGrade==1){
			itemManageDao.updateItemClassifyOne(itemClassify);
			itemManageDao.updateItemClassifyOneSon(itemClassify);
			
		}else if(itemClassifyGrade==2){
			itemManageDao.updateItemClassifyTwo(itemClassify);
			itemManageDao.updateItemClassifyTwoSon(itemClassify);

		}else if(itemClassifyGrade==3){
			itemManageDao.updateItemClassifyThree(itemClassify);

		}
		itemManageDao.updateItemClassify(itemClassify);
		
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
	public DataWrapper<Void> addItemClassify(String itemClassifyName,
			String itemPreviousClassify,Integer itemClassifyGrade) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		ItemClassify itemClassify=new ItemClassify();
		itemClassify.setItemClassifyName(itemClassifyName);
		itemClassify.setItemClassifyGrade(itemClassifyGrade);
		itemClassify.setItemPreviousClassify(itemPreviousClassify);
		System.out.println(itemClassifyGrade);
		if(itemClassifyGrade==1){
			itemManageDao.insertItemClassify(itemClassify);
			System.out.println(1);
		}else if(itemClassifyGrade==2){
			itemManageDao.insertItemClassifyTwo(itemClassify);
			System.out.println(2);
		}else if(itemClassifyGrade==3){
			itemManageDao.insertItemClassifyThree(itemClassify);
			System.out.println(3);
		}
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
			System.out.println(itemPparamList);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("itemPropertyId",itemPropertyId);
			map.put("itemPparamList", itemPparamList);
			itemManageDao.addPropertyd(map);
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			String msg=dataWrapper.getErrorCode().getLabel();
			dataWrapper.setMsg(msg);
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			String msg=dataWrapper.getErrorCode().getLabel();
			dataWrapper.setMsg(msg);
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
