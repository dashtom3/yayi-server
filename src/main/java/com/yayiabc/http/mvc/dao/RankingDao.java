package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.Ranking;
@Repository
public interface RankingDao {
	//查询实时月排行榜，从sale_income表，state为1时查询前20条
	List<Ranking> queryMonthRanking(@Param("state")Integer state,@Param("startDate")String startDate,@Param("endDate")String endDate);

	//销售员当前排名数据实时
	Ranking getSaleRankingNow(@Param("saleId")String saleId,@Param("startDate")String startDate,@Param("endDate")String endDate);

	//查询销售员总人数，实时
	int getSaleCountNow();
	
	//保存每月销售员人数
	void saveSaleNum(@Param("saleNum")Integer saleNum);
	
	//查询销售员总人数，往月
	int getSaleCount(@Param("year")String year,@Param("month")String month);
}
