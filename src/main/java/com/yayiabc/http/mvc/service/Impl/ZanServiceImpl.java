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
    public DataWrapper<Void> upvote(String token, Integer type, Integer typeId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        //是否点赞的标识
        boolean flag=false;
        //获取用户信息
        User user=utilsDao.getUserByToken(token);
        String userId=user.getUserId();
        //获取redis中保存对用的点赞列表
//        List<String> userIdList=redisService.LISTS.lrange("点赞用户列表"+type+typeId,0,-1);
        Set<String> userIdSet=redisService.SETS.sdiff("点赞用户列表"+type+typeId);
        //1.判断是否已经被点赞
        if(userIdSet!=null){
            if(userIdSet.contains(userId)){
                flag=true;
            }
        }
        //2.如果已经点赞,取消点赞
        if(flag){
            redisService.SETS.srem("点赞用户列表"+type+typeId,userId);
        }
        //3.如果未点赞，则点赞
        redisService.SETS.sadd("点赞用户列表"+type+typeId,userId);
        return dataWrapper;
    }

    @Override
    public long getZanNumber(Integer type,Integer typeId){
        return redisService.SETS.scard("点赞用户列表"+type+typeId);
    }


}
