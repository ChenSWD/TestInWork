package mtest.callable;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer> {
	private int sum;

	@Override
	public Integer call() throws Exception {
		System.out.println("Callable���߳̿�ʼ��������");
		Thread.sleep(4000);
		for (int i = 0; i < 5000; i++) {
			sum = sum + i;
		}
		System.out.println("Callable���̼߳��������");
		return sum;
	}
}
