package com.yayiabc.http.mvc.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.getTimeUtil;
import com.yayiabc.http.mvc.dao.RankingDao;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;
import com.yayiabc.http.mvc.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	RankingDao rankingDao;

	@Override
	public DataWrapper<List<Ranking>> queryMonthRanking(String beYearMonth) {
		DataWrapper<List<Ranking>> dataWrapper = new DataWrapper<List<Ranking>>();
	//	Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		Integer nowYear = cal.get(Calendar.YEAR); // 获取当前年份
		Integer nowMonth = cal.get(Calendar.MONTH); // 获取当前月份
		Integer year = Integer.parseInt(beYearMonth.substring(0, 4)); // 获取输入的年份
		Integer month = Integer.parseInt(beYearMonth.substring(5, 7)); // 获取输入的月份
		if (nowYear.equals(year) == true && nowMonth.equals(month) == true) { // 判断接收的年月是否为当前年月
			String startDate = getTimeUtil.getFirstDayOfMonth(nowYear, nowMonth); //获得该月第一天
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = sdf.format(new Date());
			System.out.println("startDate" + startDate + "endDate" + endDate);
			List<Ranking> list = rankingDao.queryMonthRanking(1, startDate,endDate);
			dataWrapper.setData(list);
		} else {
			//
		}

		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Ranking>> queryMonthRankingAll(String beYearMonth) {
		DataWrapper<List<Ranking>> dataWrapper = new DataWrapper<List<Ranking>>();
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		Integer nowYear = cal.get(Calendar.YEAR); // 获取当前年份
		Integer nowMonth = cal.get(Calendar.MONTH); // 获取当前月份
		Integer year = Integer.parseInt(beYearMonth.substring(0, 4)); // 获取输入的年份
		Integer month = Integer.parseInt(beYearMonth.substring(5, 7)); // 获取输入的月份
		if (nowYear.equals(year) == true && nowMonth.equals(month) == true) { // 判断接收的年月是否为当前年月
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("当前排行榜未结算");
		} else {
			map = getTimeUtil.getYearMonthTime(year, month);
			String startDate = map.get("startDate");
			String endDate = map.get("endDate");
			System.out.println("startDate" + startDate + "endDate" + endDate);
			List<Ranking> list = rankingDao.queryMonthRanking(0, startDate,endDate);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}
	
	@Override
	public DataWrapper<Ranking> getSaleRanking(String token, String startDate,
			String endDate) {

		return null;
	}

	

}
