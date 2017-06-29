package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.SessionManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.ItemShow;
import com.yayiabc.http.mvc.pojo.model.Search;
import com.yayiabc.http.mvc.service.ItemBrandService;

@Service
public class ItemBrandServiceImpl implements ItemBrandService{

    @Autowired
    private ItemBrandDao itemBrandDao;
    public ItemBrandServiceImpl()
    {
    }

    public DataWrapper<List<ItemBrand>> brandList(){
        DataWrapper<List<ItemBrand>> dataWrapper = new DataWrapper<List<ItemBrand>>();
        List<ItemBrand> itemBrandList = itemBrandDao.brandList();
        dataWrapper.setData(itemBrandList);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    public DataWrapper<List<ItemInfo>> brandItemList(Integer itemBrandId,Integer currentPage,Integer numberPerPage,Integer rule)
    {   
        DataWrapper<List<ItemInfo>> dataWrapper = new DataWrapper<List<ItemInfo>>();
        Search search =new Search();
        search.setItemBrandId(itemBrandId);
        search.setNumberPerPage(numberPerPage);
        search.setCurrentPage(currentPage);
        search.setRule(rule);
        Page page=new Page();
        page.setNumberPerPage(search.getNumberPerPage());
        page.setCurrentPage(search.getCurrentPage());
        int totalNumber=itemBrandDao.getCount(search);
        dataWrapper.setPage(page, totalNumber);
        search.setCurrentNumber(page.getCurrentNumber());
        List<ItemInfo> itemInfoList = itemBrandDao.brandItemList(search);
        dataWrapper.setData(itemInfoList);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    public DataWrapper<ItemInfo> itemDetailDes(String itemId,String token)
    {
        DataWrapper<ItemInfo> dataWrapper = new DataWrapper<ItemInfo>();
        String userId =itemBrandDao.getUserIdByToken(token);


        String starItemId=itemBrandDao.getItemIdByUserId(userId);

        int num=0;
        if(itemId.equals(starItemId)){
        	num=1;
        }

        ItemInfo itemInfo = itemBrandDao.itemDetailDes(itemId);
        dataWrapper.setData(itemInfo);
        dataWrapper.setNum(num);
        return dataWrapper;
    }

	@Override
	public DataWrapper<List<ItemShow>> itemShow() {
		DataWrapper<List<ItemShow>> dataWrapper =new DataWrapper<List<ItemShow>>();
		List<ItemShow> itemShowList=itemBrandDao.itemShow();
		dataWrapper.setData(itemShowList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

}

