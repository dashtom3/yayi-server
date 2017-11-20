package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.MessageNumber;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.MessageService;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private static final int TOTAL=10;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UtilsDao utilsDao;

    @Override
    public DataWrapper<MessageNumber> getNumber(String token) {
        DataWrapper<MessageNumber> dataWrapper=new DataWrapper<MessageNumber>();
        if(token==null){
            return dataWrapper;
        }
        User user=utilsDao.getUserByToken(token);
        if(user==null){
            return dataWrapper;
        }
        String userId=user.getUserId();
        //获取系统消息的数量
        Integer systemMessageNumber=(int)redisService.LISTS.llen("系统消息"+userId);
        //获取点赞相关的信息
        Integer zanNumber=(int)redisService.LISTS.llen("点赞消息"+userId);
        //获取评论相关的信息
        Integer commentNumber=(int)redisService.LISTS.llen("评论消息"+userId);
        MessageNumber messageNumber=new MessageNumber(systemMessageNumber,zanNumber,commentNumber);
        dataWrapper.setData(messageNumber);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Object> getDetail(String token, Integer type) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if(token==null){//未登录
            return dataWrapper;
        }
        User user=utilsDao.getUserByToken(token);
        if(user==null){
            return dataWrapper;
        }
        String userId=user.getUserId();
        List<String> messageList=new ArrayList<String>();
        messageList=messageList(type,userId);
        if(messageList.size()==0){
            return dataWrapper;
        }
        dataWrapper.setData(messageList);
        return dataWrapper;
    }

    //弹出消息,获取消息列表,这里获取消息后即会从消息列表中移除
    public List<String> messageList(Integer type,String userId){
        String typeStr="";
        if(type==1){//系统消息
            typeStr="系统消息";
        }else if(type==2){//点赞消息
            typeStr="点赞消息";
        }else if(type==3){//评论消息
            typeStr="评论消息";
        }
        List<String> messageList=new ArrayList<String>();
        String message="";
        int i=0;//显示多少条,计数
        while((int)redisService.LISTS.llen(typeStr+userId)!=0){
            message=redisService.LISTS.lpop(typeStr+userId);
            messageList.add(message);
            if(++i==TOTAL){
                break;
            }
        }
        return messageList;
    }


}
