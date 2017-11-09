package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.ZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ZanServiceImpl implements ZanService{

    @Autowired
    private RedisService redisService;

    @Autowired
    private UtilsDao utilsDao;

    @Override
    public DataWrapper<Void> upvote(String token, Integer type, Integer typeId,Integer parentId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        //获取用户信息
        User user=utilsDao.getUserByToken(token);
        String userId=user.getUserId();
        //获取redis中保存对用的点赞列表
        //1.判断是否已经被点赞
        boolean flag=redisService.SETS.sismember("点赞用户列表"+type+typeId,userId);
        //判断父id是否为空
        if(parentId==null){
            parentId=0;
        }
        //2.如果已经点赞,取消点赞
        if(flag){
            redisService.SETS.srem("点赞用户列表"+type+typeId,userId);
            redisService.SORTSET.zincrby("点赞数+时间数"+type+parentId,-1,typeId+"");
        }
        //3.如果未点赞，则点赞
        redisService.SETS.sadd("点赞用户列表"+type+typeId,userId);
        redisService.SORTSET.zincrby("点赞数+时间数"+type+parentId,1,typeId+"");
        return dataWrapper;
    }

    @Override
    public long getZanNumber(Integer type,Integer typeId){
        return redisService.SORTSET.zcard("点赞数"+type+typeId);
    }


}
