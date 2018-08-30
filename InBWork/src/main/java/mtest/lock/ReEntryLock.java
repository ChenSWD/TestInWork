package mtest.lock;

//可重入锁  相对来说，可重入就意味着：线程可以进入任何一个它已经拥有的锁所同步着的代码块。
public class ReEntryLock {
	private boolean isLock = false;
	private Thread thread = null;
	private int lockedCount = 0;

	public synchronized void lock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
//		System.out.println("currentThread = " + currentThread + " thread = " + thread);
		while (isLock && currentThread != thread) {
			wait();
		}
		lockedCount++;
		thread = currentThread;
		isLock = true;
	}

	public synchronized void unLock() {
		System.out.println("unlock");
		if (Thread.currentThread() == this.thread) {
			lockedCount--;
			if (lockedCount == 0) {
				isLock = false;
				notify();
			}
		}
	}
}
