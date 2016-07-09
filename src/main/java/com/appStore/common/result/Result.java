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
package com.appStore.common.result;


/**
 *  接口返回对象中，包括状态码和状态信息。
 *
 *  @author xiachuan at 2016/7/9 13:57。
 */

public abstract class Result {

    public static GLOBAL  GLOBAL = new GLOBAL();


    public static class GLOBAL{
        public ResultCode FAIL = ResultCode.result(0, "操作失败，请重试操作或联系客服人员。");
        public ResultCode SUCCESS = ResultCode.result(1, "操作成功。");
        public ResultCode NO_DATA = ResultCode.result(400, "没有找到数据。");


        public ResultCode MISSING_PARAMS = ResultCode.result(-1, "您输入的数据不完整。");
        public ResultCode WRONG_PARAMS = ResultCode.result(-2, "您输入的数据有误。");
        public ResultCode DB_ERRORS = ResultCode.result(-4, "操作失败，请重试。");
        public ResultCode TOO_MANY_RESULTS = ResultCode.result(-5, "返回结果超过一行记录。");
        public ResultCode ERROR_FORMAT = ResultCode.result(-6, "您输入的数据格式有误。");
        public ResultCode ERROR_JDBC = ResultCode.result(-8, "数据库级别错误。");
        public ResultCode INVALID_DATA = ResultCode.result(-10, "数据已失效或已伪删除。");
        public ResultCode REPEAT_DATA = ResultCode.result(-14, "数据已存在。");
    }

}

