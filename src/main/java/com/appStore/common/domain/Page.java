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
package com.appStore.common.domain;

import com.appStore.common.util.PageUtils;

import java.util.List;


/**
 *  数据容器。
 *
 *  @author xiachuan at 2016/10/12 17:18。
 */

public class Page<T> {
    private PageUtils page = new PageUtils();
    private List<T> list;
    private T objectT;
    private Object object;

    public T getObjectT() {
        return objectT;
    }

    public void setObjectT(T objectT) {
        this.objectT = objectT;
    }

    public PageUtils getPage() {
        return page;
    }

    public void setPage(PageUtils page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page [page=" + page + ", list=" + list + ", objectT=" + objectT
                + "]";
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}

