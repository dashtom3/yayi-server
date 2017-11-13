//package com.yayiabc.http.mvc.expand;
//
//import com.yayiabc.common.utils.RedisClient;
//
////redis-cli -h 127.0.0.1 -p 6379 shutdown
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//public class Subscriber {
//
//    public static void main(String[] args) {
//       /* JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.80.130");
//        Jedis jedis = pool.getResource();*/
//    	RedisClient redis=RedisClient.getInstance();
//    	
//       /* jedis.auth("123");*/
//    	redis.jedis.psubscribe(new KeyExpiredListener(), "__key*__:*");
//    }
//}
