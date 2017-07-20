package com.yayiabc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class getTimeUtil {
	//获得本月起止时间
		public static Map<String,String> getTime(){
			  Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
			  Map<String, String> map =new HashMap<String, String>(); 
			  int year = cal.get(Calendar.YEAR);    //获取年
			  int month =Integer.parseInt(getMonth());
			  String startDate=getFirstDayOfMonth(year, month);
			  String endDate=getLastDayOfMonth(year, month);
			  map.put("startDate",startDate);
			  map.put("endDate",endDate);
			  return map;
		}
	
	
	//获得上月起止时间
	public static Map<String,String> getLastTime(){
		  Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
		  Map<String, String> map =new HashMap<String, String>(); 
		  int year = cal.get(Calendar.YEAR);    //获取年
		  int month =Integer.parseInt(getLastMonth());
		  String startDate=getFirstDayOfMonth(year, month);
		  String endDate=getLastDayOfMonth(year, month);
		  map.put("startDate",startDate);
		  map.put("endDate",endDate);
		  return map;
	}
	
	
	 /**
     * 获取上一个月
     * 
     * @return
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }
	
    /**
     * 
     * 描述:获取下一个月.
     * 
     * @return
     */
    public static String getPreMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        cal.add(cal.MONTH, 1);
        SimpleDateFormat dft = new SimpleDateFormat("MM");
        String preMonth = dft.format(cal.getTime());
        return preMonth;
    }
    
    /**
     * 
     * 获取当前月份
     * 
     * @return
     */
    public static String getMonth(){
    	 Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
         cal.add(cal.MONTH,0);
         SimpleDateFormat dft = new SimpleDateFormat("MM");
         String month = dft.format(cal.getTime());
         return month;
    }
    
    /** 
    * 获得该月第一天 
    * @param year 
    * @param month 
    * @return 
    */  
    public static String getFirstDayOfMonth(int year,int month){  
            Calendar cal = Calendar.getInstance();  
            //设置年份  
            cal.set(Calendar.YEAR,year);  
            //设置月份  
            cal.set(Calendar.MONTH, month-1);  
            //获取某月最小天数  
            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);  
            //设置日历中月份的最小天数  
            cal.set(Calendar.DAY_OF_MONTH, firstDay);  
            //格式化日期  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String firstDayOfMonth = sdf.format(cal.getTime());  
            return firstDayOfMonth;  
        }  
      
    /** 
    * 获得该月最后一天 
    * @param year 
    * @param month 
    * @return 
    */  
    public static String getLastDayOfMonth(int year,int month){  
            Calendar cal = Calendar.getInstance();  
            //设置年份  
            cal.set(Calendar.YEAR,year);  
            //设置月份  
            cal.set(Calendar.MONTH, month-1);  
            //获取某月最大天数  
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
            //设置日历中月份的最大天数  
            cal.set(Calendar.DAY_OF_MONTH, lastDay);  
            //格式化日期  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String lastDayOfMonth = sdf.format(cal.getTime());  
            return lastDayOfMonth;  
        }  
}
