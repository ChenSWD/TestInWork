package mtest.io.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelMain {

	public static void main(String[] args) {
		/* �����ļ������ļ���д������ */
		try {

			/* ����ļ������ڣ��������ļ�,�ļ���׺�ǲ����ı��ļ�����Ҫ */
			File file = new File("src\\\\main\\\\java\\\\mtest\\\\io\\\\nio\\\\nio.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			/* �����ļ����������������ļ���ص�ͨ�� */
			FileOutputStream fos = new FileOutputStream(file);
			FileChannel fc = fos.getChannel();

			/* ����ByteBuffer���� position = 0, limit = 64 */
			ByteBuffer bb = ByteBuffer.allocate(64);

			/* ��ByteBuffer�з����ַ���UTF-8���ֽ�, position = 17, limit = 64 */
			bb.put("Hello,World 123 \n".getBytes("gbk"));

			System.out.println("pos = " + bb.position() + " lim = " + bb.limit());
			/* flip���� position = 0, limit = 17 ��дģʽת���ɶ�ģʽ */
			bb.flip();

			/* write����ʹ��ByteBuffer��position�� limit�е�Ԫ��д��ͨ���� */
			fc.write(bb);

			/* clear����ʹ��position = 0�� limit = 64 ��ջ�����������ģʽת��дģʽ */
			bb.clear();

			/* ����Ĵ���ͬ�� */
			bb.put("��ã����� 456\n".getBytes("gbk"));
			bb.flip();

			fc.write(bb);
			bb.clear();

			fos.close();
			fc.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println(e);
		}

		/* �Ӹղŵ��ļ��ж�ȡ�ַ����� */
		try {

			/* ͨ��Path���󴴽��ļ�ͨ�� */
			Path path = Paths.get("src\\main\\java\\mtest\\io\\nio\\nio.txt");
			FileChannel fc = FileChannel.open(path);

			ByteBuffer bb = ByteBuffer.allocate((int) fc.size() + 1);

			Charset utf8 = Charset.forName("gbk");

			/* ����ģʽ����ȡ��ɲ��ܷ��� */
			fc.read(bb);

			bb.flip();
			CharBuffer cb = utf8.decode(bb);
			System.out.print(cb.toString());
			bb.clear();

			fc.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		{
			try {
				FileInputStream in = new FileInputStream(
						new File("src\\\\main\\\\java\\\\mtest\\\\io\\\\nio\\\\nio.txt"));
				FileChannel channel = in.getChannel();
				ByteBuffer bb = ByteBuffer.allocate((int) channel.size());
				Charset utf8 = Charset.forName("gbk");
				channel.read(bb);
				bb.flip();
				CharBuffer cc = utf8.decode(bb);
				System.out.println(cc.toString());
				bb.clear();
				channel.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
