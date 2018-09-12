package mtest.io.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import mtest.io.nio.Buffers;

/*�������ˣ�:���տͻ��˷��͹��������ݲ���ʾ��
 *���������Ͻ��յ������ݼ���"echo from service:"�ٷ��ͻ�ȥ*/
public class ServiceSocketChannelDemo {

	public static class TCPEchoServer implements Runnable {

		/* ��������ַ */
		private InetSocketAddress localAddress;

		public TCPEchoServer(int port) throws IOException {
			this.localAddress = new InetSocketAddress(port);
		}

		@Override
		public void run() {

			Charset utf8 = Charset.forName("UTF-8");

			ServerSocketChannel ssc = null;
			Selector selector = null;

			Random rnd = new Random();

			try {
				/* ����ѡ���� */
				selector = Selector.open();

				/* ����������ͨ�� */
				ssc = ServerSocketChannel.open();
				ssc.configureBlocking(false);

				/* ���ü����������Ķ˿ڣ�����������ӻ�����Ϊ100 */
				ssc.bind(localAddress, 100);

				/* ������ͨ��ֻ�ܶ�tcp�����¼�����Ȥ */
				ssc.register(selector, SelectionKey.OP_ACCEPT);

			} catch (IOException e1) {
				System.out.println("server start failed");
				return;
			}

			System.out.println("server start with address : " + localAddress);

			/* �������̱߳��жϺ���˳� */
			try {

				while (!Thread.currentThread().isInterrupted()) {

					// select() ���� ������������һ��ͨ������ע����¼��Ͼ�����
					int n = selector.select(3000);
					if (n == 0) {
						System.out.println("�ȴ�����ʱ......");
						continue;
					}

					Set<SelectionKey> keySet = selector.selectedKeys();
					Iterator<SelectionKey> it = keySet.iterator();
					SelectionKey key = null;

					while (it.hasNext()) {

						key = it.next();
						/* ��ֹ�´�select���������Ѵ������ͨ�� */
						it.remove();

						/* �������쳣��˵���ͻ������ӳ�������,��������Ҫ�������� */
						try {
							/*
							 * ͨ��������һ���¼���˼�Ǹ��¼��Ѿ����������ԣ�ĳ��channel�ɹ����ӵ���һ����������Ϊ�����Ӿ������� һ��server socket
							 * channel׼���Ž����½�������ӳ�Ϊ�����վ������� һ�������ݿɶ���ͨ������˵�ǡ������������ȴ�д���ݵ�ͨ������˵�ǡ�д��������
							 */

							/* sscͨ��ֻ�ܶ������¼�����Ȥ */
							if (key.isAcceptable()) {

								/*
								 * accept�����᷵��һ����ͨͨ���� ÿ��ͨ�����ں��ж���Ӧһ��socket������
								 */
								SocketChannel sc = ssc.accept();
								// ����ͨ��Ϊ������
								sc.configureBlocking(false);

								/* ��ѡ����ע�����ͨ������ͨͨ������Ȥ���¼���ͬʱ�ṩ�����ͨ����صĻ����� */
								int interestSet = SelectionKey.OP_READ;
								//�ͻ��˺ͷ���˸�ά����д����buffer(Buffers)
								sc.register(selector, interestSet, new Buffers(256, 256));

								System.out.println("accept from " + sc.getRemoteAddress());
							}

							/* ����ͨ��ͨ������Ȥ���¼��������ݿɶ� */
							if (key.isReadable()) {
								/* ͨ��SelectionKey��ȡͨ����Ӧ�Ļ����� */
								Buffers buffers = (Buffers) key.attachment();
								ByteBuffer readBuffer = buffers.getReadBuffer();
								ByteBuffer writeBuffer = buffers.gerWriteBuffer();

								/* ͨ��SelectionKey��ȡ��Ӧ��ͨ�� */
								SocketChannel sc = (SocketChannel) key.channel();

								/* �ӵײ�socket���������ж������� */
								sc.read(readBuffer);
								readBuffer.flip();

								/* ������ʾ���ͻ��˷���������Ϣ */
								CharBuffer cb = utf8.decode(readBuffer);
								System.out.println(cb.array());

								readBuffer.rewind();

								/* ׼������ͻ��˷��͵���Ϣ */
								/* ��д��"echo:"����д���յ�����Ϣ */
								writeBuffer.put("echo from service:".getBytes("UTF-8"));
								writeBuffer.put(readBuffer);

								readBuffer.clear();

								/* ����ͨ��д�¼� */
								key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);

							}

							/* ͨ������Ȥд�¼��ҵײ㻺�����п��� */
							if (key.isWritable()) {
								Buffers buffers = (Buffers) key.attachment();

								ByteBuffer writeBuffer = buffers.gerWriteBuffer();
								writeBuffer.flip();

								SocketChannel sc = (SocketChannel) key.channel();

								int len = 0;
								while (writeBuffer.hasRemaining()) {
									len = sc.write(writeBuffer);
									/* ˵���ײ��socketд�������� */
									if (len == 0) {
										break;
									}
								}

								writeBuffer.compact();

								/* ˵������ȫ��д�뵽�ײ��socketд������ */
								if (len != 0) {
									/* ȡ��ͨ����д�¼� */
									key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
								}

							}
						} catch (IOException e) {
							System.out.println("service encounter client error");
							/* ���ͻ������ӳ����쳣����Seletcor���Ƴ����key */
							key.cancel();
							key.channel().close();
						}

					}

					Thread.sleep(rnd.nextInt(500));
				}

			} catch (InterruptedException e) {
				System.out.println("serverThread is interrupted");
			} catch (IOException e1) {
				System.out.println("serverThread selecotr error");
			} finally {
				try {
					selector.close();
				} catch (IOException e) {
					System.out.println("selector close failed");
				} finally {
					System.out.println("server close");
				}
			}

		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		Thread thread = new Thread(new TCPEchoServer(8081));
		thread.start();
		Thread.sleep(100000);
		/* �����������߳� */
		thread.interrupt();
	}
}
