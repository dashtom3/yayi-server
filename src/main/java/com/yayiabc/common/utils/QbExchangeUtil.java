package com.yayiabc.common.utils;

public class QbExchangeUtil {
	public static Integer getQbByMoney(Integer money){
		Integer qb=0;
		if(money<1000){
			qb=money;
		}else if(money<2000){
			qb=(int)(money*1.1);
		}else if(money<5000){
			qb=(int)(money*1.15);
		}else if(money<10000){
			qb=(int)(money*1.2);
		}else{
			qb=(int)(money*1.25);
		}
		return qb;
	}
}
