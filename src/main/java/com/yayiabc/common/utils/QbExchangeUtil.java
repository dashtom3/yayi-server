package com.yayiabc.common.utils;

public class QbExchangeUtil {
	public static double getQbByMoney(Integer qbNum,String qbType){
		
		
		
		double monery=0;
		/*StringBuffer  sb=new StringBuffer();
		if(0<qbNum&&qbNum<3000){
			sb.append(qbNum*0.95);
			sb.append();
		}else if(3000<=qbNum&&qbNum<12000){
			
		}else if(0<qbNum&&qbNum<3000){
			
		}*/
		if("c_qb".equals(qbType) &&qbNum>=1){
			monery=(qbNum*0.95);
		}else if("b_qb".equals(qbType) &&qbNum>=2000){
			monery=(qbNum*0.90);
		}else if("a_qb".equals(qbType) &&qbNum>=5000){
			monery=(qbNum*0.80);
		}else{
			 
		}
		return Double.parseDouble(String.format("%.2f", monery));
	}
	public static void main(String[] args) {
		System.out.println(getQbByMoney(12,"c_qb"));
	}
}
