package mtest.multithread.future;

//����Future���첽ԭ��  �ȴ�֪ͨ����
//�μ���https://juejin.im/entry/59104bb92f301e006c2a6124
//Java�첽��̽ӿڣ�Callable��Future  http://blueskykong.com/2017/05/20/callable-future
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