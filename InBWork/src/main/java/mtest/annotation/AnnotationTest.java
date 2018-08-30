package mtest.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//����javaע�����
//����������Ԫע�⣬����ע���Զ����ע��
/*@Target��

@Target˵����Annotation�����εĶ���Χ��Annotation�ɱ����� packages��types���ࡢ�ӿڡ�ö�١�Annotation���ͣ���
	���ͳ�Ա�����������췽������Ա������ö��ֵ�������������ͱ��ر�������ѭ��������catch��������
	��Annotation���͵�������ʹ����target�ɸ������������ε�Ŀ�ꡣ

���ã���������ע���ʹ�÷�Χ��������������ע���������ʲô�ط���

ȡֵ(ElementType)����Դ��Java.lang.annotation.ElementType�е�ö������Ԫ�أ�

��1.CONSTRUCTOR:��������������
��2.FIELD:����������
��3.LOCAL_VARIABLE:���������ֲ�����
��4.METHOD:������������
��5.PACKAGE:����������
��6.PARAMETER:������������
��7.TYPE:���������ࡢ�ӿ�(����ע������) ��enum����

@Retention��

����@Retention�����˸�Annotation��������ʱ�䳤�̣�ĳЩAnnotation��������Դ�����У�����������������
	����һЩȴ��������class�ļ��У�������class�ļ��е�Annotation���ܻᱻ��������ԣ�����һЩ��class��װ��ʱ������ȡ
	����ע�Ⲣ��Ӱ��class��ִ�У���ΪAnnotation��class��ʹ�����Ǳ�����ģ���
	ʹ�����meta-Annotation���Զ� Annotation�ġ��������ڡ����ơ�

�������ã���ʾ��Ҫ��ʲô���𱣴��ע����Ϣ����������ע����������ڣ�������������ע����ʲô��Χ����Ч��

����ȡֵ��RetentionPoicy����Դ��java.lang.annotation.RetentionPolicy��ö������ֵ��

��������1.SOURCE:��Դ�ļ�����Ч����Դ�ļ�������
��������2.CLASS:��class�ļ�����Ч����class������
��������3.RUNTIME:������ʱ��Ч��������ʱ������


@Documented:

����@Documented���������������͵�annotationӦ�ñ���Ϊ����ע�ĳ����Ա�Ĺ���API��
	��˿��Ա�����javadoc����Ĺ����ĵ�����Documented��һ�����ע�⣬û�г�Ա��


@Inherited��

����@Inherited Ԫע����һ�����ע�⣬@Inherited������ĳ������ע�������Ǳ��̳еġ�
	���һ��ʹ����@Inherited���ε�annotation���ͱ�����һ��class�������annotation�������ڸ�class�����ࡣ

����ע�⣺@Inherited annotation�����Ǳ���ע����class���������̳С��ಢ��������ʵ�ֵĽӿڼ̳�annotation��
	�����������������صķ����̳�annotation��

������@Inherited annotation���ͱ�ע��annotation��Retention��RetentionPolicy.RUNTIME������API��ǿ�����ּ̳��ԡ�
	�������ʹ��java.lang.reflectȥ��ѯһ��@Inherited annotation���͵�annotationʱ��
	��������齫չ�����������class���丸�ֱ࣬������ָ����annotation���ͱ����֣����ߵ�����̳нṹ�Ķ��㡣
*/
public class AnnotationTest {
	public static void main(String[] args) {
		AnnotationDemo ann = new AnnotationDemo();
		Car car = new Car("test", 500);
		// ann.setCar(car);
		System.out.println(ann.getCar());
		annoProcess(ann);
		System.out.println(ann.getCar());
	}

	public static void annoProcess(AnnotationDemo annotation) {

		for (Method method : annotation.getClass().getMethods()) {
			if (method.getName().equals("getCar")) {
				try {
					Car car = (Car) method.invoke(annotation, null);
					for (Field field : annotation.getClass().getDeclaredFields()) {
						if (field.isAnnotationPresent(MyTag.class)) { // �������MyTag��ǩ
							MyTag myTag = field.getAnnotation(MyTag.class);
							System.out.println(field);
							System.out.println(car);
							if (car != null) {
								car.setName(myTag.name());
								car.setSize(myTag.size());
							} else {
								annotation.setCar(new Car(myTag.name(), myTag.size()));
							}
							break;
						}
					}
					System.out.println(car);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
