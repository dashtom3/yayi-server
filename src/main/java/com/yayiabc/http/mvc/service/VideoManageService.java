package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;




public interface VideoManageService {

	DataWrapper<Object>  showVid(Integer rule,Integer videoCategory,Integer currentPage,Integer numberPerPage,String keyWord,String token);

	DataWrapper<Void> updateVid(VidManage vidManage);

	 DataWrapper<Void> insertVid(VidManage vidManage);

	 DataWrapper<Void> deleteVid(Integer viId);

	DataWrapper<Void> play(Integer viId);

	DataWrapper<VidManage> detail(Integer viId,String token);


	DataWrapper<ItemInfo> videoItem(Integer viId);


}
