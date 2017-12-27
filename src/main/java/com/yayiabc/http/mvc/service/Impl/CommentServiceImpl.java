package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.*;
import com.yayiabc.http.mvc.dao.*;
import com.yayiabc.http.mvc.pojo.jpa.Comment;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.CommentService;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.ZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 评论中心，同时推送，推送策略key为评论消息推送+userId
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final String MESSAGE_ONE="评论了你的牙医圈动态";

    private static final String MESSAGE_TWO="回复了你的评论";

    private static final String MESSAGE_THREE="评论了你发表的病例";

    private static final String PREFIX="评论消息推送";

    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ZanService zanService;


    @Autowired
    private MomentManageDao momentManageDao;

    @Autowired
    private CommentDao commentDao;


    @Override
    public DataWrapper<Object> addCom(String token, String type, Integer beCommentedId, Comment comment,Integer parentId) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        //将类型转换成数字，存储进数据库
        int num= TypeToNumUtil.typeToNum(type);
        System.out.println("num"+num);
        //如果为0则标识传入错误
        if(num==0){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            return dataWrapper;
        }
        User user = utilsDao.getUserByToken(token);
        //初始化点赞计数列表
//        redisService.SORTSET.zadd("点赞计数列表"+type+":"+beCommentedId+str,0,commentId+"");
        //牙医圈
        if(num==4){
            return addMomentCom(beCommentedId,comment,parentId,dataWrapper,user);
        }else if(num==1||num==2){
            String suffix="";
            User beCommentedUser=null;
            if(parentId==null){
                if(num==1){
                    commentDao.addCommentNumberToVideo(beCommentedId);
                }
                if(num==2){
                    commentDao.addCommentNumberToCottom(beCommentedId);
                    suffix=MESSAGE_THREE;
                    beCommentedUser=commentDao.getUserByPostId(beCommentedId);
                }
                commentDao.addComment(user.getUserId(),comment,beCommentedId,num);
            }else{//二级评论
                //自己不能评论自己
                beCommentedUser=commentDao.getUserByTopCommentId(parentId);
                if(user.getUserId().equals(beCommentedUser.getUserId())){
                    dataWrapper.setErrorCode(ErrorCodeEnum.Error);
                    return dataWrapper;
                }else{
                    if(num==2){
                        suffix=MESSAGE_TWO;
                    }
                    commentDao.addSubComment(user.getUserId(),comment,parentId);
                    commentDao.updateCommentReplyNum(parentId);
                }
            }
            //推送消息
            if(num==2&&(beCommentedUser!=null)&&!(user.getUserId().equals(beCommentedUser.getUserId()))){
                redisService.LISTS.rpush(PREFIX+beCommentedUser.getUserId(),beCommentedUser.getTrueName()+suffix+",病例:" + beCommentedId);
            }
        }
        return dataWrapper;
    }


    //牙医圈添加评论
    private DataWrapper<Object> addMomentCom(Integer beCommentedId,Comment coment,Integer parentId,DataWrapper<Object> dataWrapper,User currentUser){
        //初始化被评论的内容的发布者
        User user;
        String suffix="";
        //为了给用户发送消息
        if(parentId==null){
            //1.获得发布该条牙医圈的人的userId和username
            user=momentManageDao.getUserByMomentId(beCommentedId);
            suffix=MESSAGE_ONE;
        }else{//回复了评论
            user=momentManageDao.getUserBySubMomentId(parentId);
            suffix=MESSAGE_TWO;
        }
        //自己不能评论自己
        boolean flag=currentUser.getUserId().equals(user.getUserId());
        if(flag&&(parentId!=null)){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            return dataWrapper;
        }
        //这一步是评论的人是自己时，不给消息提醒,一级评论
        if(user!=null&&!flag){
            redisService.LISTS.rpush(PREFIX+user.getUserId(),currentUser.getTrueName()+suffix+",牙医圈:"+beCommentedId);
        }
       int i= momentManageDao.addComment(beCommentedId,coment,parentId,currentUser.getUserId());
       if(i==0){
           dataWrapper.setErrorCode(ErrorCodeEnum.Error);
       }
       return dataWrapper;
    }



    @Override
    public DataWrapper<List<Comment>> queryCom(String type, Integer beCommentedId,Integer currentPage,Integer numberPerPage,String token,Integer order) {
        DataWrapper<List<Comment>> dataWrapper=new DataWrapper<List<Comment>>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int numberType=TypeToNumUtil.typeToNum(type);
        int totalNumber=commentDao.getCommentTotalNum(numberType,beCommentedId);
        dataWrapper.setPage(page,totalNumber);
        String userId=null;
        if(token!=null){
            userId=utilsDao.getUserID(token);
        }
        List<Comment> commentList=commentDao.getCommentList(numberType,beCommentedId,page,order);
        if(userId!=null){
            for (Comment comment:commentList
                    ) {
                //填充是否点赞
                if(redisService.SETS.sismember("点赞用户列表"+type+":"+beCommentedId+":"+comment.getCommentId(),userId)){
                    comment.setIsZan(1);
                }
            }
        }
        dataWrapper.setData(commentList);
        return dataWrapper;
    }




    @Override
    public DataWrapper<Void> delete(String type, String beCommentedId, Integer parentId, Integer presentId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        int numType=TypeToNumUtil.typeToNum(type);
        if("牙医圈".equals(type)){
            return deleteYayiCom(parentId,dataWrapper);
        }
        //子评论
        if(presentId!=null){
            //一级评论的回复数-1
            commentDao.delCommentReplyNum(parentId);
            commentDao.deleteSubComment(presentId);
        }else{
            if(numType==1){
                //视频的一级评论数-1
                commentDao.delCommentNumberToVideo(beCommentedId);
            }else if(numType==2){
                //病例的一级评论数-1
                commentDao.delCommentNumberToCottom(beCommentedId);
            }
            commentDao.deleteComment(parentId);
            commentDao.deleteWithSubComment(parentId);
        }

        return dataWrapper;
    }

    /**
     * 发送消息
     * @param userId
     * @param beCommentedUserId
     * @param key
     * @param message
     */
    @Override
    public void sendMessage(String userId, String beCommentedUserId, String key, String message) {
        if(!userId.equals(beCommentedUserId)){
            redisService.LISTS.rpush(key,message);
        }
    }

    private DataWrapper<Void> deleteYayiCom(Integer parentId,DataWrapper<Void> dataWrapper) {
        commentDao.deleteYayiCom(parentId);
        return dataWrapper;
    }










}

