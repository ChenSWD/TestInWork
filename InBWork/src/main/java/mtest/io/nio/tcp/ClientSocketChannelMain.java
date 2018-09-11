package mtest.io.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import mtest.io.nio.Buffers;

/*�ͻ���:�ͻ���ÿ��1~2���Զ���������������ݣ����շ��������յ����ݲ���ʾ*/
public class ClientSocketChannelMain {
	public static class TCPEchoClient implements Runnable {

		/* �ͻ����߳��� */
		private String name;
		private Random rnd = new Random();

		/* ��������ip��ַ+�˿�port */
		private InetSocketAddress remoteAddress;

		public TCPEchoClient(String name, InetSocketAddress remoteAddress) {
			this.name = name;
			this.remoteAddress = remoteAddress;
		}

		@Override
		public void run() {

			/* ���������� */
			Charset utf8 = Charset.forName("UTF-8");

			Selector selector;

			try {

				/* ����TCPͨ�� */
				SocketChannel sc = SocketChannel.open();

				/* ����ͨ��Ϊ������ */
				sc.configureBlocking(false);

				/* ����ѡ���� */
				selector = Selector.open();

				/* ע�����Ȥ�¼� */
				int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;

				/* ��ѡ����ע��ͨ�� */
				sc.register(selector, interestSet, new Buffers(256, 256));

				/* ���������������,һ��ͨ������һ��tcp���� */
				sc.connect(remoteAddress);

				/* �ȴ������������ */
				while (!sc.finishConnect()) {
					;
				}

				System.out.println(name + " " + "finished connection");

			} catch (IOException e) {
				System.out.println("client connect failed");
				return;
			}

			/* ��������Ͽ����̱߳��ж�������߳� */
			try {

				int i = 1;
				while (!Thread.currentThread().isInterrupted()) {

					/* �����ȴ� */
					selector.select();

					/* Set�е�ÿ��key����һ��ͨ�� */
					Set<SelectionKey> keySet = selector.selectedKeys();
					Iterator<SelectionKey> it = keySet.iterator();

					/* ����ÿ���Ѿ�����ͨ�����������ͨ���Ѿ������¼� */
					while (it.hasNext()) {

						SelectionKey key = it.next();
						/* ��ֹ�´�select���������Ѵ������ͨ�� */
						it.remove();

						/* ͨ��SelectionKey��ȡ��Ӧ��ͨ�� */
						Buffers buffers = (Buffers) key.attachment();
						ByteBuffer readBuffer = buffers.getReadBuffer();
						ByteBuffer writeBuffer = buffers.gerWriteBuffer();

						/* ͨ��SelectionKey��ȡͨ����Ӧ�Ļ����� */
						SocketChannel sc = (SocketChannel) key.channel();

						/* ��ʾ�ײ�socket�Ķ������������ݿɶ� */
						if (key.isReadable()) {
							/* ��socket�Ķ���������ȡ��������Ļ������� */
							sc.read(readBuffer);
							readBuffer.flip();
							/* �ֽڵ�utf8���� */
							CharBuffer cb = utf8.decode(readBuffer);
							/* ��ʾ���յ��ɷ��������͵���Ϣ */
							System.out.println(cb.array());
							readBuffer.clear();
						}

						/* socket��д��������д */
						if (key.isWritable()) {
							writeBuffer.put((name + "  " + i).getBytes("UTF-8"));
							writeBuffer.flip();
							/* ��������Ļ������е�����д�뵽socket��д�������� */
							sc.write(writeBuffer);
							writeBuffer.clear();
							i++;
						}
					}

					Thread.sleep(1000 + rnd.nextInt(1000));
				}

			} catch (InterruptedException e) {
				System.out.println(name + " is interrupted");
			} catch (IOException e) {
				System.out.println(name + " encounter a connect error");
			} finally {
				try {
					selector.close();
				} catch (IOException e1) {
					System.out.println(name + " close selector failed");
				} finally {
					System.out.println(name + "  closed");
				}
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {

		InetSocketAddress remoteAddress = new InetSocketAddress("127.0.0.1", 8081);

		Thread ta = new Thread(new TCPEchoClient("thread a", remoteAddress));
		Thread tb = new Thread(new TCPEchoClient("thread b", remoteAddress));
		Thread tc = new Thread(new TCPEchoClient("thread c", remoteAddress));
		Thread td = new Thread(new TCPEchoClient("thread d", remoteAddress));

		ta.start();
		tb.start();
		tc.start();

		Thread.sleep(5000);

		/* �����ͻ���a */
		ta.interrupt();

		/* ��ʼ�ͻ���d */
		td.start();
	}
}
