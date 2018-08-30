package mtest.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//测试futureTask和Callable接口异步线程执行
public class CallableTest {

	public static void main(String[] args) {
		// 第一种使用方式
		// //创建线程池
		// ExecutorService es = Executors.newSingleThreadExecutor();
		// //创建Callable对象任务
		// CallableDemo calTask=new CallableDemo();
		// //提交任务并获取执行结果
		// Future<Integer> future =es.submit(calTask);
		// //关闭线程池
		// es.shutdown();

		// 第二中使用方式

		// 创建线程池
		ExecutorService es = Executors.newSingleThreadExecutor();
		// 创建Callable对象任务
		CallableDemo calTask = new CallableDemo();
		// 创建FutureTask
		FutureTask<Integer> futureTask = new FutureTask<>(calTask);
		// 执行任务
		es.submit(futureTask);
		// 关闭线程池
		es.shutdown();
		try {
			Thread.sleep(1000);
			System.out.println("主线程在执行其他任务");
			if (futureTask.get() != null) {
				// 输出获取到的结果
				System.out.println("futureTask.get()-->" + futureTask.get());
			} else {
				// 输出获取到的结果
				System.out.println("futureTask.get()未获取到结果");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("主线程在执行完成");
	}
}
