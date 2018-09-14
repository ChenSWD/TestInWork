package mtest.thread;

public class Interrupt {
	
	public static void main(String[] args) {
		Interrupt main = new Interrupt();
        Thread t = new Thread(main.runnable);
        System.out.println("mainmainmain");
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //设置中断标记，在中断状态下，调用object的wait()、线程的sleep()、join()方法会产生InterruptedException
        t.interrupt();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int i = 0;
            try {
                synchronized (runnable) {
                	while (i < 1000) {
                        wait();
                        System.out.println(i++);
                    }
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
