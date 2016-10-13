package com.appStore.controller;

import com.appStore.common.domain.Page;
import com.appStore.entity.App;
import com.appStore.service.AppCacheService;
import com.appStore.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
@RequestMapping(value = "app")
@Controller
public class AppController extends BaseController {

    public AppController() {
    }

    @Resource
    AppService appService;

    @Resource
    AppCacheService appCacheService;

    /**
     * app 分页列表。
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "list", produces = "application/json")
    @ResponseBody
    public Map<String, Object> list(
            Integer start,
            Integer limit,
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        int total = 0;
        List<App> list = new ArrayList<App>();

        try {
            total = appService.getAllCount();
            list = appService.getPageList(start, limit);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("params",getParams(request));
        }
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    /**
     * app 详情。
     * @param appid
     * @return
     */
    @RequestMapping(value = "getAppInfo",produces = "application/json")
    @ResponseBody
    public App getAppInfo(String appid){
        return appService.getAppById(appid);
    }

    /**
     *  从缓存中获取App信息。
     * @param pageSze
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "listFormCache", produces = "application/json")
    @ResponseBody
    public Page<App> listFormCache(
            Integer pageSze,
            Integer currentPage){
        int size = pageSze == null?10:pageSze;
        int cutPage = currentPage == null?1:currentPage;
        Page<App> appPage = null;
        try {
            appPage = appCacheService.loadAppFromCache(1,1,size,cutPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appPage == null){
            appPage = new Page<>();
        }
        return appPage;
    }


}
