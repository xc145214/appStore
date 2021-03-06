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
package com.appStore.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *  控制器中基础公共方法的定义，被控制器们继承
 *
 *  @author xiachuan at 2016/7/7 11:30。
 */

public abstract class BaseController {

    public BaseController() {
    }

    /**
     *  通过Http请求获取参数。
     *
     * @param request
     * @return
     */
    protected Map getParams(HttpServletRequest request){
        return   request.getParameterMap();
    }

}

