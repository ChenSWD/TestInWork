package mtest.multithread.CountDownLatch.loadingData.impl;

import mtest.multithread.CountDownLatch.loadingData.AbstractDataRunnable;

import java.util.concurrent.CountDownLatch;

/**
 * ����ģ��
 */
public class PersonageData extends AbstractDataRunnable {
    public PersonageData(String name, CountDownLatch count) {
        super(name, count);
    }

    @Override
    public void handle() throws InterruptedException {
        //ģ�����ʱ�䣬1��
        Thread.sleep(1000);
    }
}