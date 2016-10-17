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

import com.appStore.common.domain.Page;
import com.appStore.dao.AppDAO;
import com.appStore.entity.App;
import com.appStore.service.AppCacheService;
import com.appStore.util.AppComparatorIdAsc;
import com.appStore.util.AppComparatorScoreAsc;
import com.appStore.util.AppComparatorScoreDesc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  缓存服务实现。
 *
 *  @author xiachuan at 2016/10/13 9:28。
 */
@Service("appCacheService")
public class AppCacheServiceImpl implements AppCacheService {
    Logger LOG = LogManager.getLogger(AppCacheServiceImpl.class);

    @Resource
    AppDAO appDAO;

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

//        if(refresh) {
//            synchronized (this){
//                if(refresh) {
//                    appUserMap.clear();
//                    appUserMap.addAll(appCacheMap);
//                    refresh = false;
//                }
//            }
//        }

//        appUserMap = appDAO.readAllList();

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

