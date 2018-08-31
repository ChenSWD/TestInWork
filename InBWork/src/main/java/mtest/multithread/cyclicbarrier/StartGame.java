package mtest.multithread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class StartGame implements Runnable {
	private String player;
	private CyclicBarrier barrier;

	public StartGame(String player, CyclicBarrier barrier) {
		this.player = player;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			System.out.println(this.getPlayer() + " ��ʼƥ�����...");
			findOtherPlayer();
			//����await()����������һ������һ���ֵ������0�����߳�����
			//�����ﵽ0ʱ��������Ϊ�����ֵ���¿�ʼ
			barrier.await();
			
			System.out.println(this.getPlayer() + " ����ѡ���ɫ...");
			choiceRole();
			System.out.println(this.getPlayer() + " ��ɫѡ����ϵȴ��������...");
			barrier.await();

			System.out.println(this.getPlayer() + " ��ʼ��Ϸ,������Ϸ����...");
			loading();
			System.out.println(this.getPlayer() + " ��Ϸ������ϵȴ�������Ҽ������...");
			barrier.await();
			// barrier.await(2,TimeUnit.SECONDS);

			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPlayer() {
		return player;
	}

	/**
	 * ƥ���������
	 */
	public void findOtherPlayer() throws InterruptedException {
		Thread.sleep((long) (1000 * Math.random()));
	}

	/**
	 * ѡ���ɫ
	 */
	public void choiceRole() throws InterruptedException {
		Thread.sleep((long) (3000 * Math.random()));
	}

	/**
	 * ����
	 */
	public void loading() throws InterruptedException {
		Thread.sleep((long) (10000 * Math.random()));
	}

	/**
	 * ��ʼ
	 */
	public void start() {
		System.out.println(this.getPlayer() + " ��ʼ");
	}

}