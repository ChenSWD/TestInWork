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
				System.out.println("��ʼ���ػ�������...");
				Long l1 = System.currentTimeMillis();
				// ��count��Ϊ0��ʱ��������
				// ����await()����ֻ�����������Լ���û�κ�Ӱ��
				count.await();
				Thread.sleep(2000);
				Long l2 = System.currentTimeMillis();
				System.out.println("�������ݼ�����ϣ��ܹ�����ʱ��:" + (l2 - l1) + ".���Կ�ʼ��Ϸ...");
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
				System.out.println("��ʼ���ػ�������11...");
				Long l1 = System.currentTimeMillis();
				// ��count��Ϊ0��ʱ��������
				// ����await()����ֻ�����������Լ���û�κ�Ӱ��
				count.await();
				Long l2 = System.currentTimeMillis();
				System.out.println("�������ݼ�����ϣ��ܹ�����ʱ��11:" + (l2 - l1) + ".���Կ�ʼ��Ϸ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//������  �ڴﵽ�����󣬿���ͬʱ���� startGameThread �� startGameThread1 �����߳�
		CountDownLatch count = new CountDownLatch(4);

		// ���߳�
		Thread startGameThread = new Thread(new StartGame(count));
		startGameThread.start();
		
		// ���߳�1
		Thread startGameThread1 = new Thread(new StartGame1(count));
		startGameThread1.start();

		// ���������߳�
		Thread mapThread = new Thread(new MapData("��ͼ", count));
		Thread goodsThread = new Thread(new GoodsData("��Ʒ", count));
		Thread personageThread = new Thread(new PersonageData("����", count));
		Thread backGroundThread = new Thread(new BackGroundData("����", count));

		mapThread.start();
		goodsThread.start();
		personageThread.start();
		backGroundThread.start();

		System.in.read();
	}
}
