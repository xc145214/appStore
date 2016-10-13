package com.appStore.dao;

import com.appStore.entity.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表实体{@link com.appStore.entity.App}的DAO接口定义。
 *
 * Created by Administrator on016/7/7.
 */
public interface AppDAO {


    /**
     * 查询所有App数量。
     *return
     */
    Integer readAllCount();

    /**
     * 分页获取App列表。
     *
     * @param start 开始位置。
     * @param limit 分页数。
     * @return
     */
    List<App> readPageList(
            @Param("start") Integer start,
            @Param("limit") Integer limit);

    List<App> readAllList();

    int deleteByPrimaryKey(String appid);


    int insert(App record);


    int insertSelective(App record);


    App selectByPrimaryKey(String appid);


    int updateByPrimaryKeySelective(App record);


    int updateByPrimaryKey(App record);

}
