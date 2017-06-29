package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ItemIdUtils {
	private static int count=0;
	
	public static String getItemId(){
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		String str=sdf.format(date);
		str+=getLastNum();
		return str;
		
	}
	public static String getLastNum(){
		if(count>=8000){
			count=0;
		}
		count++;
		int num=0;
		String str="";
		while(count/10!=0){
			num++;
		}
		for(int i=1;i<4-num;i++){
			str+="0";
		}
		str+=count;
		return str;
	}
}
