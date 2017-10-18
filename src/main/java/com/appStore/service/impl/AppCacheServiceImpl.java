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
package com.appStore.service.impl;

import com.alibaba.fastjson.JSON;
import com.appStore.common.cache.CacheSessionStorage;
import com.appStore.common.domain.Page;
import com.appStore.common.thread.CountDownLatchExecutor;
import com.appStore.dao.AppDAO;
import com.appStore.entity.App;
import com.appStore.exceutor.CallBack;
import com.appStore.exceutor.ThreadExecutor;
import com.appStore.service.AppCacheService;
import com.appStore.util.AppComparatorIdAsc;
import com.appStore.util.AppComparatorScoreAsc;
import com.appStore.util.AppComparatorScoreDesc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *  缓存服务实现。
 *
 *  @author xiachuan at 2016/10/13 9:28。
 */
@Service("appCacheService")
public class AppCacheServiceImpl implements AppCacheService {
    Logger LOG = LogManager.getLogger(AppCacheServiceImpl.class);

    String APP_CACHE_LIST = "_APP_CACHE_LIST";

    String APP_CHACHE_PREFIX = "_APP_CACHE_";

    int expireTime = 86400;

    @Resource
    AppDAO appDAO;
    @Resource
    private CacheSessionStorage cacheSessionStorage;
    @Resource
    private ThreadExecutor executor;

    public static Comparator appComparatorIdAsc = new AppComparatorIdAsc();//id默认排序。

    public static Comparator appComparatorScoreAsc = new AppComparatorScoreAsc(); // 积分升序。
    public static Comparator appComparatorScoreDesc = new AppComparatorScoreDesc();// 积分降序。

    /**
     * APP 列表本地缓存。
     */
    public static List<App> appCacheMap = new ArrayList<>();
    public static List<App> appUserMap = new ArrayList<>();
    public static boolean refresh = false;


    @Override
    public void loadAppFromDB() {
        appUserMap = appDAO.readAllList();
    }

    @Override
    public Page<App> loadAppFromCache(int oc, int ou, int pageSize, int currentPage) {

        Page<App> page = new Page<>();

        if(refresh) {
            synchronized (this){
                if(refresh) {
                    appUserMap.clear();
                    appUserMap.addAll(appCacheMap);
                    refresh = false;
                }
            }
        }

        appUserMap = appDAO.readAllList();

        List<App> cacheList = sortData(oc, ou, appUserMap);
        //设置当前页及页大小
        page.getPage().setCurrentPage(currentPage);
        page.getPage().setPageSize(pageSize);
        page.getPage().setTotalPage(cacheList.size() % pageSize == 0 ? cacheList.size() / pageSize : cacheList.size() / pageSize + 1);
        //分页
        if (cacheList.size() < pageSize * (currentPage - 1)) {
            cacheList = cacheList.subList(0, cacheList.size() > pageSize ? pageSize : cacheList.size());
        } else {
            cacheList = cacheList.subList(pageSize * (currentPage - 1), cacheList.size() > pageSize * currentPage ? pageSize * currentPage : cacheList.size());
        }
        page.setList(cacheList);

        return page;
    }

