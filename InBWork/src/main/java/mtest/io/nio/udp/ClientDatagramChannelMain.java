package mtest.io.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;

import mtest.io.nio.Buffers;

public class ClientDatagramChannelMain {

	public static class UDPEchoClient implements Runnable {

		private String name;
		private InetSocketAddress serviceAddress;

		public UDPEchoClient(String name, InetSocketAddress serviceAddress) {
			this.name = name;
			this.serviceAddress = serviceAddress;
		}

		@Override
		public void run() {
			DatagramChannel dc = null;
			try {

				/*
				 * ÿ��ʵ���Ͽ��Դ������ͨ������ͬһ����������ַ�� ��������Ϊ����ʾ���㣬ֻ������һ��ͨ��
				 */
				dc = DatagramChannel.open();

				/* �ͻ��˲��÷�����ģʽ */
				dc.configureBlocking(false);

				/*
				 * ��������Ӳ���ָTCP���������ӣ���ΪUDPЭ�鱾����Ҫ���ӣ� �������ӵ���˼�������ǰ�����ϵͳ����ñ��ض˿ںţ��Լ����ٲ���ϵͳҪ���͵�Ŀ��
				 * ���Ӻ��UDPͨ��������߷��͵�Ч�ʣ������Ե���read��write�������պͷ�������
				 * δ���ӵ�UDPͨ��ֻ�ܵ���receive��send�������պͷ�������
				 */
				dc.connect(serviceAddress);

				Selector selector = Selector.open();
				int interest = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
				dc.register(selector, interest, new Buffers(256, 256));

				int i = 0;
				while (!Thread.currentThread().isInterrupted()) {

					selector.select();

					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					while (it.hasNext()) {

						SelectionKey key = it.next();
						it.remove();

						Buffers buffers = (Buffers) key.attachment();

						ByteBuffer readBuffer = buffers.getReadBuffer();
						ByteBuffer writeBuffer = buffers.gerWriteBuffer();

						try {

							if (key.isReadable()) {
								dc.read(readBuffer);
								readBuffer.flip();
								CharBuffer charBuffer = Charset.defaultCharset().decode(readBuffer);
								System.out.println(charBuffer.array());
								readBuffer.clear();
							}

							if (key.isWritable()) {
								writeBuffer.put((name + (i++)).getBytes());
								writeBuffer.flip();
								dc.write(writeBuffer);
								writeBuffer.clear();

								Thread.sleep(500);
							}

						} catch (IOException e) {
							key.cancel();
							key.channel().close();
						}
					}
				}
			} catch (InterruptedException e) {
				System.out.println(name + "interrupted");
			} catch (IOException e) {
				System.out.println(name + "encounter connect error");
			} finally {
				try {
					dc.close();
				} catch (IOException e) {
					System.out.println(name + "encounter close error");
				} finally {
					System.out.println(name + "closed");
				}
			}
		}
	}

	public static void main(String[] args) {

		InetSocketAddress serviceAddress = new InetSocketAddress("127.0.0.1", 8082);

		UDPEchoClient clientA = new UDPEchoClient("thread a ", serviceAddress);
		UDPEchoClient clientB = new UDPEchoClient("thread b ", serviceAddress);
		UDPEchoClient clientC = new UDPEchoClient("thread c ", serviceAddress);

		new Thread(clientA).start();
		new Thread(clientB).start();
		new Thread(clientC).start();

	}
}
