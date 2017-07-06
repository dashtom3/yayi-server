package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ItemIdUtils {
	
	public static String getItemId(){ 
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		String str=sdf.format(date);
		return str;
		
	}
}