    /**
     * 从KV或DB中加载数据刷新到本地MAP缓存。
     */
    @Override
    public void reloadCacheTask() {
        Set<String> appIds = cacheSessionStorage.getAllMembersInSet(APP_CACHE_LIST);
        /**
         * 如果缓存不为空。
         */
        if(!CollectionUtils.isEmpty(appIds)){

            List<CallBack> distributedCallBacks =new ArrayList<>(appIds.size());
            final List<App> appList = new CopyOnWriteArrayList<>();
            final List<String> dataList = new ArrayList<>();
            for(String id:appIds){
                dataList.add(id);
            }

            int length = appIds.size();
            final int threadNum = 10;
            final int pageSze = length % threadNum == 0 ? length/threadNum :(length/threadNum) +1;

            for (int i =0;i<threadNum;i++){

                final int start =i*pageSze;
                final int end = (i+1) * pageSze>length?length:(i+1)*pageSze;
                distributedCallBacks.add (new CallBack() {
                    @Override
                    public void call() {
                        try {
                            for (int i = start; i < end; i++) {
                                App app = _loadSingleAppFromCacheOrDB(dataList.get(i));
                                if (app != null) {

                                    appList.add(app);
                                } else {
                                    //删除缓存记录。
                                    _removeSingleCache(dataList.get(i));
                                }
                            }
                        } catch (Exception e) {
                            LOG.error(" load app from cache error：{}", e.getStackTrace());
                        }
                    }
                });
            }


            executor.excute(distributedCallBacks);

            appCacheMap = appList;
            refresh = true;

        }else{
            // 如果缓存为空
            appCacheMap =_loadAllFromDB();
            refresh =true;
        }

    }

    @Override
    public void refreshCacheList() {
        List<App> apps = appDAO.readAllList();
        Set<String> idSet = new HashSet<>();
        if(apps!=null && apps.size()>0){
            for(App app:apps){
                idSet.add(app.getAppid());
            }
        }
        Set<String> idsFromCache = cacheSessionStorage.getAllMembersInSet(APP_CACHE_LIST);
        //新数据刷入缓存。
        for(String appId :idSet){
            if(!idsFromCache.contains(appId)){
                cacheSessionStorage.addToSet(APP_CACHE_LIST,appId);
            }
        }
        //老数据删除。
        for(String appId:idsFromCache){
            if(!idSet.contains(appId)){
                cacheSessionStorage.removeFromSet(APP_CACHE_LIST,appId);
            }
        }



    }

    private List<App> _loadAllFromDB(){

        List<App> apps = appDAO.readAllList();

        if(!CollectionUtils.isEmpty(apps)){
            for(App app:apps){
                //更新缓存集合
                cacheSessionStorage.addToSet(APP_CACHE_LIST,app.getAppid());
                //放入单个APP缓存
                cacheSessionStorage.setAndClose(APP_CHACHE_PREFIX+app.getAppid(),JSON.toJSONString(app),expireTime);

            }
            return apps;
        }else{
            return null;
        }
    }

    private void _removeSingleCache(String appid){
        cacheSessionStorage.removeAndClose(APP_CHACHE_PREFIX+appid);
        cacheSessionStorage.removeFromSet(APP_CACHE_LIST,appid);
    }

    private App _loadSingleAppFromCacheOrDB(String appId) {
        String jsonObject = cacheSessionStorage.getAndClose(APP_CHACHE_PREFIX+appId);
        //缓存存在
        if (!StringUtils.isEmpty(jsonObject)) {
            return JSON.parseObject(jsonObject, App.class);
        }
        else{
            //缓存不存在从DB中加载
            App app = appDAO.selectByPrimaryKey(appId);
            if(app!= null){
                cacheSessionStorage.setAndClose(APP_CHACHE_PREFIX+appId,JSON.toJSON(app),expireTime);
                return app;
            }
            return null;
        }
    }

    /**
     * 排序。
     *
     * @param oc
     * @param ou
     * @param list
     * @return
     */
    private List<App> sortData(int oc,int ou,List<App> list){

        List<App> result = new ArrayList<>();

        Comparator comparator = null;

        switch (oc){
            case 1://积分。
                if(ou !=1){
                    comparator = appComparatorScoreAsc;
                }else{
                    comparator= appComparatorScoreDesc;
                }
                break;
            //可以扩展其他条件
            default:
                comparator = appComparatorIdAsc;
                break;
        }

        if(null !=list && list.size() >0){

            for(App app: list){
                if(app == null){
                    continue;
                }
                //扩展其他晒选条件

                result.add(app);
            }
        }

        Collections.sort(result,comparator);

        return result;
    }
}

