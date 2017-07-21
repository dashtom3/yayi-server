package com.yayiabc.common.utils;


import java.util.Date;

public class CornUtil {
	private static int counter = 0;  
	    protected void execute()  {  
	        long ms = System.currentTimeMillis();  
	        System.out.println("\t\t" + new Date(ms));  
	        System.out.println("(" + counter++ + ")");  
	        

	    }

}
