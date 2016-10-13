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

/**
 *  排序比较。
 *
 *  @author xiachuan at 2016/10/13 10:07。
 */

public class AppComparatorTop {

    public AppComparatorTop() {
    }

    /**
     * id倒序比较。
     *
     * @param o1
     * @param o2
     * @return
     */
    public int compareIdDesc(App o1,App o2){
        if(o1.getAppidAsInt() > o2.getAppidAsInt()){
            return -1;
        }else if(o1.getAppidAsInt() < o2.getAppidAsInt()){
            return 1;
        }else{// 不会有有相同的情况
            return 0;
        }
    }

    /**
     * id 顺序比较。
     *
     * @param o1
     * @param o2
     * @return
     */
    public int compareIdAsc(App o1,App o2){
        if(o1.getAppidAsInt() > o2.getAppidAsInt()){
            return 1;
        }else if(o1.getAppidAsInt() < o2.getAppidAsInt()){
            return -1;
        }else{// 不会有有相同的情况
            return 0;
        }
    }

    /**
     * 积分倒序比较。
     *
     * @param o1
     * @param o2
     * @return
     */
    public int compareScoreDesc(App o1, App o2) {
        if (o1.getScore() > o2.getScore()) {
            return -1;
        } else if (o1.getScore() < o2.getScore()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 积分正序比较。
     *
     * @param o1
     * @param o2
     * @return
     */
    public int compareScoreAsc(App o1, App o2) {
        if (o1.getScore() > o2.getScore()) {
            return 1;
        } else if (o1.getScore() < o2.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}

