package mtest.thread;

import java.util.concurrent.locks.LockSupport;

/* LockSupport���ƣ�
 * 1��Java����ͬ������ܵĺ���AQS:AbstractQueuedSynchronizer��
 * ����ͨ������LockSupport.park()��LockSupport.unpark()ʵ���̵߳������ͻ��ѵģ�
 * 2��LockSupport�е�park() �� unpark() �����÷ֱ��������̺߳ͽ�������̣߳�
 * 3��LockSupport������Ҫ��ȡ����ļ�������
 * 4��LockSupport������ÿ��unpark���߳�1��"���"�������ֻ����1����park���෴�������ǰ�߳�����ɣ�
 * ��ôpark����������1�������أ�
 * ����������߳�ֱ���߳����»����ɣ����߳�����֮ǰ���� park/unpark����û���κ�Ч����
 * 5�� LockSupport ��park��Object��waitһ��Ҳ����Ӧ�ж�. */
public class LockSupportTest {

	public static void main(String[] args) throws InterruptedException {
		LockSupportTest main = new LockSupportTest();
		Thread t = new Thread(main.runnable);
		System.out.println("start thread");
		t.start();
		//��ε��� unpark ���Ҳֻ��õ�1�����
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
		//�����жϣ����Խ��park��״̬
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
