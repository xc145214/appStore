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
package com.appStore.task;

import com.appStore.service.AppCacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *  定时从数据库中加载数据到本地。
 *
 *  @author xiachuan at 2016/10/13 17:08。
 */
@Component
public class LoadingAppFromDBTask {
    protected static final Logger LOG = LogManager.getLogger(LoadingAppFromDBTask.class);
    @Resource
    AppCacheService appCacheService;

    /**
     * 间隔10秒执行，更新缓存中的投资列表到本地。
     */
    @Scheduled(cron="0/10 * * * * ? ")
    public void loadBiddingListFromCache(){

        try {
            appCacheService.reloadCacheTask();
        }catch (Exception e){
            LOG.error("间隔10秒执行，更形列表缓存出错！e:{}",e.getStackTrace());
        }
    }


    @Scheduled(cron = "0 */6 * * * ?")
    public void autoRefreshCacheList(){
        try {
            appCacheService.refreshCacheList();
        }catch (Exception e){
            LOG.error("间隔10秒执行，更形列表缓存出错！e:{}",e.getStackTrace());
        }
    }
}

