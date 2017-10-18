/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.appStore.service;

import com.appStore.common.domain.Page;
import com.appStore.entity.App;

/**
 *  App 缓存服务。
 *
 *  @author xiachuan at 2016/10/12 17:11。
 */

public interface AppCacheService {

    /**
     * APP列表排序。
     *
     * @param oc    排序字段。
     * @param ou    顺序1，倒序，0
     * @param pageSize  分页大小。
     * @param currentPage   当前页。
     * @return
     */
    Page<App> loadAppFromCache(int oc,int ou,int pageSize,int currentPage);

    /**
     * 从DB中获取数据。
     */
    void loadAppFromDB();

    /**
     * 定时任务，从KV或DB加载数据刷新给本地MAP缓存。
     */
    void reloadCacheTask();

    /**
     * 从缓存加载到本地内存。
     */
    void refreshCacheList();
}

