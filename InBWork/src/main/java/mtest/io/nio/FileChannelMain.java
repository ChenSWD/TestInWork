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
		/* 创建文件，向文件中写入数据 */
		try {

			/* 如果文件不存在，创建该文件,文件后缀是不是文本文件不重要 */
			File file = new File("src\\\\main\\\\java\\\\mtest\\\\io\\\\nio\\\\nio.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			/* 根据文件输出流创建与这个文件相关的通道 */
			FileOutputStream fos = new FileOutputStream(file);
			FileChannel fc = fos.getChannel();

			/* 创建ByteBuffer对象， position = 0, limit = 64 */
			ByteBuffer bb = ByteBuffer.allocate(64);

			/* 向ByteBuffer中放入字符串UTF-8的字节, position = 17, limit = 64 */
			bb.put("Hello,World 123 \n".getBytes("gbk"));

			System.out.println("pos = " + bb.position() + " lim = " + bb.limit());
			/* flip方法 position = 0, limit = 17 将写模式转换成读模式 */
			bb.flip();

			/* write方法使得ByteBuffer的position到 limit中的元素写入通道中 */
			fc.write(bb);

			/* clear方法使得position = 0， limit = 64 清空缓冲区，将读模式转换写模式 */
			bb.clear();

			/* 下面的代码同理 */
			bb.put("你好，世界 456\n".getBytes("gbk"));
			bb.flip();

			fc.write(bb);
			bb.clear();

			fos.close();
			fc.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.out.println(e);
		}

		/* 从刚才的文件中读取字符序列 */
		try {

			/* 通过Path对象创建文件通道 */
			Path path = Paths.get("src\\main\\java\\mtest\\io\\nio\\nio.txt");
			FileChannel fc = FileChannel.open(path);

			ByteBuffer bb = ByteBuffer.allocate((int) fc.size() + 1);

			Charset utf8 = Charset.forName("gbk");

			/* 阻塞模式，读取完成才能返回 */
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
