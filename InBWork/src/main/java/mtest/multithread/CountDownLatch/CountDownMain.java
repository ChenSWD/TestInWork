package mtest.multithread.CountDownLatch;

import mtest.multithread.CountDownLatch.loadingData.impl.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CountDownMain {

	static class StartGame implements Runnable {
		private CountDownLatch count;

		public StartGame(CountDownLatch count) {
			this.count = count;
		}

		@Override
		public void run() {
			try {
				System.out.println("开始加载基础数据...");
				Long l1 = System.currentTimeMillis();
				// 在count变为0的时候解除阻塞
				// 调用await()方法只进行阻塞，对计数没任何影响
				count.await();
				Thread.sleep(2000);
				Long l2 = System.currentTimeMillis();
				System.out.println("基础数据加载完毕，总共花费时长:" + (l2 - l1) + ".可以开始游戏...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class StartGame1 implements Runnable {
		private CountDownLatch count;

		public StartGame1(CountDownLatch count) {
			this.count = count;
		}

		@Override
		public void run() {
			try {
				System.out.println("开始加载基础数据11...");
				Long l1 = System.currentTimeMillis();
				// 在count变为0的时候解除阻塞
				// 调用await()方法只进行阻塞，对计数没任何影响
				count.await();
				Long l2 = System.currentTimeMillis();
				System.out.println("基础数据加载完毕，总共花费时长11:" + (l2 - l1) + ".可以开始游戏...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//共享锁  在达到条件后，可以同时唤醒 startGameThread 和 startGameThread1 两个线程
		CountDownLatch count = new CountDownLatch(4);

		// 主线程
		Thread startGameThread = new Thread(new StartGame(count));
		startGameThread.start();
		
		// 主线程1
		Thread startGameThread1 = new Thread(new StartGame1(count));
		startGameThread1.start();

		// 加载数据线程
		Thread mapThread = new Thread(new MapData("地图", count));
		Thread goodsThread = new Thread(new GoodsData("物品", count));
		Thread personageThread = new Thread(new PersonageData("人物", count));
		Thread backGroundThread = new Thread(new BackGroundData("背景", count));

		mapThread.start();
		goodsThread.start();
		personageThread.start();
		backGroundThread.start();

		System.in.read();
	}
}
