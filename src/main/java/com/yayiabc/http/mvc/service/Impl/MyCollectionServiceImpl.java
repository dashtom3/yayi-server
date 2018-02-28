package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.*;
import com.yayiabc.http.mvc.pojo.jpa.*;
import com.yayiabc.http.mvc.service.MyCollectionService;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MyCollectionServiceImpl implements MyCollectionService{

    @Autowired
    private RedisService redisService;

    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private CottomsPostDao cottomsPostDao;

    @Autowired
    private UserCenterStarDao userCenterStarDao;

    @Autowired
    private VideoManageDao videoManageDao;

    @Autowired
    private FaqDao faqDao;

    @Autowired
    private CollectDao collectDao;

    @Override
    public DataWrapper<Object> queryList(String type,Integer currentPage,Integer numberPerPage,String userId,Integer category) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        if("病例".equals(type)){
            return myCottomCollect(dataWrapper,page,userId,category);
        }else if("视频".equals(type)){
            return myVideoCollect(dataWrapper,page,userId,category);
        }else if("商品".equals(type)){
            return myItemCollect(dataWrapper,page,userId);
        }else if("问答".equals(type)){
            return myFaqCollect(dataWrapper,page,userId,category);
        }
        return dataWrapper;
    }

    //显示问答收藏列表
    //我的收藏
    private DataWrapper<Object> myFaqCollect(DataWrapper<Object> dataWrapper, Page page, String userId,Integer category) {
    	System.out.println(page);
        int totalNumber=collectDao.getMyCollectTotalNo(userId,"问答",category);
        List<FaqQuestion> faqQuestionList=collectDao.getMyCollectFaqList(page,userId,"问答",category);
        dataWrapper.setData(faqQuestionList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //显示病例收藏列表
    //我的收藏
    public DataWrapper<Object> myCottomCollect(DataWrapper<Object> dataWrapper,Page page,String userId,Integer category) {
        List<Integer> list=cottomsPostDao.queryMyCollect(userId,category,"病例");
        System.out.println(list);
        int totalNumber=0;
        List<CottomsPost> cottomsPosts=null;
        if(!list.isEmpty()){
            totalNumber=list.size();
            cottomsPosts = cottomsPostDao.myCollect(userId,page,"病例",category);
            System.err.println(cottomsPosts);
            for (CottomsPost cottomsPost : cottomsPosts) {
                cottomsPost.setChargeContent(null);
            }
        }
        dataWrapper.setData(cottomsPosts);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //显示商品的信息的收藏
    public DataWrapper<Object> myItemCollect(DataWrapper<Object> dataWrapper,Page page,String userId){
        //总条数
        int count=userCenterStarDao.queryCount(userId);
        List<MyStar> itemStarList=userCenterStarDao.shows(userId,page);
        dataWrapper.setData(itemStarList);
        dataWrapper.setPage(page,count);
        if(itemStarList==null){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
            dataWrapper.setMsg("暂无数据信息");
            return dataWrapper;
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
            return dataWrapper;
        }
    }

    //显示视频的收藏
    public DataWrapper<Object> myVideoCollect(DataWrapper<Object> dataWrapper,Page page,String userId,Integer category){
        int totalNumber=collectDao.getMyCollectTotalNo(userId,"视频",category);
        List<VidManage> vidManageList=collectDao.getMyCollectVideoList(page,userId,"视频",category);
        for (VidManage vidManage:vidManageList
                ) {
            String videoRout=videoManageDao.getVideoRoute(vidManage.getViId());
            ItemInfo itemInfo=videoManageDao.getVideoItem(videoRout);
            vidManage.setItemInfo(itemInfo);
            //填充是否已收藏
            if(userId!=null){
                if(cottomsPostDao.existCollect(vidManage.getViId()+"",userId,"视频")!=0){
                    vidManage.setIsStar(1);
                }
            }
        }
        dataWrapper.setData(vidManageList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }


}
