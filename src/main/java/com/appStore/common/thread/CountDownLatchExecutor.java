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
package com.appStore.common.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.*;

/**
 *  线程执行器。
 *
 *  @author xiachuan at 2016/7/7 19:32。
 */

public class CountDownLatchExecutor {

    /**
     * 在主线程同步等待异步处理完成，再执行最后一步操作。
     * <p>
     *     外层做了分页控制。
     *     加入了并发控制。
     * </p>
     *
     * @param callbacks 异步批处理处理回调，依赖于size 大小。
     * @param lastCallback     最后一处理，同步。
     * @exception InterruptedException 当线程被中断时。
     */
    public static void execute( ThreadPoolTaskExecutor executor ,
                                final List<ExeCallback> callbacks,
                                ExeCallback lastCallback)
            throws InterruptedException {

        final Semaphore semaphore = new Semaphore(64);

        int size = callbacks.size();
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int k = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    //防止严重超时,异常必须抛出来。
                    try {
                        //控制并发数，线程资源有限。
                        semaphore.acquire();
                        callbacks.get(k).call();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage());
                    } finally {
                        countDownLatch.countDown();
                        semaphore.release();
                    }
                }
            });
        }
        countDownLatch.await();
        /**
         * 所有{@link callbacks}执行完成之后的动作
         * 执行。
         *
         * 说明情况，如果有异常，自己抛异常给前端用户，这里并不需要执行最后一步动作。
         */
        if(lastCallback != null){
            lastCallback.call();
        }

    }

    /**
     * 在主线程同步等待异步处理完成，再执行最后一步操作。
     * <p>
     *     外层做了分页控制。
     *     加入了并发控制。
     * </p>
     *
     * @param callbacks 异步批处理处理回调，依赖于size 大小。
     * @param threadMaxSize    最大同时开启线程数。
     * @exception InterruptedException 当线程被中断时。
     */
    public static void execute( ThreadPoolTaskExecutor executor ,
                                final List<ExeCallback> callbacks,
                                int threadMaxSize)
            throws InterruptedException {

        final  Semaphore  semaphore = new Semaphore(threadMaxSize);

        int size = callbacks.size();
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int k = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    //防止严重超时,异常必须抛出来。
                    try {
                        //控制并发数，线程资源有限。
                        semaphore.acquire();
                        callbacks.get(k).call();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage());
                    } finally {
                        countDownLatch.countDown();
                        semaphore.release();
                    }
                }
            });
        }
        countDownLatch.await();
    }

    /**
     * timeMillis 主线程等待的时间。
     *
     * 此方法针对查询，如果是写入操作，慎用。
     *
     * 在主线程同步等待异步处理完成，再执行最后一步操作。
     * <p>
     *     外层做了分页控制。
     *     加入了并发控制。
     * </p>
     *
     * @param callbacks 异步批处理处理回调，依赖于size 大小。
     * @param lastCallback     最后一处理，同步。
     * @exception InterruptedException 当线程被中断时。
     */
    public static void execute( ThreadPoolTaskExecutor executor ,
                                final List<ExeCallback> callbacks,
                                ExeCallback lastCallback,long timeMillis)
            throws InterruptedException, TimeoutException, ExecutionException {
        execute(executor,callbacks,lastCallback,timeMillis,64);
    }

    /**
     * timeMillis 主线程等待的时间。
     *
     * 此方法针对查询，如果是写入操作，慎用。
     *
     * 在主线程同步等待异步处理完成，再执行最后一步操作。
     * <p>
     *     外层做了分页控制。
     *     加入了并发控制。
     * </p>
     *
     * @param callbacks 异步批处理处理回调，依赖于size 大小。
     * @param lastCallback     最后一处理，同步。
     * @param threadMaxSize    最大同时开启线程数。
     * @exception InterruptedException 当线程被中断时。
     */
    public static void execute( ThreadPoolTaskExecutor executor ,
                                final List<ExeCallback> callbacks,
                                ExeCallback lastCallback,
                                long timeMillis,
                                int threadMaxSize)
            throws InterruptedException, TimeoutException, ExecutionException {

        final  Semaphore  semaphore = new Semaphore(threadMaxSize);

        int size = callbacks.size();
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int k = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    //防止严重超时,异常必须抛出来。
                    try {
                        //控制并发数，线程资源有限。
                        semaphore.acquire();
                        callbacks.get(k).call();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage());
                    } finally {
                        countDownLatch.countDown();
                        semaphore.release();
                    }
                }
            });

        }
        countDownLatch.await(timeMillis, TimeUnit.MILLISECONDS);
        /**
         * 所有{@link callbacks}执行完成之后的动作
         * 执行。
         *
         * 说明情况，如果有异常，自己抛异常给前端用户，这里并不需要执行最后一步动作。
         */
        if(lastCallback != null){
            lastCallback.call();
        }
    }

}

