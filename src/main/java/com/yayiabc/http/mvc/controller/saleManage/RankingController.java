package com.yayiabc.http.mvc.controller.saleManage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.SaleTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Ranking;
import com.yayiabc.http.mvc.service.RankingService;

@Controller
@RequestMapping("api/rankingList")
public class RankingController {
	
	@Autowired
	RankingService rankingService;
	
	/**
	 * 销售员查询排行榜
	 */
	@RequestMapping("list")
	@ResponseBody
	public DataWrapper<List<Ranking>> list(
			@RequestParam(value="beYearMonth",required=true)String beYearMonth
	){
		return rankingService.queryMonthRanking(beYearMonth);
	}
	
	/**
	 * 销售员排行榜排名信息
	 */
	@RequestMapping("compareData")
	@ResponseBody
	@SaleTokenValidate(description="销售员排行榜排名信息")
	public DataWrapper<Ranking> compareData(
			@RequestParam(value="beYearMonth",required=true)String beYearMonth,
			@RequestHeader(value="saleToken",required=true)String token
	){
		return rankingService.getSaleRanking(beYearMonth, token);
	
	}
}
