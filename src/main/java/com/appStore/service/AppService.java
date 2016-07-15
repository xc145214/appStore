package com.appStore.service;

import com.appStore.entity.App;

import java.util.List;

/**
 * app service 定义的接口。
 * Created by Administrator on 2016/7/8.
 */
public interface AppService {

    /**
     * 查找所有app的数量。
     *
     * @return
     */
    Integer getAllCount();

    /**
     * 分页查询App列表。
     *
     * @param start 开始位置。
     * @param limit pageSize
     * @return
     */
    List<App> getPageList(Integer start,Integer limit);

    /**
     * 根据appid 查询App详情。
     * @param appid
     * @return
     */
    App getAppById(String appid);
}
