package com.appStore.dao;

import com.appStore.entity.App;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表实体{@link com.appStore.entity.App}的DAO接口定义。
 *
 * Created by Administrator on 2016/7/7.
 */
public interface AppDAO {

    /**
     * 插入一条App纪录。
     *
     * @param app
     * @return
     */
    Integer createApp(App app);

    /**
     * 删除一条App记录。
     *
     * @param appId
     * @return
     */
    Integer deleteApp(@Param("appid") String appId);

    /**
     * 更新App信息。
     *
     * @param app
     * @return
     */
    Integer updateApp(App app);

    /**
     * 通过appId获取APP。
     *
     * @param appId
     * @return
     */
    App getAppById(@Param("appid") String appId);

}
