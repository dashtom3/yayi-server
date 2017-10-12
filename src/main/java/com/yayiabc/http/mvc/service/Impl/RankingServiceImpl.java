package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.common.utils.SerializeUtil;
import org.apache.poi.ss.formula.functions.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.getTimeUtil;
import com.yayiabc.http.mvc.dao.RankingDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;
import com.yayiabc.http.mvc.service.RankingService;
import redis.clients.jedis.Jedis;

@Service
public class RankingServiceImpl implements RankingService {
	private Jedis jedis;
	@Autowired
	RankingDao rankingDao;
	@Autowired
	SaleLogDao saleLogDao;
	
	@Override
	public DataWrapper<List<Ranking>> queryMonthRanking(String beYearMonth) {
		DataWrapper<List<Ranking>> dataWrapper = new DataWrapper<List<Ranking>>();
		List<Ranking> list=new ArrayList<Ranking>();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		Integer nowYear = cal.get(Calendar.YEAR); // 获取当前年份
		Integer nowMonth = (cal.get(Calendar.MONTH)+1); // 获取当前月份
		Integer year = Integer.parseInt(beYearMonth.substring(0, 4)); // 获取输入的年份
		Integer month = Integer.parseInt(beYearMonth.substring(5, 7)); // 获取输入的月份
		if (nowYear.equals(year) && nowMonth.equals(month)) {
			/**
			 * 返回当月销售排行榜数据
			 */
			String startDate = getTimeUtil.getFirstDayOfMonth(year, month); // 获得该月第一天
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = sdf.format(new Date());
			list = rankingDao.queryMonthRanking(1, startDate,endDate);
			if(list==null){
				dataWrapper.setData(null);
			}else{
				dataWrapper.setData(list);
			}
		} else {
			/**
			 * 连接redis
			 */
			jedis=new Jedis("127.0.0.1",6379);
			jedis.auth("123");
			//RedisClient.getJedis();
			/**
			 * 返回往月销售排行榜数据
			 */
			String tableName="rank_"+beYearMonth.substring(0, 4)+"_"+beYearMonth.substring(5, 7);
			int sign=jedis.keys(tableName+"*").size();
			//int sign=rankingDao.queryRankingExist(tableName);		//查询月排行榜表是否存在
			if(sign==0){
				dataWrapper.setData(null);
				dataWrapper.setMsg("无该月排行榜数据");
			}else{
				//list = rankingDao.queryMonthRankingOld(tableName);
				for(int i=0;i<sign;i++){
					byte[] ranking=jedis.get((tableName+i).getBytes());
					Ranking r=(Ranking) SerializeUtil.unserialize(ranking);
					list.add(r);
				}
				/**
				 * 对list集合进行排序根据RowNum
				 */
//				Collections.sort(list, new Comparator<Ranking>() {
//					@Override
//					public int compare(Ranking o1, Ranking o2) {
//						if(o1.getRowNum()>o2.getRowNum()){
//							return 1;
//						}
//						if(o1.getRowNum()==o2.getRowNum()){
//							return 0;
//						}
//						return -1;
//					}
//				});
				dataWrapper.setData(list);
			}
			 	//释放连接池
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Ranking>> queryMonthRankingAll(String beYearMonth) {
		DataWrapper<List<Ranking>> dataWrapper = new DataWrapper<List<Ranking>>();
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		Integer nowYear = cal.get(Calendar.YEAR); // 获取当前年份
		Integer nowMonth = (cal.get(Calendar.MONTH)+1); // 获取当前月份
		Integer year = Integer.parseInt(beYearMonth.substring(0, 4)); // 获取输入的年份
		Integer month = Integer.parseInt(beYearMonth.substring(5, 7)); // 获取输入的月份
		if (nowYear.equals(year) && nowMonth.equals(month)) { // 判断接收的年月是否为当前年月
			dataWrapper.setMsg("当前排行榜未结算");
		} else {
			map = getTimeUtil.getYearMonthTime(year, month);
			String startDate = map.get("startDate");
			String endDate = map.get("endDate");
			List<Ranking> list = rankingDao.queryMonthRanking(0, startDate,endDate);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Ranking> getSaleRanking(String beYearMonth,String token) {
		DataWrapper<Ranking> dataWrapper = new DataWrapper<Ranking>();
		Ranking ranking =new Ranking();
		Map<String, String> map = new HashMap<String, String>();
		String saleId = saleLogDao.getSaleIdByToken(token);
		Calendar cal = Calendar.getInstance();
		Integer nowYear = cal.get(Calendar.YEAR);
		Integer nowMonth = (cal.get(Calendar.MONTH)+1);
		Integer year = Integer.parseInt(beYearMonth.substring(0, 4));
		Integer month = Integer.parseInt(beYearMonth.substring(5, 7));
		map = getTimeUtil.getYearMonthTime(year, month);
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");
		if (nowYear.equals(year) && nowMonth.equals(month)) { // 返回当月销售员排行榜排名信息
			ranking = rankingDao.getSaleRankingNow(saleId, startDate,endDate);
			Integer saleNum = rankingDao.getSaleCountNow();	//返回实时销售员人数信息
			if (ranking == null) {
				dataWrapper.setMsg("当前销售员未上榜"+year+month);
				dataWrapper.setNum(saleNum);
			} else {
				dataWrapper.setNum(saleNum);
				dataWrapper.setData(ranking);
			}
		} else { 	// 返回往月销售员排行榜排名信息
			/**
			 * 连接redis
			 */
			jedis=new Jedis("127.0.0.1",6379);
			jedis.auth("123");
			//RedisClient.getJedis();
			String tableName="rank_"+beYearMonth.substring(0, 4)+"_"+beYearMonth.substring(5, 7);
			Integer saleNum=rankingDao.getSaleCount(beYearMonth);
			//int sign=rankingDao.queryRankingExist(tableName);
			int sign=jedis.keys(tableName+"*").size();
			if(sign==0){
				dataWrapper.setMsg("无该月排行榜数据");
			}else{
				//ranking = rankingDao.getSaleRankingOld(saleId, tableName);
				for(int i=0;i<sign;i++){
					byte[] rank=jedis.get((tableName+i).getBytes());
					Ranking r=(Ranking) SerializeUtil.unserialize(rank);
					if(saleId.equals(r.getSaleId())){
						ranking=r;
						break;
					}
					ranking=null;
				}
				if(ranking == null){
					dataWrapper.setMsg("当前销售员未上榜"+year+month);
					dataWrapper.setNum(saleNum);
				}else{
					dataWrapper.setNum(saleNum);
					dataWrapper.setData(ranking);
				}
			}
			// RedisClient.getInstance().returnJedis(jedis);	//释放连接池
		}
		return dataWrapper;
	}

	@Override
	public void addRecord(String tableName, List<Ranking> rankingList) {
		HashMap<String, Object> map =new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("rankingList", rankingList);
		rankingDao.addRecord(map);
	}

}
