package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.model.ItemStatistics;
@Repository
public interface ItemStatisticsDao {
	List<ItemStatistics> query(
			@Param("itemName")String itemName,
			@Param("itemId")String itemId,
			@Param("itemSKU")String itemSKU,
			@Param("itemBrandName")String itemBrandName,
			@Param("startDate")String startDate,
			@Param("endDate")String endDate,
			@Param("page")Page page,
			@Param("state")Integer state);
	
	int getCount(@Param("itemName")String itemName,
				@Param("itemId")String itemId,
				@Param("itemSKU")String itemSKU,
				@Param("itemBrandName")String itemBrandName,
				@Param("startDate")String startDate,
				@Param("endDate")String endDate,
				@Param("state")Integer state);
}
