package com.yayiabc.common.utils;





import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.http.mvc.service.CornService;





public class CornUtil {
	
	@Autowired
	private CornService cornService;
	

    protected void execute()  {   
        
      
       
       cornService.addBalance();
       
       
        
    }
	
	
    
   

}
