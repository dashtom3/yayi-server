package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.model.ItemShow;
import com.yayiabc.http.mvc.pojo.model.Property;
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
        List<String> starItemId=itemBrandDao.getItemIdByUserId(userId);
        int num=0;
        if(starItemId!=null){
        	if(starItemId.contains(itemId)){
        		num=1;
        	}
        }
        System.out.println(num);
        ItemInfo itemInfo = itemBrandDao.itemDetailDes(itemId);
        System.out.println(itemInfo);
        List<Property> propertyList=new ArrayList<Property>();
        Property propertyA =new Property();
        String propertyAName=itemBrandDao.getItemPropertyNameA(itemId);
        List<String> propertyAInfoList=itemBrandDao.getItemPropertyInfoA(itemId);
        propertyA.setPropertyName(propertyAName);
        propertyA.setPropertyInfoList(propertyAInfoList);
        Property propertyB =new Property();
        String propertyBName=itemBrandDao.getItemPropertyNameB(itemId);
        List<String> propertyBInfoList=itemBrandDao.getItemPropertyInfoB(itemId);
        propertyB.setPropertyName(propertyBName);
        propertyB.setPropertyInfoList(propertyBInfoList);
        Property propertyC =new Property();
        String propertyCName=itemBrandDao.getItemPropertyNameC(itemId);
        List<String> propertyCInfoList=itemBrandDao.getItemPropertyInfoC(itemId);
        propertyC.setPropertyName(propertyCName);
        propertyC.setPropertyInfoList(propertyCInfoList);
        Property propertyD =new Property();
        String propertyDName=itemBrandDao.getItemPropertyNameD(itemId);
        List<String> propertyDInfoList=itemBrandDao.getItemPropertyInfoD(itemId);
        propertyD.setPropertyName(propertyDName);
        propertyD.setPropertyInfoList(propertyDInfoList);
        Property propertyE =new Property();
        String propertyEName=itemBrandDao.getItemPropertyNameE(itemId);
        List<String> propertyEInfoList=itemBrandDao.getItemPropertyInfoE(itemId);
        propertyE.setPropertyName(propertyEName);
        propertyE.setPropertyInfoList(propertyEInfoList);
        Property propertyF =new Property();
        String propertyFName=itemBrandDao.getItemPropertyNameF(itemId);
        List<String> propertyFInfoList=itemBrandDao.getItemPropertyInfoF(itemId);
        propertyF.setPropertyName(propertyFName);
        propertyF.setPropertyInfoList(propertyFInfoList);
        propertyList.add(propertyA);
        propertyList.add(propertyB);
        propertyList.add(propertyC);
        propertyList.add(propertyD);
        propertyList.add(propertyE);
        propertyList.add(propertyF);
        itemInfo.setPropertyList(propertyList);
        dataWrapper.setData(itemInfo);
        System.out.println(itemInfo.getItemPrice());
        String msg=itemBrandDao.getItemSKUByPrice(itemInfo.getItemPrice(),itemId);
        System.out.println(msg);
        dataWrapper.setMsg(msg);
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

