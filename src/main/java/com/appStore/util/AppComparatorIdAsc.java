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
package com.appStore.util;

import com.appStore.entity.App;

import java.util.Comparator;

/**
 *  id升序。默认排序
 *
 *  @author xiachuan at 2016/10/13 13:33。
 */

public class AppComparatorIdAsc extends AppComparatorTop implements Comparator<App> {

    /**
     * 默认按Id升序。
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(App o1, App o2) {
        return super.compareIdAsc(o1,o2);
    }
}

