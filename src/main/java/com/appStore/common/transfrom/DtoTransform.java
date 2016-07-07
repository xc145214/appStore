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
package com.appStore.common.transfrom;

import java.util.List;
import java.util.Map;

/**
 *  POJO对象与对应DTO对象互转工具接口。
 *
 *  @author xiachuan at 2016/7/7 19:37。
 */

public interface DtoTransform<POJO,DTO> {

    /**
     * POJO对象转化为DTO对象》
     *
     * @param pojo POJO对象。
     * @return DTO对象。
     */
    DTO from(POJO pojo);

    /**
     * Map对象转化为DTO对象。
     *
     * @param map {@link Map}。
     * @return DTO对象。
     */
    DTO from(Map<String, Object> map);

    /**
     * 将POJO的集合转化为DTO的集合
     *
     * @param list {@link List}。
     * @return DTO对象列表。
     */
    List<DTO> fromByList(List<POJO> list);

    /**
     * 将POJO的集合转化为DTO的集合。
     *
     * @param list {@link List}。
     * @return DTO对象列表。
     */
    List<DTO> fromByListMap(List<Map<String, Object>> list);

    /**
     * DTO对象转化为POJO对象。
     *
     * @param dto DTO对象。
     * @return POJO对象。
     */
    POJO to(DTO dto);
}

