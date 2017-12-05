package com.yayiabc.common.listener;


import com.alibaba.fastjson.JSON;
import com.yayiabc.http.mvc.dao.VideoManageDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class MyServletContextListener implements ServletContextListener{




    private VideoManageDao videoManageDao;

    private Logger logger= LogManager.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("servletContext初始化开始");
        System.err.println("服务器启动了");
        logger.debug("一系列初始化的操作");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("servletContext销毁");
        System.err.println("服务器销毁了");
        logger.debug("一系列逆初始化操作");
    }


}
