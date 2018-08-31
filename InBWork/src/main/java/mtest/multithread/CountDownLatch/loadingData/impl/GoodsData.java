package mtest.multithread.CountDownLatch.loadingData.impl;

import mtest.multithread.CountDownLatch.loadingData.AbstractDataRunnable;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class GoodsData extends AbstractDataRunnable {

    public GoodsData(String name, CountDownLatch count) {
        super(name, count);
    }

    @Override
    public void handle() throws InterruptedException {
        //ģ�����ʱ�䣬2.5��
        Thread.sleep(2500);
    }
}