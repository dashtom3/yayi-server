package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.FindDao;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.FindService;
import com.yayiabc.http.mvc.service.VideoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService {

    @Autowired
    private FindDao findDao;

    @Autowired
    private VideoManageService videoManageService;

    @Override
    public DataWrapper<Object> findList(String keyWord, Integer type, Integer classify,Integer currentPage,Integer numberPerPage) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if(type==1){//病例

        }else if(type==2){//视频
            return videoManageService.showVid(3,classify,currentPage,numberPerPage,keyWord);
        }
        //3.问答,TODO
        return null;
    }
}
