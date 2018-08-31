package mtest.multithread.CountDownLatch.loadingData.impl;

import mtest.multithread.CountDownLatch.loadingData.AbstractDataRunnable;

import java.util.concurrent.CountDownLatch;

/**
 * 地图模型
 */
public class MapData extends AbstractDataRunnable {
    public MapData(String name, CountDownLatch count) {
        super(name, count);
    }

    @Override
    public void handle() throws InterruptedException {
        //模拟加载时间，3秒
        Thread.sleep(3000);
    }
}