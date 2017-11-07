package com.yayiabc.http.mvc.service.Impl;


import com.yayiabc.common.utils.DataWrapper;
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
        //生成自增主键的STRING类型
        Long commentId = redisService.STRINGS.incrBy(type + beCommentedId + "id", 1);
        comment.setCommentId(commentId);
        //生成某一内容对应下的评论List的key
        String key = type + "pl" + beCommentedId;
        //将对象存储进List
        JSONObject jsonObject = JSONObject.fromObject(comment);
        String json = jsonObject.toString();
        redisService.LISTS.rpush(key, json);
        //将点赞数放进一个ZSET中
        redisService.SORTSET.zadd(type + beCommentedId + "zan", comment.getZan(), String.valueOf(commentId));
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Comment>> queryCom(String type, Integer beCommentedId) {
        DataWrapper<List<Comment>> dataWrapper = new DataWrapper<List<Comment>>();
        List<Comment> commentModelList = new ArrayList<Comment>();
        String key = type + "pl" + beCommentedId;
        List<String> jsonList = redisService.LISTS.lrange(key, 0, -1);
        System.out.println(jsonList);
        for (String json : jsonList
                ) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            Comment commentModel = (Comment) JSONObject.toBean(jsonObject, Comment.class);
            commentModelList.add(commentModel);
        }
        System.out.println(commentModelList);
        Set<String> idSet = redisService.SORTSET.zrevrange(type + beCommentedId + "zan", 0, -1);
        System.out.println(idSet);
        List<Comment> comments = new ArrayList<Comment>();
        for (String id : idSet
                ) {
            for (Comment comment : commentModelList
                    ) {
                if (String.valueOf(comment.getCommentId()).equals(id)) {
                    comments.add(comment);
                }
            }
        }
        System.out.println(comments);
        dataWrapper.setData(comments);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> addSubCom(String token, Long preCommentId, SubComment subComment) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        //通过token来获取用户的信息
        User user = utilsDao.getUserByToken(token);
        subComment.setUserId(user.getUserId());
        subComment.setUserName(user.getTrueName());
        //生成自增主键的STRING类型
        Long commentId = redisService.STRINGS.incrBy("sub" + "id", 1);
        //生成某一内容对应下的评论List的key
        String key = "plsub" + preCommentId;
        //将对象存储进List
        JSONObject jsonObject = JSONObject.fromObject(subComment);
        String json = jsonObject.toString();
        redisService.LISTS.rpush(key, json);
        //将点赞数放进一个ZSET中
        redisService.SORTSET.zadd("pl" + preCommentId + "zan", subComment.getZan(), String.valueOf(commentId));
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

    @Override
    public DataWrapper<Void> zan(String type, Integer beCommentedId, Integer category, Long commentId) {
        DataWrapper<Void> dataWrapper = new DataWrapper<>();
        if (category == 1) {//一级分类
            //先取出list
            String key = type + "pl" + beCommentedId;
            List<String> jsonList = redisService.LISTS.lrange(key, 0, -1);
            Comment comment = null;
            for (int i = 0; i < jsonList.size(); i++) {
                JSONObject jsonObject = JSONObject.fromObject(jsonList.get(i));
                comment = (Comment) JSONObject.toBean(jsonObject, Comment.class);
                if (commentId == comment.getCommentId()) {
                    comment.setZan(comment.getZan() + 1);
                    redisService.LISTS.lset(key, i, JSONObject.fromObject(comment).toString());
                    return dataWrapper;
                }
            }
        }else if (category == 2) {//二级分类
                //先取出list
                String keySub = "plsub" + beCommentedId;
                List<String> jsons = redisService.LISTS.lrange(keySub, 0, -1);
                SubComment subComment = null;
                for (int i = 0; i < jsons.size(); i++) {
                    JSONObject object = JSONObject.fromObject(jsons.get(i));
                    subComment = (SubComment) JSONObject.toBean(object, SubComment.class);
                    if (subComment.getCommentId() == commentId) {
                        subComment.setZan(subComment.getZan() + 1);
                        redisService.LISTS.lset(keySub, i, JSONObject.fromObject(subComment).toString());
                        return dataWrapper;
                    }
                }
            }
            return dataWrapper;
        }
    }

