package com.yayiabc.http.mvc.controller.item;

import com.yayiabc.http.mvc.dao.ItemClassifyDao;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.service.ItemClassifyService;
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

    public void init(){
    	getClassify();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
    
    private void getClassify(){
    	classifyList=itemClassifyDao.showsTreeClassify();
    }
}
