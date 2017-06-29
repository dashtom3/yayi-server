package com.yayiabc.http.mvc.pojo.utils;

import java.util.Date;

import com.yayiabc.common.utils.TimeUtil;



public class SkuUtil {
	private static int NUM=1;
	private static Date date=new Date();
	public static String getSkuCode(String userId){
		Date dat=new Date();
		String dateStr=TimeUtil.changeDateToString(dat);
		String oldDateStr=TimeUtil.changeDateToString(date);
		if(!oldDateStr.equals(dateStr)){
			NUM=1;
			date=dat;
		}
		String lastFourNum=getLastFourNum(NUM);
		NUM++;
		
		return "SP"+userId+dateStr+lastFourNum;
	}
	public static String getLastFourNum(int num){
		int k=0;
		String str=""+num;
		while(num/10!=0){
			k++;
		}
		for(int i=0;i<3-k;i++){
			str="0"+str;
		}
		return str;
	}
}
