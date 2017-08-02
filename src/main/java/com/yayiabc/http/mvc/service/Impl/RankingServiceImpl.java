package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.getTimeUtil;
import com.yayiabc.http.mvc.dao.RankingDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;
import com.yayiabc.http.mvc.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

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
		if (nowYear.equals(year) && nowMonth.equals(month)) { // 返回当月销售排行榜
			String startDate = getTimeUtil.getFirstDayOfMonth(year, month); // 获得该月第一天
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = sdf.format(new Date());
			list = rankingDao.queryMonthRanking(1, startDate,endDate);
			dataWrapper.setData(list);
		} else { // 返回往月销售排行榜
			String tableName="rank_"+beYearMonth.substring(0, 4)+"_"+beYearMonth.substring(5, 7);
			int sign=rankingDao.queryRankingExist(tableName);
			if(sign==0){
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				dataWrapper.setMsg("无该月排行榜数据");
			}else{
				list = rankingDao.queryMonthRankingOld(tableName);
				dataWrapper.setData(list);
			}
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
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
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
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				dataWrapper.setMsg("当前销售员未上榜"+year+month);
				dataWrapper.setNum(saleNum);
			} else {
				dataWrapper.setNum(saleNum);
				dataWrapper.setData(ranking);
			}
		} else { 	// 返回往月销售员排行榜排名信息
			String tableName="rank_"+beYearMonth.substring(0, 4)+"_"+beYearMonth.substring(5, 7);
			Integer saleNum=rankingDao.getSaleCount(beYearMonth);
			int sign=rankingDao.queryRankingExist(tableName);
			if(sign==0){
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				dataWrapper.setMsg("无该月排行榜数据");
			}else{
				ranking = rankingDao.getSaleRankingOld(saleId, tableName);
				if(ranking == null){
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					dataWrapper.setMsg("当前销售员未上榜"+year+month);
					dataWrapper.setNum(saleNum);
				}else{
					dataWrapper.setNum(saleNum);
					dataWrapper.setData(ranking);
				}
			}
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
