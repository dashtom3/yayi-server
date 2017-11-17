package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage,String token) {
        DataWrapper<List<Moment>> dataWrapper =new DataWrapper<List<Moment>>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int totalNumber=momentManageDao.getMomentTotalNumber();
        dataWrapper.setPage(page, totalNumber);
        List<Moment> momentList=momentManageDao.queryList(page);
        User user=null;
        String userId=null;
        if(token!=null){
            user=utilsDao.getUserByToken(token);
            userId=user.getUserId();
        }
        System.out.println(userId);
        for (Moment moment:momentList
             ) {
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
            if(moment.getMomentType()==2){//如果是2视频
                map=momentManageDao.getMomentTitleByVedio(moment.getMomentContentId());
            }else if(moment.getMomentType()==3){//如果是3病例
                map=momentManageDao.getMomentTitleByPost(moment.getMomentContentId());
            }else if(moment.getMomentType()==4) {//如果是4培训
                map = momentManageDao.getMomentTitleByTrain(moment.getMomentContentId());
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
        dataWrapper.setData(momentList);
        return dataWrapper;
    }


    //获取牙医圈的评论列表
    public List<SubComment> getSubCommentList(Integer parentId){
        List<String> comListStr= redisService.LISTS.lrange("牙医圈评论"+ parentId,0,-1);
        List<SubComment> subCommentList=new ArrayList<SubComment>();
        JSONObject jsonObject=null;
        SubComment subComment=null;
        for (String jsonList:comListStr
             ) {
            jsonObject=JSONObject.fromObject(jsonList);
            subComment=(SubComment)JSONObject.toBean(jsonObject,SubComment.class);
            subCommentList.add(subComment);
        }
        return subCommentList;
    }






}
