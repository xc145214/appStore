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

import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * 缓存存储接口
 *
 * @author xiachuan at 2017/8/21 16:05。
 */

public interface CacheStorage<T> {

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    void set(String key, T value, int expiredTime, Jedis jedis);

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     */
    void set(String key, T value, Jedis jedis);

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    void setAndClose(String key, T value, int expiredTime);

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     */
    void setAndClose(String key, T value);

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    void set(String key, T value, Date expiredTime, Jedis jedis);

    /**
     * 设置键值，有过期时间。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    void setAndClose(String key, T value, Date expiredTime);

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
     T get(String key, Jedis jedis);

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
     T getAndClose(String key);

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
     T get(byte[] key, Jedis jedis);

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
     T getAndClose(byte[] key);

    /**
     * 从缓存中移除。
     *
     * @param key 键。
     * @return {@link java.lang.Long}。
     */
     Long remove(String key, Jedis jedis);

    /**
     * 从缓存中移除。
     *
     * @param key 键。
     * @return {@link java.lang.Long}。
     */
     Long removeAndClose(String key);

    /**
     * 关闭连接。
     *
     */
     void close(Jedis jedis);
}

