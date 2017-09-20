package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;

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
		jedis = new Jedis("127.0.0.1", 6379);	//连接redis服务器(在这里是连接本地的)
		jedis.auth("123");		//权限认证
		System.out.println("连接redis服务成功");
		RedisClient.getJedis();		//获取连接池
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
			System.out.println(i);
		}
		//反序列化
//		for(int i=0;i<rankingList.size();i++){
//			byte[] ranking = jedis.get((tableName+i).getBytes());
//			Ranking r = (Ranking)SerializeUtil.unserialize(ranking);
//			System.out.println(r.toString());
//		}
		RedisClient.returnResource(jedis);		//释放连接池
	}
}
