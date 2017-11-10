package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.service.CommentService;
import com.yayiabc.http.mvc.service.ZanService;
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

@Service
public class MomentManageServiceImpl implements MomentManageService{
    @Autowired
    private MomentManageDao momentManageDao;
    @Autowired
    private UtilsDao utilsDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ZanService zanService;

  /*  @Autowired
    private RedisService redisService;*/

    @Override
    public DataWrapper<Void> add(Moment moment,String token) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        User user=utilsDao.getUserByToken(token);
        moment.setUserId(user.getUserId());
        try {
            if (moment.getMomentType() < 3) {
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
        //TODO delete the comments of moment
        //删除评论列表
        redisService.getJedis().del("牙医圈评论"+momentId);
        //删除对应的点赞数
        redisService.SORTSET.zrem("点赞数11"+momentId);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage) {
        DataWrapper<List<Moment>> dataWrapper =new DataWrapper<List<Moment>>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        System.out.println(page);
        int totalNumber=momentManageDao.getMomentTotalNumber();
        dataWrapper.setPage(page, totalNumber);
        List<Moment> momentList=momentManageDao.queryList(page);
        for (Moment moment:momentList
             ) {
            //填充评论
            List<Comment> commentList=commentService.queryCom("牙医圈",moment.getMomentId(),1,-1);
            moment.setCommentList(commentList);
            //天秤点赞数
            int zanNumber=(int)zanService.getZanNumber(11,moment.getMomentId());
            moment.setZanNumber(zanNumber);
            int commentNumber=0;
            if(commentList!=null){
                commentNumber=commentList.size();
            }
            moment.setCommentNumber(commentNumber);
            Map<String,String> map=null;
            if(moment.getMomentType()==3){//如果是3视频
                map=momentManageDao.getMomentTitleByVedio(moment.getMomentContentId());
                moment.setMomentContentTitle(map.get("contentTitle"));
                moment.setMomentPicture(map.get("contentPic"));
            }else if(moment.getMomentType()==4){//如果是4病例
                map=momentManageDao.getMomentTitleByPost(moment.getMomentContentId());
                moment.setMomentContentTitle(map.get("contentTitle"));
                moment.setMomentPicture(map.get("contentPic"));
            }else if(moment.getMomentType()==5) {//如果是5培训
                map = momentManageDao.getMomentTitleByTrain(moment.getMomentContentId());
                moment.setMomentContentTitle(map.get("contentTitle"));
                moment.setMomentPicture(map.get("contentPic"));
            }
        }
        dataWrapper.setData(momentList);
        return dataWrapper;
    }





}
