package mtest.multithread.CountDownLatch.loadingData;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractDataRunnable implements Runnable {
	private String name;
	private CountDownLatch count;

	public AbstractDataRunnable(String name, CountDownLatch count) {
		this.name = name;
		this.count = count;
	}

	@Override
	public void run() {
		try {
			System.out.println(this.getName() + " ��ʼ����...");
			Long l1 = System.currentTimeMillis();
			handle();
			Long l2 = System.currentTimeMillis();
			System.out.println(this.getName() + " �������,����ʱ��:" + (l2 - l1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ����countDown()����������һ
			count.countDown();
		}
		afterCountDown();
	}

	public String getName() {
		return name;
	}

	public abstract void handle() throws InterruptedException;

	public void afterCountDown() {
		System.out.println(this.getName() + ":CountDownLatch������һ֮��,����������������...");
	};
}