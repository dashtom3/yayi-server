package com.yayiabc.common.utils;

public class QbExchangeUtil {
	public static Double getQbByMoney(Integer qbNum,String qbType){
		
		
		
		Double monery=0.0;
		/*StringBuffer  sb=new StringBuffer();
		if(0<qbNum&&qbNum<3000){
			sb.append(qbNum*0.95);
			sb.append();
		}else if(3000<=qbNum&&qbNum<12000){
			
		}else if(0<qbNum&&qbNum<3000){
			
		}*/
		if(qbType.equals("c_qb")&&qbNum>=1){
			monery=(qbNum*0.95);
		}else if(qbType.equals("b_qb")&&qbNum>=2000){
			monery=(qbNum*0.90);
		}else if(qbType.equals("a_qb")&&qbNum>=5000){
			monery=(qbNum*0.80);
		}else{
			 
		}
		return monery;
	}
}
