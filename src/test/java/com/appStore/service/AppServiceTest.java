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
package com.appStore.service;

import com.appStore.dao.AppDAO;
import com.appStore.service.impl.AppServiceImpl;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertEquals;

/**
 *  mock单元测试
 *
 *  @author xiachuan at 2016/10/17 18:04。
 */

public class AppServiceTest {

    public AppServiceTest() {
    }

    private AppService appService= new AppServiceImpl();

    private AppDAO appDAO = EasyMock.createNiceMock(AppDAO.class);

    /**
     * 初始化。
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        ReflectionTestUtils.setField(appService,"appDAO",appDAO);

    }

    @Test
    public void testGetAllCount() throws Exception {

        Integer count = appService.getAllCount();
        assertEquals(count,null);

        expect(appDAO);


    }
}

