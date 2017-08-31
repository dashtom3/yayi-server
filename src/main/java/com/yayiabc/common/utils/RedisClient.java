package com.yayiabc.common.utils;

import redis.clients.jedis.Jedis;

/**
 * Created by 小月亮 on 2017/8/27.
 */
public class RedisClient {
    private static final String ip= "127.0.0.1";

    private static final int port=6379;

    protected static RedisClient redis = new RedisClient ();

    public final static Jedis jedis = new Jedis( ip, port);
  
    static {
//    	jedis.auth("123");
    }

    protected RedisClient() {
    	
    }

    public static RedisClient getInstance()

    {
        return redis;
    }



    /**set Object*/

    public String set(Object object,String key)

    {

        return jedis.set(key.getBytes(), SerializeUtil.serialize(object));

    }



    /**get Object*/

    public Object get(String key)

    {

        byte[] value = jedis.get(key.getBytes());

        return SerializeUtil. unserialize(value);

    }



    /**delete a key**/

    public boolean del(String key)

    {

        return jedis.del(key.getBytes())>0;

    }

}
