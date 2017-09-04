package com.yayiabc.common.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Created by 小月亮 on 2017/9/2.
 */
public class RedisUtil {
    private JedisPool pool = null;

    /**
     * <p>构建redis 连接池</p>
     *
     */
    public RedisUtil() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//            config.setTestOnBorrow(true);
//             pool = new JedisPool(config, "192.168.0.121", 6379, 100000);
            pool = new JedisPool(config, "47.93.48.111", 6379, 100000);
        }
    }

    /**
     * <p>通过配置对象 ip 端口 构建连接池</p>
     * @param config 配置对象
     * @param ip ip
     * @param prot 端口
     */
    public RedisUtil(JedisPoolConfig config ,String ip, int prot){
        if (pool == null) {
            pool = new JedisPool(config,ip,prot,10000);
        }
    }

    /**
     * <p>通过配置对象 ip 端口 超时时间 构建连接池</p>
     * @param config 配置对象
     * @param ip ip
     * @param prot 端口
     * @param timeout 超时时间
     */
    public RedisUtil(JedisPoolConfig config ,String ip, int prot ,int timeout){
        if (pool == null) {
            pool = new JedisPool(config,ip,prot,timeout);
        }
    }

    /**
     * <p>通过连接池对象 构建一个连接池</p>
     * @param pool 连接池对象
     */
    public RedisUtil(JedisPool pool){
        if (this.pool == null) {
            this.pool = pool;
        }
    }

    /**
     * <p>通过key获取储存在redis中的value</p>
     * <p>并释放连接</p>
     * @param key
     * @return 成功返回value 失败返回null
     */
    public Object get(String key){
        Jedis jedis = null;
        Object obj=null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            obj = SerializeUtil.unserialize(jedis.get(key.getBytes()));
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        return obj;
    }

    /**
     * <p>向redis存入key和value,并释放连接资源</p>
     * <p>如果key已经存在 则覆盖</p>
     * @param key
     * @param obj
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key,Object obj){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            return jedis.set(key.getBytes(),SerializeUtil.serialize(obj));
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return "0";
        } finally {
            returnResource(pool, jedis);
        }
    }


    /**
     * <p>删除指定的key,也可以传入一个包含key的数组</p>
     * @param key 一个key
     * @return 返回删除成功的个数
     */
    public Long del(String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        } finally {
            returnResource(pool, jedis);
        }
    }





    /**
     * <p>设置key value,如果key已经存在则返回0,nx==> not exist</p>
     * @param key
     * @param value
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public Long setnx(String key ,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            return jedis.setnx(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
            return 0L;
        } finally {
            returnResource(pool, jedis);
        }
    }

    /**
     * <p>设置key value并制定这个键值的有效期</p>
     * @param key
     * @param obj
     * @param seconds 单位:秒
     * @return 成功返回OK 失败和异常返回null
     */
    public String setex(String key,Object obj,int seconds){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            res = jedis.setex(key.getBytes(), seconds,SerializeUtil.serialize(obj));
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        return res;
    }
    /**
     * <p>返回满足pattern表达式的所有key</p>
     * <p>keys(*)</p>
     * <p>返回所有的key</p>
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        Jedis jedis = null;
        Set<String> res = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            res = jedis.keys(pattern);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        return res;
    }

    /**
     * <p>通过key判断值得类型</p>
     * @param key
     * @return
     */
    public String type(String key){
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            jedis.auth("123");
            res = jedis.type(key);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(pool, jedis);
        }
        return res;
    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param jedis
     */
    public static void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

}
