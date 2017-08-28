package com.yayiabc.http.mvc.controller.item;


import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.ItemBrandDao;
import com.yayiabc.http.mvc.dao.ItemClassifyDao;
import com.yayiabc.http.mvc.pojo.model.Classify;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 小月亮 on 2017/8/22.
 */
@Component
public class ClassifyManage implements InitializingBean{

    public static List<Classify> classifyList;

    
    @Autowired
    private ItemClassifyDao itemClassifyDao;

    @Autowired
    private ItemBrandDao itemBrandDao;

    public void init(){

        getClassify();
        getBrand();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
    
    private void getClassify(){
    	classifyList=itemClassifyDao.showsTreeClassify();
    }

    private void getBrand(){
        RedisClient redis=RedisClient.getInstance();
        redis.set(itemBrandDao.brandList(),"itemBrandList");
    }
}
