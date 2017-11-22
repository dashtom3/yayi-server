package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.MessageEntry;
import com.yayiabc.http.mvc.pojo.jpa.MessageNumber;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.MessageService;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
        //获取评论相关的信息
        System.out.println("userId"+userId);
        Integer commentNumber=(int)redisService.LISTS.llen("评论消息推送"+userId);
        MessageNumber messageNumber=new MessageNumber(commentNumber);
        dataWrapper.setData(messageNumber);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Object> getDetail(String token, Integer type,Integer numberPerPage) {
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if(token==null){//未登录
            return dataWrapper;
        }
        User user=utilsDao.getUserByToken(token);
        if(user==null){
            return dataWrapper;
        }
        String userId=user.getUserId();
        List<MessageEntry> messageList=new ArrayList<MessageEntry>();
        messageList=messageList(type,userId,numberPerPage);
        if(messageList.size()==0){
            return dataWrapper;
        }
        dataWrapper.setData(messageList);
        return dataWrapper;
    }

    //弹出消息,获取消息列表,这里获取消息后即会从消息列表中移除
    public List<MessageEntry> messageList(Integer type,String userId,Integer numberPerPage){
        String typeStr="";
        if(type==1){//评论消息
            typeStr="评论消息推送";
        }
        List<MessageEntry> messageList=new ArrayList<MessageEntry>();
        System.out.println("key为"+typeStr+userId);
        int totalNumber=(int)redisService.LISTS.llen(typeStr+userId);
        numberPerPage=totalNumber>numberPerPage?numberPerPage:totalNumber;
        Jedis jedis=RedisService.getInstance().getJedis();
        for(int i=0;i<numberPerPage;i++){
            System.out.println(redisService.LISTS);
//            String messageEntryStr=redisService.LISTS.lpop(typeStr+userId);
            String messageEntryStr=jedis.rpop(typeStr+userId);
            System.out.println("messageEntryStr"+messageEntryStr);
            MessageEntry messageEntry=new MessageEntry();
            String message=messageEntryStr.substring(0,messageEntryStr.indexOf(","));
            String detailMessage=messageEntryStr.substring(messageEntryStr.indexOf(",")+1);
            String tStr=detailMessage.substring(0,detailMessage.indexOf(":"));
            String idStr=detailMessage.substring(detailMessage.indexOf(":")+1);
            messageEntry.setMessage(message);
            messageEntry.setType(tStr);
            messageEntry.setTypeId(idStr);
            messageList.add(messageEntry);
        }

        return messageList;
    }


}
