/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package com.appStore.exceutor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 线程池管理。
 *
 * @author xiachuan at 2017/8/24 17:14。
 */
@Service
public class ThreadExecutor {
    static final Logger LOG = LogManager.getLogger(ThreadExecutor.class);

    public ThreadExecutor() {
    }

    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 异步批处理多个实现{@link CallBack}接口的操作。
     * <p>
     *     异步批量执行操作，不做信号量控制，由Spring executor的配置决定线程的最大SIZE。
     * </p>
     * @param callBacks
     */
    public void excute(final List<CallBack> callBacks){
        for (final CallBack callBack:callBacks){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        callBack.call();
                    } catch (Exception e) {
                        LOG.debug(e.getStackTrace());
                    }
                }
            });
        }
    }

    /**
     * 异步批处理多个实现{@link CallBack}接口的操作,并在主线程同步等待异步处理完成，执行最后一步操作。
     * @param callBacks     异步批处理处理回调，依赖于size 大小。
     * @param lastCallBack  最后一处理，同步。
     */
    public void executeForLast(final List<CallBack> callBacks,CallBack lastCallBack) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(callBacks.size());

        for (final CallBack callBack:callBacks){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        callBack.call();
                    } catch (Exception e) {
                        LOG.debug(e.getStackTrace());
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        /**
         * 所有{@link callbacks}执行完成之后的动作
         * 执行。
         *
         * 当抛出{@link InterruptedException}之后，显然最后一步动作
         * 没有被执行到。
         *
         * 说明情况，如果有异常，自己抛异常给前端用户，这里并不需要执行最后一步动作。
         */
        lastCallBack.call();
    }

    /**
     * 获取线程活动数。
     * @return
     */
    public int getActiveCount(){
        return executor.getActiveCount();
    }

    /**
     * 获取线程队列数。
     * @return
     */
    public int getTaskCount(){
        return executor.getThreadPoolExecutor().getQueue().size();
    }
}

