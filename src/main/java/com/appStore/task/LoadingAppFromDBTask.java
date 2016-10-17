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

    @Resource
    AppCacheService appCacheService;

    /**
     * 间隔10秒执行，更新缓存中的投资列表到本地。
     */
    @Scheduled(cron="0/10 * * * * ? ")
    public void loadBiddingListFromCache(){
        appCacheService.loadAppFromDB();

        System.out.println("Loading from DB!");
    }
}

