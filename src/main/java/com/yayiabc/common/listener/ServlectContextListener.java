    package com.yayiabc.common.listener;  
      
    import javax.servlet.ServletContextEvent;  
    import javax.servlet.ServletContextListener;

import com.yayiabc.common.utils.CheckIsSignUtils;  
    //创建的类名根据需要定义，但一定要实现ServletContextListener接口  
    public class ServlectContextListener implements ServletContextListener {  
      
          
        @Override  
        public void contextInitialized(ServletContextEvent arg0) {  
        	CheckIsSignUtils c=  CheckIsSignUtils.getInstance();
        	c.getList().add("初始化数据，防止java.lang.NullPointerException");
        }  
          
        @Override  
        public void contextDestroyed(ServletContextEvent arg0) {  
            // TODO Auto-generated method stub  
              
        }  
      
          
    }  
