package mtest.multithread.future;

//测试Future的异步原理  等待通知机制
//参见：https://juejin.im/entry/59104bb92f301e006c2a6124
//Java异步编程接口：Callable和Future  http://blueskykong.com/2017/05/20/callable-future
import java.util.concurrent.ThreadPoolExecutor;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Service service = new Service();
		IData data = service.getData("test");

		System.out.println("a");
		System.out.println("b");
		System.out.println("result is " + data.getResult());
		System.out.println("c");
	}
}