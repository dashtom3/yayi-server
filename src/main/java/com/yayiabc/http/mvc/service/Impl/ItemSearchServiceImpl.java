package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemSearchDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemSearchServiceImpl implements ItemSearchService{
	
	@Autowired
	private ItemSearchDao itemSearchDao;

	@Override
	public DataWrapper<List<ItemInfo>> itemSearch(String keyWord,Integer currentPage,Integer numberPerPage,Integer rule) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		Search search =new Search();
		search.setCurrentPage(currentPage);
		search.setNumberPerPage(numberPerPage);
		search.setItemBrandName(keyWord);
		search.setRule(rule);
		Page page=new Page();
        page.setNumberPerPage(search.getNumberPerPage());
        page.setCurrentPage(search.getCurrentPage());
        int totalNumber=itemSearchDao.getCount(search);
        dataWrapper.setPage(page, totalNumber);
        search.setCurrentNumber(page.getCurrentNumber());
		List<ItemInfo> itemInfoList=itemSearchDao.itemSearch(search);
		if(itemInfoList==null){
			dataWrapper.setErrorCode(ErrorCodeEnum.NO_MESSAGE);
			String msg=ErrorCodeEnum.NO_MESSAGE.getLabel();
			dataWrapper.setMsg(msg);
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			String msg=ErrorCodeEnum.No_Error.getLabel();
			dataWrapper.setMsg(msg);
		}
		dataWrapper.setData(itemInfoList);
		return dataWrapper;
	}


	@Override
	public DataWrapper<List<ItemInfo>> search(Search search) {
		DataWrapper<List<ItemInfo>> dataWrapper =new DataWrapper<List<ItemInfo>>();
		Page page=new Page();
		Integer numberPerPage=search.getNumberPerPage()==null?10:search.getNumberPerPage();
		search.setNumberPerPage(numberPerPage);
		page.setNumberPerPage(numberPerPage);
		Integer currentPage=search.getCurrentPage()==null?1:search.getCurrentPage();
		page.setCurrentPage(currentPage);
		search.setCurrentNumber(page.getCurrentNumber());
		List<ItemInfo> itemInfoList=itemSearchDao.search(search);
		if(itemInfoList==null){
			dataWrapper.setErrorCode(ErrorCodeEnum.NO_MESSAGE);
		}
		Integer totalNumber=itemInfoList.size();
		dataWrapper.setPage(page,totalNumber);
		dataWrapper.setData(itemInfoList);
		return dataWrapper;
	}

}
