package com.yayiabc.common.listener;

import com.alibaba.fastjson.JSON;
import com.yayiabc.http.mvc.dao.VideoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.RedisService;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class MyServletContextListener implements ServletContextListener{


   // private RedisService redisService;

    private VideoManageDao videoManageDao;



    private Logger logger= LogManager.getLogger(MyServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("servletContext销毁");
        System.err.println("服务器销毁了");
        logger.debug("一系列逆初始化操作");
    }


}
