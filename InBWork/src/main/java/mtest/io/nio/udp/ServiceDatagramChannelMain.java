package mtest.io.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/*�������˴��루��������ֻ��һ��ͨ������Ӧһ������������һ��д������������ʹ�÷�������ʽ���׷������ݻ��ң�*/
public class ServiceDatagramChannelMain {

	public static class UDPEchoService implements Runnable {

		private int port;

		public UDPEchoService(int port) {
			this.port = port;
		}

		@Override
		public void run() {

			ByteBuffer readBuffer = ByteBuffer.allocate(256);
			ByteBuffer writeBuffer = ByteBuffer.allocate(256);

			DatagramChannel dc = null;

			try {

				/* ��������ʹ��Ĭ�ϵ�����IO�ķ�ʽ */
				dc = DatagramChannel.open();
				dc.bind(new InetSocketAddress(port));

				System.out.println("service start");
				while (!Thread.currentThread().isInterrupted()) {
					try {

						/* �ȶ�ȡ�ͻ��˷��͵���Ϣ��ֱ����ȡ����Ϣ�Ż᷵�� */
						/* ֻ�ܵ���receive��������Ϊ��֪���ĸ���ַ������������Ϣ��û��ʵ�ֵ���connect���� */
						/* dc�������ģ�����receive����Ҫ�ȵ����յ����ݲŷ��� */
						SocketAddress clientAddress = dc.receive(readBuffer);
						readBuffer.flip();
						CharBuffer charBuffer = Charset.defaultCharset().decode(readBuffer);
						System.out.println(charBuffer.array());

						/*
						 * ����send������ͻ��˷��͵���Ϣ�� dc��������,����ֱ��send����������ȫ��д�뵽socket�������ŷ���
						 */
						writeBuffer.put("echo : ".getBytes());
						readBuffer.rewind();
						writeBuffer.put(readBuffer);
						writeBuffer.flip();
						dc.send(writeBuffer, clientAddress);

						readBuffer.clear();
						writeBuffer.clear();

					} catch (IOException e) {
						System.out.println("receive from or send to client failed");
					}
				}
			} catch (IOException e) {
				System.out.println("server error");
			} finally {
				try {
					if (dc != null) {
						dc.close();
					}
				} catch (IOException e) {

				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Thread(new UDPEchoService(8082)).start();
	}
}
