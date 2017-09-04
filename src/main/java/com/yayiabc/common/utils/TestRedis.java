package com.yayiabc.common.utils;

/**
 * Created by 小月亮 on 2017/9/2.
 */
public class TestRedis {
    public static void main(String[] args) {
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.set("name","zhangsan");
        System.out.println(redisUtil.get("name"));
    }
}
