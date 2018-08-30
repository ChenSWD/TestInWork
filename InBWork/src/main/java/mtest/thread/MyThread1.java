package mtest.thread;

public class MyThread1 extends Thread {
	@Override
	public void run() {
		System.out.println("mythread1 = " + this + " current1 = " + Thread.currentThread()+" group = "+this.getThreadGroup());
		
	}
}
