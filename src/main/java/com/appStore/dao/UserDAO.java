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
package com.appStore.dao;

import com.appStore.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  用户表实体{@link com.appStore.entity.User}的DAO接口定义。
 *
 *  @author xiachuan at 2016/7/7 12:32。
 */

public interface UserDAO {

    /**
     * 保存用户。
     *
     * @param user
     */
     void saveUser(User user);

    /**
     * 查看用户列表。
     *
     * @return
     */
    List<User> listUser();

    /**
     * 通过用户名查询用户。
     *
     * @param userName
     * @return
     */
    User getUserByName(
            @Param("userName")String userName
    );

    /**
     * 通过用户ID查询用户。
     *
     * @param userId
     * @return
     */
    User getUserById(
            @Param("userId")Long userId
    );
}

