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
import java.util.Set;

/**
 * 缓存适配器
 *
 * @author xiachuan at 2017/8/21 16:13。
 */

public abstract class CacheStorageAdapter<T> implements CacheStorage<T>{

    public CacheStorageAdapter() {
        super();
    }

    /**
     * {@link CacheStorage#set(String, Object, int, redis.clients.jedis.Jedis)}缺省实现。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    @Override
    public void set(String key, T value, int expiredTime, Jedis jedis) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@link CacheStorage#set(String, Object, int, redis.clients.jedis.Jedis)}缺省实现。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    @Override
    public void setAndClose(String key, T value, int expiredTime) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@link CacheStorage#set(String, Object, Date, redis.clients.jedis.Jedis)}缺省实现。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    @Override
    public void set(String key, T value, Date expiredTime, Jedis jedis) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@link CacheStorage#set(String, Object, Date, redis.clients.jedis.Jedis)}缺省实现。
     *
     * @param key 键。
     * @param value 值。
     * @param expiredTime 过期时间。
     */
    @Override
    public void setAndClose(String key, T value, Date expiredTime) {
        throw new UnsupportedOperationException();
    }

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
    public T get(String key, Jedis jedis) {
        throw new UnsupportedOperationException();
    }

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
    public T getAndClose(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
    public T get(byte[] key, Jedis jedis) {
        throw new UnsupportedOperationException();
    }

    /**
     * 会话中获取键值。
     *
     * @param key 键。
     * @return  值。
     */
    public T getAndClose(byte[] key) {
        throw new UnsupportedOperationException();
    }

    /**
     * 从缓存中移除。
     *
     * @param key 键。
     * @return {@link java.lang.Long}。
     */
    @Override
    public Long remove(String key, Jedis jedis) {
        throw new UnsupportedOperationException();
    }

    /**
     * 从缓存中移除。
     *
     * @param key 键。
     * @return {@link java.lang.Long}。
     */
    @Override
    public Long removeAndClose(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * 将字符串数据放入SET集合中。
     *
     * @param key       键。
     * @param values    数据集合。
     * @return {@link Long}。
     */
    public abstract Long addToSet(String key, String... values);

    /**
     * 将字符串数据放入SET集合中。
     *
     * @param key       键。
     * @param expiredTime 过期时间。
     * @param values    数据集合。
     * @return {@link Long}。
     */
    public abstract Long addToSet(String key, int expiredTime, String... values);
    /**
     * 将指定字符串数据移除SET集合中。
     *
     * @param key       键。
     * @param values    数据集合。
     * @return {@link Long}。
     */
    public abstract Long removeFromSet(String key, String... values);

    /**
     * 检测指定字符串数据是否在SET集合中。
     *
     * @param key       键。
     * @param value    数据集合。
     * @return {@link java.lang.Boolean}。
     */
    public abstract Boolean isMemberInSet(String key, String value);

    /**
     * 检测指定字符串数据是否在SET集合中。
     *
     * @param key       键。
     * @return {@link java.util.Set}。
     */
    public abstract Set getAllMembersInSet(String key);

    /**
     * 查询指定Key的SET集合数量。
     *
     * @param key       键。
     * @return {@link java.lang.Long}。
     */
    public abstract Long getMemberCountInSet(String key);

    /**
     * 关闭连接。
     *
     */
    @Override
    public void close(Jedis jedis) {
        throw new UnsupportedOperationException();
    }
}

