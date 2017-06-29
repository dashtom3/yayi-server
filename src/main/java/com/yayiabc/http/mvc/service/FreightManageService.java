package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;


public interface FreightManageService {
	//显示运费
	DataWrapper< List<PostFee>>    showFreight();
	   //自定义运费
	DataWrapper<Void> customFreight(PostFee postFee);
	DataWrapper<Void> deleteCustomFreight(Integer postFeeId);
	//显示包邮数据
	DataWrapper<List<FreeShipping> > showFreeShipp();
	 //add
	DataWrapper<Void> insertFreeShipp(FreeShipping f);
	//update
	DataWrapper<Void> updateFreeShipp( FreeShipping f);
}
