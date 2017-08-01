package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;

public interface RankingService {
	DataWrapper<List<Ranking>> queryMonthRanking(String beYearMonth);	//排行榜
	
	DataWrapper<List<Ranking>> queryMonthRankingAll(String beYearMonth);	//每月结算查询方法

	void addRecord(String tableName,List<Ranking> rankingList);
	
	DataWrapper<Ranking> getSaleRanking(String beYearMonth,String token);

}
