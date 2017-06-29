package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;

public interface AdvManageService {
   //显示广告信息
	DataWrapper<List<AdvChart>> showAdv();
	//新增广告信息
	DataWrapper<Void> insertAdv(AdvChart advChart);
	//更改广告信息
	DataWrapper<Void> updateAdv(AdvChart advChart);
	//删除广告信息
	DataWrapper<Void> deleteAdv(Integer advId);
	
}
