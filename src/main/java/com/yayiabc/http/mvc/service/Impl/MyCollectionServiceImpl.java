package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.*;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;
import com.yayiabc.http.mvc.pojo.jpa.MyStar;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
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

    @Override
    public DataWrapper<Object> queryList(String token, Integer type,Integer currentPage,Integer numberPerPage) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        Assert.isTrue(token!=null,"请登录后查看");
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        String userId=utilsDao.getUserID(token);
        //1.病例收藏列表
        if(type==1){
            return myCottomCollect(currentPage,numberPerPage,dataWrapper,page,userId);
        }else if(type==2){//2.视频收藏列表
            return myVideoCollect(currentPage,numberPerPage,dataWrapper,page,userId);
        }else if(type==3){//3.商品收藏列表
            return myItemCollect(currentPage,numberPerPage,dataWrapper,page,userId);
        }else if(type==4){//4.问答收藏列表
            return myFaqCollect(currentPage,numberPerPage,dataWrapper,page,userId);
        }
        return null;
    }

    //显示问答收藏列表
    //我的收藏
    private DataWrapper<Object> myFaqCollect(Integer currentPage, Integer numberPerPage, DataWrapper<Object> dataWrapper, Page page, String userId) {
        int totalNumber=(int)redisService.SETS.scard(userId+"问答收藏列表");
        System.out.println("totalNumber"+totalNumber);
        Set<String> idSet=redisService.SETS.smembers(userId+"问答收藏列表");
        List<String> idList=new ArrayList<String>();
        idList.addAll(idSet);
        List<FaqQuestion> faqQuestionList=null;
        if(idList.size()!=0){
            faqQuestionList=faqDao.queryMyCollect(idList,page.getCurrentNumber(),numberPerPage);
        }
        dataWrapper.setData(faqQuestionList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //显示病例收藏列表
    //我的收藏
    public DataWrapper<Object> myCottomCollect(Integer currentPage,Integer numberPerPage,DataWrapper<Object> dataWrapper,Page page,String userId) {
        List<Integer> list=cottomsPostDao.queryMyCollect(userId);
        System.out.println(list);
        int totalNumber=0;
        List<CottomsPost> cottomsPosts=null;
        if(!list.isEmpty()){
            totalNumber=list.size();
            cottomsPosts = cottomsPostDao.myCollect(list,page);
            System.err.println(cottomsPosts);
            for (CottomsPost cottomsPost : cottomsPosts) {
                cottomsPost.setChargeContent(null);
                int readNumber = (int)redisService.SORTSET.zscore("阅读数",cottomsPost.getPostId()+"");//阅读数
                int commentNumber = (int)redisService.LISTS.llen("病例评论"+cottomsPost.getPostId());//评论数
                int favourNumber = (int)redisService.SETS.scard("点赞用户列表病例:"+cottomsPost.getPostId());//点赞数
                cottomsPost.setReadNumber(readNumber);
                cottomsPost.setPostFavour(favourNumber);
                cottomsPost.setCommentNumber(commentNumber);
            }
        }
        dataWrapper.setData(cottomsPosts);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //显示商品的信息的收藏
    public DataWrapper<Object> myItemCollect(Integer currentPage,Integer numberPerPage,DataWrapper<Object> dataWrapper,Page page,String userId){
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
    public DataWrapper<Object> myVideoCollect(Integer currentPage,Integer numberPerPage,DataWrapper<Object> dataWrapper,Page page,String userId){
        int totalNumber=(int)redisService.SETS.scard(userId+"视频收藏列表");
        System.out.println("totalNumber"+totalNumber);
        Set<String> idSet=redisService.SETS.smembers(userId+"视频收藏列表");
        List<String> idList=new ArrayList<String>();
        idList.addAll(idSet);
        List<VidManage> vidManageList=null;
        if(idList.size()!=0){
            vidManageList=videoManageDao.queryMyCollect(idList,page.getCurrentNumber(),numberPerPage);
        }
        dataWrapper.setData(vidManageList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }


}
