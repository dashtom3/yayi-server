package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.SaleStatistics;
@Repository
public interface SaleStatisticsDao {
	List<SaleStatistics> query(@Param("phone")String phone,@Param("trueName")String trueName,@Param("page")Page page);

	int getCount(@Param("phone")String phone,@Param("trueName")String trueName);
}
