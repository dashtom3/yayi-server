package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.service.ReadNumberServer;
import com.yayiabc.http.mvc.service.RedisService;

import redis.clients.jedis.ShardedJedis;

@Service
public class ReadNumberImpl implements ReadNumberServer{
	@Autowired
    RedisService redisService;
	public DataWrapper<CottomsPost> readNumber(String token,Integer postId) {
		DataWrapper<CottomsPost> dataWrapper=new DataWrapper<CottomsPost>();
		redisService.STRINGS.incrBy(postId+"",1);
		return dataWrapper;
	}
}
