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

import redis.clients.jedis.Jedis;

@Component
public class RankUtil {
	private Jedis jedis;

	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private RankingDao rankingDao;
	/**
	 * 每月将销售员排行榜插入数据库中
	 */
	public void rank(){
		RedisClient red=RedisClient.getInstance();
		Jedis jedis=red.getJedis();	//连接redis服务器
			//权限认证
		System.out.println("连接redis服务成功");
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date now=calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String nowTime=sdf.format(now);
		Integer saleCount=rankingDao.getSaleCountNow();
		rankingDao.saveSaleNum(saleCount,now);	//保存每月销售员数量
		DataWrapper<List<Ranking>> dataWrapper=rankingService.queryMonthRankingAll(nowTime);
		List<Ranking> rankingList=dataWrapper.getData();
		SimpleDateFormat sdfTwo=new SimpleDateFormat("yyyy_MM");
		String tableName="rank_"+sdfTwo.format(now);
		//rankingService.addRecord(tableName,rankingList);		//创表
		//序列化后向redis集合添加数据
		for(int i=0;i<rankingList.size();i++){
			jedis.set((tableName+i).getBytes(),SerializeUtil.serialize(rankingList.get(i)));
		}
		//反序列化
//		for(int i=0;i<rankingList.size();i++){
//			byte[] ranking = jedis.get((tableName+i).getBytes());
//			Ranking r = (Ranking)SerializeUtil.unserialize(ranking);
//			System.out.println(r.toString());
//		}
		 RedisClient.getInstance().returnJedis(jedis);		//释放连接池
	}
}
