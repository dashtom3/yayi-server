package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ItemIdUtils {
	
	public static String getItemId(){ 
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		String str=sdf.format(date);
		return str+new Random().nextInt(1000);
		
	}


}
