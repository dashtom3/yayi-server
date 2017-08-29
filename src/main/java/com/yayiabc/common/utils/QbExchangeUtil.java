package com.yayiabc.common.utils;

public class QbExchangeUtil {
	public static Integer getQbByMoney(Integer qb,String qbType){
		Integer monery=0;
		if(qbType.equals("cQb")){
			monery=(int)(qb*0.95);
		}else if(qbType.equals("bQb")){
			monery=(int)(qb*0.90);
		}else{
			monery=(int)(qb*1.25);
		}
		return monery;
	}
}
