package com.yayiabc.common.utils;

import java.util.ArrayList;
import java.util.Map;

public class CheckIsSignUtils {
    private  static ArrayList<String> list=new ArrayList<String>();
    private static CheckIsSignUtils checkIsSignUtils=null;
    
    private  CheckIsSignUtils(){
    	
    }
    public static CheckIsSignUtils getInstance() {  
        if (checkIsSignUtils == null) {    
        	checkIsSignUtils = new CheckIsSignUtils();  
        }    
       return checkIsSignUtils;  
   }  
   public ArrayList<String> getList(){
	   return list;
   }
}
