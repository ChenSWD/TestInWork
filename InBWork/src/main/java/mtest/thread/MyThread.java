package mtest.thread;

public class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println("mythread = " + this + " current = " + Thread.currentThread() + " group = "
				+ this.getThreadGroup() + " id = " + Thread.currentThread().getId());
		MyThread1 thread = new MyThread1();
		thread.start();
	}
}
