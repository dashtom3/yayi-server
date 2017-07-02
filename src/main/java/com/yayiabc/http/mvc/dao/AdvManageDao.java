package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.AdvChart;

public interface AdvManageDao {
	//显示广告信息
		List<AdvChart> showAdv();
		//新增广告信息
		int insertAdv(@Param("advChart")AdvChart advChart);
		//更改广告信息
		int updateAdv(AdvChart advChart);
		//删除广告信息
		int  deleteAdv(@Param("advId")Integer advId);
}
