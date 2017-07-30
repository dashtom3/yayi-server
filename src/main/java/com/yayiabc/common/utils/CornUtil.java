package com.yayiabc.common.utils;





import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.http.mvc.service.CornService;





public class CornUtil {
	
	@Autowired
	private CornService cornService;
	

    protected void execute()  {   
        
      
       System.out.println("开始执行定时任务");
       cornService.addBalance();
       
       
        
    }
	
	
    
   

}
