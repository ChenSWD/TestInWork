package mtest.proxy;

import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) {
		ProxyFactory factory = new ProxyFactory();// ��������
		factory.setTargetObject(new ManWaiter());// ����Ŀ�����
		factory.setBeforeAdvice(new BeforeAdvice() {// ����ǰ����ǿ
			public void before() {
				System.out.println("���ò��ã�");
			}
		});

		factory.setAfterAdvice(new AfterAdvice() {// ���ú�����ǿ
			public void after() {
				System.out.println("�ټ�������");
			}
		});
		Waiter waiter = (Waiter) factory.createProxy();
		waiter.shouQian();
	}
}
