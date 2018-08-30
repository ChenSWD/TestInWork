package mtest.lock;

//不可重入锁
public class NotReEntryLock {
	private boolean isLock = false;

	public synchronized void lock() throws InterruptedException {
		while (isLock) {
			wait();
		}
		isLock = true;
	}

	public synchronized void unLock() {
		isLock = false;
		notify();
	}
}
