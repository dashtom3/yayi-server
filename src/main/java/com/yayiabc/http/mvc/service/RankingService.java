package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;

public interface RankingService {
	DataWrapper<List<Ranking>> queryMonthRanking(String beYearMonth);
	
	DataWrapper<List<Ranking>> queryMonthRankingAll(String beYearMonth);
	
	DataWrapper<Ranking> getSaleRanking(String token,String startDate,String endDate);
	
}
