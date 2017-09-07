package com.yayiabc.common.utils;

public class QbExchangeUtil {
	public static Double getQbByMoney(Integer qbNum,String qbType){
		Double monery=0.0;
		if(qbType.equals("c_qb")){
			monery=(qbNum*0.95);
		}else if(qbType.equals("b_qb")){
			monery=(qbNum*0.90);
		}else if(qbType.equals("a_qb")){
			monery=(qbNum*0.80);
		}
		return monery;
	}
}
