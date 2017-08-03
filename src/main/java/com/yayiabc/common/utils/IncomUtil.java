package com.yayiabc.common.utils;

import java.text.DecimalFormat;




/**
 * 销售员收入规则
 * @author 小月亮
 *
 */

public class IncomUtil {
	
	//耗材类产品收益规则
	/*本期创客销售之耗材类产品的收益计算标准如下：

	(1) 当期销售额未达1000元，按其实际销售额乘以5%计算收益；

	(2) 当期销售额满1000元未达2000元，按其实际销售额乘以8%计算收益；

	(3) 当期销售额满2000元未达3000元，按其实际销售额乘以12%计算收益；

	(4) 当期销售额达到3000元及其以上，按其实际销售额乘以16%计算收益。*/
	public static Double getMoneyByHaoCai(Double totalMoney,Double realMoney){
		Double money=0.0;
		if(totalMoney<1000.0){
			money=realMoney*0.05;
		}else if(totalMoney<2000.0){
			money=realMoney*0.08;
		}else if(totalMoney<3000){
			money=realMoney*0.12;
		}else{
			money=realMoney*0.16;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		money=Double.parseDouble(df.format(money));
		return money;
	}
	
	//工具设备类产品收益规则
	/*(1) 当期销售额未达10000元，按其实际销售额乘以8%计算收益；

	(2) 当期销售额达到10000元及其以上，按其实际销售额乘以15%计算收益。
	*/
	public static Double  getMoneyByGongJu(Double totalMoney,Double realMoney){
		Double money=0.0;
		if(totalMoney<10000){
			money=realMoney*0.08;
		}else {
			money=realMoney*0.15;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		money=Double.parseDouble(df.format(money));
		return money;
	}
}
