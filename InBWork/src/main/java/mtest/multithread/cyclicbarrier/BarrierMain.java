package mtest.multithread.cyclicbarrier;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

public class BarrierMain {

	public static void main(String[] args) throws IOException {
		CyclicBarrier barrier = new CyclicBarrier(5, () -> {
			try {
				System.out.println("�׶���ɣ��ȴ�2��...");
				Thread.sleep(2000);
				System.out.println("�����¸��׶�...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});

		Thread player1 = new Thread(new StartGame("1", barrier));
		Thread player2 = new Thread(new StartGame("2", barrier));
		Thread player3 = new Thread(new StartGame("3", barrier));
		Thread player4 = new Thread(new StartGame("4", barrier));
		Thread player5 = new Thread(new StartGame("5", barrier));

		player1.start();
		player2.start();
		player3.start();
		player4.start();
		player5.start();

		System.in.read();
	}
}
