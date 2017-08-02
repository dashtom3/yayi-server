package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yayiabc.http.mvc.dao.RankingDao;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;
import com.yayiabc.http.mvc.service.RankingService;

@Component
public class RankUtil {
	
	
	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private RankingDao rankingDao;
	/**
	 * 每月将销售员排行榜插入数据库中
	 */
	public void rank(){
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date now=calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String nowTime=sdf.format(now);
		DataWrapper<List<Ranking>> dataWrapper=rankingService.queryMonthRankingAll(nowTime);
		List<Ranking> rankingList=dataWrapper.getData();
		System.out.println(rankingList);
		System.out.println(dataWrapper.getMsg());
		SimpleDateFormat sdfTwo=new SimpleDateFormat("yyyy_MM");
		Integer saleCount=rankingDao.getSaleCountNow();
		rankingDao.saveSaleNum(saleCount,now);
		String tableName=sdfTwo.format(now);
		tableName="rank_"+tableName;
		rankingService.addRecord(tableName,rankingList);
		
	}
}
