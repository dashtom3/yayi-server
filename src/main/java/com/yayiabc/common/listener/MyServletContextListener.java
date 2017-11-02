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

    private RedisService redisService;

    private VideoManageDao videoManageDao;



    private Logger logger= LogManager.getLogger(MyServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        redisService= WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(RedisService.class);
        videoManageDao=WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(VideoManageDao.class);
        System.err.println("redisService引进来了");
        redisService.STRINGS.set("redis","创建了");
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

    //加载所有的视频列表进redis中,采用redis的map进行存储
    public void putVedioToRedis(){
        List<VidManage> videoList=videoManageDao.showVid(3,6,0,100000000);
        for (VidManage video:videoList
             ) {
            redisService.HASH.hset("videoList",video.getViId()+"", JSON.toJSONString(video));
            redisService.SORTSET.zadd("videoTime",video.getVedioTime().getTime(),video.getViId()+"");
        }
    }
}
