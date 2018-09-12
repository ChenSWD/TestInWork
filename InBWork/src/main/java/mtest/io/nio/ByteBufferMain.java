package mtest.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteBufferMain {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(128);
		byteBuffer.put(new byte[]{-26, -120, -111, -25, -120, -79, -28, -67, -96});
		byteBuffer.flip();
		 
		/*�Ի�ȡutf8�ı������*/
		Charset utf8 = Charset.forName("UTF-8");
		CharBuffer charBuffer = utf8.decode(byteBuffer);/*��bytebuffer�е����ݽ���*/
		 
		/*array()���صľ����ڲ����������ã������Ժ����Ч������0~limit*/
		char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
		System.out.println(charArr); /*���н�����Ұ���*/
		
		
		
		CharBuffer charBuffer1 = CharBuffer.allocate(128);
		charBuffer1.append("�Ұ���");
		charBuffer1.flip();
		 
		/*�Ի�ȡutf8�ı������*/
		Charset utf81 = Charset.forName("UTF-8");
		ByteBuffer byteBuffer1 = utf81.encode(charBuffer1); /*��charbuffer�е����ݱ���*/
		 
		/*array()���صľ����ڲ����������ã������Ժ����Ч������0~limit*/
		byte[] bytes = Arrays.copyOf(byteBuffer1.array(), byteBuffer1.limit());
		System.out.println(Arrays.toString(bytes));
		/*���н����[-26, -120, -111, -25, -120, -79, -28, -67, -96] */
	}
}
