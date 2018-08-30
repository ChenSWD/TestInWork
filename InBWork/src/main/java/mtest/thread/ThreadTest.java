package mtest.thread;

//测试线程相关内容
public class ThreadTest {

	public static void main(String[] args) {
		System.out
				.println("thread = " + Thread.currentThread() + " group = " + Thread.currentThread().getThreadGroup());
		for (int i = 0; i < 10; i++) {
			MyThread thread = new MyThread();
			thread.start();
		}
	}
}
