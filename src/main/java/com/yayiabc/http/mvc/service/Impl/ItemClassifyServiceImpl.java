package com.yayiabc.http.mvc.service.Impl;


import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.dao.ItemClassifyDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.pojo.model.SysResult;
import com.yayiabc.http.mvc.service.ItemClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemClassifyServiceImpl implements ItemClassifyService {
	@Autowired
	private ItemClassifyDao itemClassifyDao;
	@Autowired
	private ItemBrandDao itemBrandDao;

	@Override
	public DataWrapper<List<Classify>> showsTreeClassify() {
		DataWrapper<List<Classify>> dataWrapper=new DataWrapper<List<Classify>>();
		List<Classify> list= itemClassifyDao.showsTreeClassify();
		  if(list.isEmpty()){
			  dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		  }else{
			  dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			  dataWrapper.setData(list);
		  }
		  return dataWrapper;
	}

	/*@Override
	public DataWrapper<SysResult> getAllClassifyAndBrand() {
		DataWrapper<SysResult> dataWrapper=new DataWrapper<SysResult>();
		SysResult sysResult =new SysResult();
		List<ItemBrand> brandList=itemBrandDao.brandList();
		List<Classify> classifyList=itemClassifyDao.showsTreeClassify();
		sysResult.setClassifyList(classifyList);
		sysResult.setItemBrandList(brandList);
		dataWrapper.setData(sysResult);
		return dataWrapper;
	}*/

	@Override
	public DataWrapper<List<ItemInfo>> queryItemSearch(String oneClassify,
			String twoClassify, String threeClassify, String itemBrandName,
			Integer rule,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		Search search =new Search();
		search.setNumberPerPage(numberPerPage);
		search.setCurrentPage(currentPage);
		search.setOneClassify(oneClassify);
		search.setTwoClassify(twoClassify);
		search.setItemBrandName(itemBrandName);
		search.setThreeClassify(threeClassify);
		search.setRule(rule);
		Page page=new Page();
        page.setNumberPerPage(search.getNumberPerPage());
        page.setCurrentPage(search.getCurrentPage());
		int totalNumber=itemClassifyDao.getCount(search);
		dataWrapper.setPage(page, totalNumber);
		search.setCurrentNumber(page.getCurrentNumber());
		List<ItemInfo> itemInfoList=itemClassifyDao.queryItemSearch(search);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		dataWrapper.setData(itemInfoList);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<ItemInfo>> queryItemSearchGet(String oneClassify,
			String twoClassify, String threeClassify, String itemBrandName,
			Integer rule, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		Search search =new Search();
		search.setNumberPerPage(numberPerPage);
		search.setCurrentPage(currentPage);
		search.setOneClassify(oneClassify);
		search.setTwoClassify(twoClassify);
		search.setItemBrandName(itemBrandName);
		search.setThreeClassify(threeClassify);
		search.setRule(rule);
		Page page=new Page();
        page.setNumberPerPage(search.getNumberPerPage());
        page.setCurrentPage(search.getCurrentPage());
		int totalNumber=itemClassifyDao.getCountGet(search);
		dataWrapper.setPage(page, totalNumber);
		search.setCurrentNumber(page.getCurrentNumber());
		List<ItemInfo> itemInfoList=itemClassifyDao.queryItemSearchGet(search);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		dataWrapper.setData(itemInfoList);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<ItemInfo>> getAllRecommendItemList() {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		List<ItemInfo> itemInfoList=itemClassifyDao.getAllRecommendItemList();
		dataWrapper.setData(itemInfoList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<SysResult> getAllClassifyAndBrand() {
		DataWrapper<SysResult> dataWrapper=new DataWrapper<SysResult>();
		SysResult sysResult =new SysResult();
		List<ItemBrand> brandList=itemBrandDao.brandList();
		List<Classify> classifyList=itemClassifyDao.showsTreeClassify();
		sysResult.setClassifyList(classifyList);
		sysResult.setItemBrandList(brandList);
		dataWrapper.setData(sysResult);
		return dataWrapper;
	}

}
