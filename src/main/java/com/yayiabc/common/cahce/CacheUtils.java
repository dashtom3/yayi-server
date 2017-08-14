package com.yayiabc.common.cahce;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class CacheUtils {

	//缓存集合
  private static ConcurrentHashMap<String, Date> cacheMap=new ConcurrentHashMap<String, Date>();
  private static List<String> temporaryList =new ArrayList<String>();
	private  int ContinuedTime=/*24*60**/24*60*60*1000;//持续时间    一天
	private CacheUtils() {}  
	private static CacheUtils single=null;  
	//静态工厂方法   
	public static CacheUtils getInstance() {  
		if (single == null) {    
			single = new CacheUtils();  
		}    
		return single;  
	} 
	public boolean  addCache(String orderId,Date createrDate){
		boolean boo=false;
		try {
			cacheMap.put(orderId, createrDate);
			boo=true;
		} catch (Exception e) {
			// TODO: handle exception
		}	    
		return boo;
	}
	//获得缓存
	public Map<String,Date> getCacheMap(){
		
		return cacheMap;
	}
	/*//test
	public static void setCacheMap(Date date ) {
		cacheMap.put("f7598780-2bc1-4e8f-87d3-0cd88c900a630053",date);
	}*/
	//设置缓存持续时间
	public int getContinuedTime() {
		return ContinuedTime;
	}
	//得到缓存持续时间
	public void setContinuedTime(int continuedTime) {
		ContinuedTime = continuedTime;
	}
	private boolean qc(String orderId){
		for(String key:cacheMap.keySet()){
			if(orderId.equals(key)){
				cacheMap.remove(orderId);
				return true;
			}else{
				return false;
			}
		}
		return false;
		
	}
	public static List<String> getTemporaryList() {
		return temporaryList;
	}
	public static void setTemporaryList(List<String> temporaryList) {
		CacheUtils.temporaryList = temporaryList;
	}
	
}
