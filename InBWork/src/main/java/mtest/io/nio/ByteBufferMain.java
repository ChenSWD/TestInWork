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
		 
		/*对获取utf8的编解码器*/
		Charset utf8 = Charset.forName("UTF-8");
		CharBuffer charBuffer = utf8.decode(byteBuffer);/*对bytebuffer中的内容解码*/
		 
		/*array()返回的就是内部的数组引用，编码以后的有效长度是0~limit*/
		char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
		System.out.println(charArr); /*运行结果：我爱你*/
		
		
		
		CharBuffer charBuffer1 = CharBuffer.allocate(128);
		charBuffer1.append("我爱你");
		charBuffer1.flip();
		 
		/*对获取utf8的编解码器*/
		Charset utf81 = Charset.forName("UTF-8");
		ByteBuffer byteBuffer1 = utf81.encode(charBuffer1); /*对charbuffer中的内容编码*/
		 
		/*array()返回的就是内部的数组引用，编码以后的有效长度是0~limit*/
		byte[] bytes = Arrays.copyOf(byteBuffer1.array(), byteBuffer1.limit());
		System.out.println(Arrays.toString(bytes));
		/*运行结果：[-26, -120, -111, -25, -120, -79, -28, -67, -96] */
	}
}
