package mtest.multithread.CountDownLatch.loadingData.impl;

import mtest.multithread.CountDownLatch.loadingData.AbstractDataRunnable;

import java.util.concurrent.CountDownLatch;

/**
 * ��ͼģ��
 */
public class MapData extends AbstractDataRunnable {
    public MapData(String name, CountDownLatch count) {
        super(name, count);
    }

    @Override
    public void handle() throws InterruptedException {
        //ģ�����ʱ�䣬3��
        Thread.sleep(3000);
    }
}