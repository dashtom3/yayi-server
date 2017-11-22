package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.service.CottomsPostService;
import com.yayiabc.http.mvc.service.FindService;
import com.yayiabc.http.mvc.service.VideoManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService {



    @Autowired
    private VideoManageService videoManageService;

    @Autowired
    private CottomsPostService cottomsPostService;

    @Override
    public DataWrapper<Object> findList(String keyWord, Integer type, Integer classify,Integer currentPage,Integer numberPerPage) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if(type==1){//病例
            return cottomsPostService.queryPost(currentPage,numberPerPage,classify,0,1,null,1,keyWord);
        }else if(type==2){//视频
            return videoManageService.showVid(3,classify,currentPage,numberPerPage,keyWord,null);
        }
        //3.问答,TODO
        return null;
    }
}
