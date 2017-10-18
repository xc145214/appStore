/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package com.appStore.common.cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Redis客户端库Jedis操作方法集合。
 *
 * @author xiachuan at 2017/8/21 16:16。
 */

public final class JedisClient {

    protected static final Logger LOG = LogManager.getLogger(JedisClient.class);

    private static final int TIMEOUT = 3000;//默认超时时间

    /*主机*/
    final private String host;
    /*端口*/
    final private int port;
    /*实例名称，阿里云*/
    final private String instanceName;
    /*访问密码*/
    final private String password;
    /*Redis最大空闲连接*/
    final private int maxIdle;
    /*Redis最大活动连接*/
    final private int maxTotal;
    /*获取不到连接最大等待时间(毫秒)*/
    final private int maxWaitMillis;
    /**
     * {@link Jedis}连接池实例。
     */
    JedisPool pool;

    /**
     * 构建{@link JedisClient}。
     *
     * @param host 主机。
     * @param port 端口。
     * @param instanceName 实例名称，阿里云。
     * @param password 访问密码。
     */
    public JedisClient(String host, int port, String instanceName, String password, int maxIdle, int maxTotal) {
        this(host, port, instanceName, password, maxIdle, maxTotal, TIMEOUT);
    }

    /**
     * 构建{@link JedisClient}。
     *
     * @param host 主机。
     * @param port 端口。
     * @param instanceName 实例名称，阿里云。
     * @param password 访问密码。
     * @param maxIdle   最大空闲连接数。
     * @param maxTotal  最大活动连接数。
     * @param maxWaitMillis 超时，毫秒。
     */
    public JedisClient(String host, int port, String instanceName, String password, int maxIdle, int maxTotal, int maxWaitMillis) {
        this.host = host;
        this.port = port;
        this.instanceName = instanceName;
        this.password = password;
        this.maxIdle = maxIdle;
        this.maxTotal = maxTotal;
        this.maxWaitMillis = maxWaitMillis;

        if(StringUtils.isBlank(host)) {
            throw new CacheException("Redis主机不能为空。");
        }

        if(port <= 0) {
            throw new CacheException("Redis端口不合法。");
        }

//        if(StringUtils.isBlank(instanceName)) {
//            throw new CacheException("Redis实例名称不能为空。");
//        }

//        this.jedis = new Jedis(host, port);
//        if(!this.jedis.auth(StringUtils.isNotBlank(this.instanceName) ?String.format("%s:%s", this.instanceName, this.password) : this.password).equals("OK")) {
//            throw new CacheException("Jedis连接鉴权失败。");
//        }

        //使用连接池获取实例。
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接数, 不要超过KVStore每个实例最大的连接数
        config.setMaxIdle(maxIdle);
        //最大连接数, 不要超过KVStore每个实例最大的连接数
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        pool = new JedisPool(config, host, port, maxWaitMillis, password);
    }

    /**
     * 获取初始化后的{@link Jedis}实例。
     *
     * @return {@link Jedis}实例。
     */
    public final Jedis getJedis() {
//        LOG.info("连接池最大活动连接={},最大空闲连接={}", pool.getNumActive(), pool.getNumIdle());
        return pool.getResource();
    }

    /**
     * 获取缓存池的连接信息。
     * @return  {@link java.util.Map}。
     */
    public final Map<String, Object> getJedisPoolInfo () {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("numIdle", pool.getNumIdle());
        map.put("numActive", pool.getNumActive());
        map.put("numWaiters", pool.getNumWaiters());
        map.put("maxBorrowWaitTimeMillis", pool.getMaxBorrowWaitTimeMillis());
        map.put("meanBorrowWaitTimeMillis", pool.getMeanBorrowWaitTimeMillis());
        map.put("isClosed", pool.isClosed());

        return map;
    }

}

