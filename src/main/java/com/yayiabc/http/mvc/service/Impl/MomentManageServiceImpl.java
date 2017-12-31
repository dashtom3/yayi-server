package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yayiabc.common.utils.GlobalVariables;
import com.yayiabc.http.mvc.dao.ZanDao;
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
    private ZanDao zanDao;


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
        //删除评论
        momentManageDao.deleteMomentComment(momentId);
        //删除点赞
        momentManageDao.deleteMomentZan(momentId,"牙医圈");
        return dataWrapper;
    }


    @Override
    public DataWrapper<Moment> detail(Integer momentId, String token) {
        DataWrapper<Moment> dataWrapper=new DataWrapper<Moment>();
        Moment moment=momentManageDao.getMomentByMomentId(momentId);
        User user=null;
        String userId=null;
        if(token!=null){
            userId=utilsDao.getUserID(token);
        }
        fillCommentAndUpvoteMessage(moment,userId);
        dataWrapper.setData(moment);
        return dataWrapper;
    }


    /**
     * 查询列表 1表示朋友圈的动态列表，2.表示我的个人动态
     *
     * @param currentPage           当前第几页(默认为1)
     * @param numberPerPage         每页显示多少条(默认为10)
     * @param token                 用户的身份标识
     * @param type                  1.牙医圈动态列表 2.我发表的动态
     * @return
     */
    @Override
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
        int totalNumber=momentManageDao.getMomentTotalNumber(userId,type);
        List<Moment> momentList=momentManageDao.queryList(page,userId,type);
        dataWrapper.setData(momentList);
        dataWrapper.setPage(page,totalNumber);
        return dataWrapper;
    }

    //填充单条牙医圈动态的评论信息和点赞信息
    public void fillCommentAndUpvoteMessage(Moment moment,String userId){
        //填充评论
        List<SubComment> subCommentList=momentManageDao.getMomentCommentList(moment.getMomentId());
        moment.setSubCommentList(subCommentList);
        Map<String,String> map=new HashMap<String,String>();
        //2.视频3.病例
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
