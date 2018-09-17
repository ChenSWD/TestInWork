package mtest.thread;

import java.util.concurrent.locks.LockSupport;

/* LockSupport机制：
 * 1、Java锁和同步器框架的核心AQS:AbstractQueuedSynchronizer，
 * 就是通过调用LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒的；
 * 2、LockSupport中的park() 和 unpark() 的作用分别是阻塞线程和解除阻塞线程；
 * 3、LockSupport并不需要获取对象的监视器；
 * 4、LockSupport机制是每次unpark给线程1个"许可"——最多只能是1，而park则相反，如果当前线程有许可，
 * 那么park方法会消耗1个并返回，
 * 否则会阻塞线程直到线程重新获得许可，在线程启动之前调用 park/unpark方法没有任何效果；
 * 5、 LockSupport 的park和Object的wait一样也能响应中断. */
public class LockSupportTest {

	public static void main(String[] args) throws InterruptedException {
		LockSupportTest main = new LockSupportTest();
		Thread t = new Thread(main.runnable);
		System.out.println("start thread");
		t.start();
		//多次调用 unpark 最多也只会得到1个许可
		LockSupport.unpark(t);
		LockSupport.unpark(t);
		LockSupport.unpark(t);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("start interrupt");
		//调用中断，可以解除park的状态
		t.interrupt();
	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LockSupport.park();
			System.out.println("lock support park1");
			LockSupport.park();
			System.out.println("lock support park2");
		}
	};
}
