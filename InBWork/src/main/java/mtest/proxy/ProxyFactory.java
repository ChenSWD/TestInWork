package mtest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProxyFactory {
	private Object targetObject;// Ŀ�����
	private BeforeAdvice beforeAdvice;// ǰ����ǿ
	private AfterAdvice afterAdvice;// ������ǿ

	/**
	 * �������ɴ������
	 * 
	 * @return
	 */
	public Object createProxy() {
		/*
		 * 1. �����������
		 */
		ClassLoader loader = targetObject.getClass().getClassLoader();
		System.out.println("loader = "+loader);
		Class[] interfaces = targetObject.getClass().getInterfaces();
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				/*
				 * �ڵ��ô������ķ���ʱ��ִ�����������
				 */
				// ִ��ǰ����ǿ
				if (beforeAdvice != null) {
					beforeAdvice.before();
				}

				Object result = method.invoke(targetObject, args);// ִ��Ŀ������Ŀ�귽��
				// ִ�к�����ǿ
				if (afterAdvice != null) {
					afterAdvice.after();
				}

				// ����Ŀ�����ķ���ֵ
				return result;
			}
		};
		/*
		 * 2. �õ��������
		 */
		Object proxyObject = Proxy.newProxyInstance(loader, interfaces, h);
		return proxyObject;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

	public BeforeAdvice getBeforeAdvice() {
		return beforeAdvice;
	}

	public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
		this.beforeAdvice = beforeAdvice;
	}

	public AfterAdvice getAfterAdvice() {
		return afterAdvice;
	}

	public void setAfterAdvice(AfterAdvice afterAdvice) {
		this.afterAdvice = afterAdvice;
	}
}
