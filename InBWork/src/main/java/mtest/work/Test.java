package mtest.work;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws NoSuchProviderException {
		// TODO Auto-generated method stub
		// http://www.google.com.hk
		// Request request = new
		// Request.Builder().url("http://www.baidu.com").build();
		/*
		 * Request request = new
		 * Request.Builder().url("http://www.google.com.hk").build(); OkHttpClient
		 * client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS)
		 * .readTimeout(2, TimeUnit.SECONDS).writeTimeout(2, TimeUnit.SECONDS).build();
		 * client.newCall(request).enqueue(new Callback() {
		 * 
		 * @Override public void onResponse(Call arg0, Response arg1) throws IOException
		 * { // TODO Auto-generated method stub System.out.println("成功"); }
		 * 
		 * @Override public void onFailure(Call arg0, IOException e) { // TODO
		 * Auto-generated method stub System.out.println("onFailure");
		 * 
		 * if (e.getClass().equals(SocketTimeoutException.class)) {
		 * client.newCall(request).enqueue(this); System.out.println("请求超时"); } } });
		 */
		// System.out.println(Math.toDegrees(Math.atan(-1)));
		// UUID ID = UUID.randomUUID();
		// System.out.println(ID + " ID = " + ID.toString());
		// 静态方法可以重写但是只属于类
		/*
		 * Dog dog = new Dog(); dog.tttt(); Dog cat = new Cat(); cat.tttt();
		 */
		/*
		 * Message message = new Message(); WeakReference<Message> message1 = new
		 * WeakReference(message); System.out.println(message + " " + message1 + " " +
		 * message1.get()); message = null; System.gc(); System.out.println(message +
		 * " " + message1 + " " + message1.get());
		 */

		// class对象只有一个，类加载器只会加载一次class文件，生成一个class对象作为方法区的入口
		/*
		 * Message message1 = new Message(); Message message2 = new Message(); try {
		 * System.out.println("1 = " + message1.getClass() + " 2 = " +
		 * message2.getClass() + " 3 = " + Message.class + " 4 = " +
		 * Class.forName("work.Cat")); } catch (ClassNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		List<Message> message = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			message.add(new Message(i));
			System.out.println(message.get(i));
		}
		Collections.sort(message, new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				System.out.println("o1 = " + o1 + " o2 = " + o2);
				return o1.getNumber() > o2.getNumber() ? -1 : 1;
			}
		});
		for (int i = 0; i < 10; i++) {
			System.out.println(message.get(i).getNumber() + " " + message.get(i));
		}
	}

	public static class Message {
		int number = 0;

		public Message(int number) {
			this.number = number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}
	}
}
