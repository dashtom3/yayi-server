package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.Balance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CornDao {

	List<String> getSaleIdList();

	Double getTotalHaoCaiMoneyBySaleId(String saleId);

	Double getTotalGongJuMoneyBySaleId(String saleId);

	Double getTotalHaoCaiRefundBySaleId(String saleId);

	Double getTotalGongJuRefundBySaleId(String saleId);

	Double getLatestBalanceBySaleId(String saleId);

	void addBalancePerMonth(Balance balanceMonthCash);

	void updateSaleInfo(@Param("saleId")String saleId,@Param("money")Integer money);

	void addBalance();
}
