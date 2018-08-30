package mtest.lock;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Test test = new Test();
		test.print();
	}

	private static class Test {
		NotReEntryLock lock = new NotReEntryLock();
//		ReEntryLock lock = new ReEntryLock();

		public void print() throws InterruptedException {
			System.out.println("print");
			lock.lock();
		/*	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						doAdd();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();*/
			doAdd();
			lock.unLock();
		}

		public void doAdd() throws InterruptedException {
			System.out.println("doAdd");
			lock.lock();
			System.out.println("doAdd lock");
			lock.unLock();
		}
	}
}
