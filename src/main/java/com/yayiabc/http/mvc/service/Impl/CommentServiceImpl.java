package com.yayiabc.http.mvc.service.Impl;


import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ScoreUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Comment;

import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.CommentService;
import com.yayiabc.http.mvc.service.RedisService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private RedisService redisService;


    @Override
    public DataWrapper<Void> addCom(String token, String type, Integer beCommentedId, Comment comment) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        //通过token来获取用户的信息
        User user = utilsDao.getUserByToken(token);
        comment.setUserId(user.getUserId());
        comment.setUserName(user.getTrueName());
        if(type.equals("视频")){//添加评论数
            redisService.SORTSET.zincrby("视频评论数",1,beCommentedId+"");
        }
        //生成自增主键的STRING类型
        long commentId = redisService.STRINGS.incrBy(type + beCommentedId+"的自增id序列" , 1);
        comment.setCommentId(commentId);
        //生成某一内容对应下的评论List的key
        String key = type + "评论" + beCommentedId;
        //生成点赞对应的用户列表
        //将对象存储进List
        JSONObject jsonObject = JSONObject.fromObject(comment);
        String json = jsonObject.toString();
        redisService.LISTS.rpush(key, json);
        //将点赞数和时间数放进一个ZSET中
        double score= ScoreUtil.getScore(commentId);
        redisService.SORTSET.zadd("点赞数+时间数"+type+beCommentedId,score,commentId+"");
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Comment>> queryCom(String type, Integer beCommentedId) {
        DataWrapper<List<Comment>> dataWrapper = new DataWrapper<List<Comment>>();
        List<Comment> commentModelList = new ArrayList<Comment>();
        String key = type + "评论" + beCommentedId;
        List<String> jsonList = redisService.LISTS.lrange(key, 0, -1);
        System.out.println(jsonList);
        for (String json : jsonList
                ) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            Comment commentModel = (Comment) JSONObject.toBean(jsonObject, Comment.class);
            commentModelList.add(commentModel);
        }
        System.out.println(commentModelList);
        Set<String> idSet=redisService.SORTSET.zrevrange("点赞数+时间数"+type+beCommentedId,0,-1);
        System.out.println(idSet);
        List<Comment> comments = new ArrayList<Comment>();
        for (String id : idSet
                ) {
            for (Comment comment : commentModelList
                    ) {
                if (String.valueOf(comment.getCommentId()).equals(id)) {
                    int zan=(int)redisService.SORTSET.zcard("点赞数+时间数"+type+beCommentedId);
                    comment.setZan(zan);
                    comments.add(comment);
                }
            }
        }
        System.out.println(comments);
        dataWrapper.setData(comments);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> addSubCom(String token, Long preCommentId, SubComment subComment,String type) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        //通过token来获取用户的信息
        User user = utilsDao.getUserByToken(token);
        subComment.setUserId(user.getUserId());
        subComment.setUserName(user.getTrueName());
        //生成自增主键的STRING类型
        Long commentId = redisService.STRINGS.incrBy("sub" + "生成id自增主键", 1);
        subComment.setCommentId(commentId);
        //生成某一内容对应下的评论List的key
        String key = type+"评论"+ preCommentId;
        //将对象存储进List
        JSONObject jsonObject = JSONObject.fromObject(subComment);
        String json = jsonObject.toString();
        redisService.LISTS.rpush(key, json);
        //将点赞数放进一个ZSET中
        double score=ScoreUtil.getScore(commentId);
        redisService.SORTSET.zadd("点赞数+时间数"+type+preCommentId,score,commentId+"");
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<SubComment>> querySubCom(Long preCommentId) {
        DataWrapper<List<SubComment>> dataWrapper = new DataWrapper<List<SubComment>>();
        String key = "plsub" + preCommentId;
        List<String> jsonList = redisService.LISTS.lrange(key, 0, -1);
        List<SubComment> subCommentList = new ArrayList<SubComment>();
        for (String json : jsonList
                ) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            SubComment subComment = (SubComment) JSONObject.toBean(jsonObject, SubComment.class);
            subCommentList.add(subComment);
        }
        dataWrapper.setData(subCommentList);
        return dataWrapper;
    }







    }

