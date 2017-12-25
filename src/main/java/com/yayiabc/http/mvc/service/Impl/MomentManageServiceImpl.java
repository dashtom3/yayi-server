package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yayiabc.common.utils.GlobalVariables;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.service.CommentService;
import com.yayiabc.http.mvc.service.ZanService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.MomentManageDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.MomentManageService;
import com.yayiabc.http.mvc.service.RedisService;
import redis.clients.jedis.Jedis;

@Service
public class MomentManageServiceImpl implements MomentManageService{



    @Autowired
    private MomentManageDao momentManageDao;
    @Autowired
    private UtilsDao utilsDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ZanService zanService;


    @Override
    public DataWrapper<Void> add(Moment moment,String token) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        User user=utilsDao.getUserByToken(token);
        moment.setUserId(user.getUserId());
        try {
            if (moment.getMomentType() == GlobalVariables.BASE_MOMENT) {
                momentManageDao.addLower(moment);
            } else {
                momentManageDao.addHigh(moment);
            }
        }catch (Exception e){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> delete(Integer momentId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        momentManageDao.deleteMoment(momentId);
        Jedis jedis=redisService.getJedis();
        //删除评论列表
        jedis.del("牙医圈评论"+momentId);
        //删除点赞计数列表
        redisService.SORTSET.zrem("点赞计数列表牙医圈",momentId+"");
        //删除点赞用户列表
        jedis.del("点赞用户列表牙医圈:"+momentId);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage,String token) {
        return getMommentList(currentPage,numberPerPage,token,1);
    }

    @Override
    public DataWrapper<List<Moment>> myMoment(Integer currentPage, Integer numberPerPage, String token) {
        return getMommentList(currentPage,numberPerPage,token,2);
    }

    @Override
    public DataWrapper<Moment> detail(Integer momentId, String token) {
        DataWrapper<Moment> dataWrapper=new DataWrapper<Moment>();
        Moment moment=momentManageDao.getMomentByMomentId(momentId);
        User user=null;
        String userId=null;
        if(token!=null){
            user=utilsDao.getUserByToken(token);
            userId=user.getUserId();
        }
        fillCommentAndUpvoteMessage(moment,userId);
        dataWrapper.setData(moment);
        return dataWrapper;
    }


    //获取牙医圈的评论列表
    public List<SubComment> getSubCommentList(Integer momentId){
        List<SubComment> subCommentList=momentManageDao.getMomentCommentList(momentId);
        return subCommentList;
    }

    //查询列表 1表示朋友圈的动态列表，2.表示我的个人动态
    public DataWrapper<List<Moment>> getMommentList(Integer currentPage, Integer numberPerPage,String token,Integer type){
        System.out.println("token为"+token);
        DataWrapper<List<Moment>> dataWrapper =new DataWrapper<List<Moment>>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        User user=null;
        String userId=null;
        if(token!=null){
            userId=utilsDao.getUserID(token);
        }
        System.out.println(userId);
        int totalNumber=momentManageDao.getMomentTotalNumber(userId,type);;//总条数
        List<Moment> momentList=momentManageDao.queryList(page,userId,type);//数据集
        for (Moment moment:momentList
                ) {
            fillCommentAndUpvoteMessage(moment,userId);
        }
        dataWrapper.setData(momentList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //填充单条牙医圈动态的评论信息和点赞信息
    public void fillCommentAndUpvoteMessage(Moment moment,String userId){
        //填充评论
        List<SubComment> subCommentList=getSubCommentList(moment.getMomentId());
        moment.setSubCommentList(subCommentList);
        //填充点赞数
        int zanNumber=zanService.getZanNumber("牙医圈",moment.getMomentId(),null,null);
        moment.setZanNumber(zanNumber);
        //判断用户是否已经点赞
        if(userId!=null){
            boolean flag = redisService.SETS.sismember("点赞用户列表"+"牙医圈:"+moment.getMomentId(), userId);
            if(flag){
                moment.setIsZan(1);
            }
        }
        //如果是病例，培训，视频，填充图片和标题
        Map<String,String> map=new HashMap<String,String>();
        //2.视频3.病例4.培训
        if(moment.getMomentType()==GlobalVariables.VIDEO_MOMENT){
            map=momentManageDao.getMomentTitleByVedio(moment.getMomentContentId());
        }else if(moment.getMomentType()==GlobalVariables.POST_MOMENT){
            map=momentManageDao.getMomentTitleByPost(moment.getMomentContentId());
        }else if(moment.getMomentType()==GlobalVariables.FAQ_MOMENT) {
            map = momentManageDao.getMomentTitleByFaq(moment.getMomentContentId());
        }
        if(map!=null){
            String momentContentTitle=map.get("contentTitle");
            String momentPicture=map.get("contentPic");
            if(momentContentTitle!=null){
                moment.setMomentContentTitle(momentContentTitle);
            }
            if(momentPicture!=null){
                moment.setMomentPicture(momentPicture);
            }
        }
    }







}
