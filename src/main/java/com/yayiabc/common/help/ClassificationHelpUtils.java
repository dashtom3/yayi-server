package com.yayiabc.common.help;

import java.util.concurrent.ConcurrentHashMap;


public class ClassificationHelpUtils {
	private ClassificationHelpUtils() {} 
	
	private static ConcurrentHashMap<String, Object> CHashMap=new ConcurrentHashMap<String, Object>();
	//private static HashMap<String, Object> HashMap=new HashMap<String,Object>();
	private static ClassificationHelpUtils single=null;  
	//静态工厂方法   
	public static ClassificationHelpUtils getInstance() {  
		if (single == null) {    
			single = new ClassificationHelpUtils();  
		}    
		return single;  
	}
	public ConcurrentHashMap<String, Object> getSynchronizedMap(){
		return CHashMap;
	}
}
