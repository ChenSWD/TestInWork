package mtest.work.mutlibuild;

//ͳһ�������������İ��Ĺ��ߣ�ͳһ���ƣ���������apk�ļ����Ƶ�ͳһĿ¼����
public class ChannelMain {

	public static void main(String[] args) {
		PathRead read = new PathRead();
		read.read();
		FileMove move = new FileMove(read.getConfigInfo());
		move.startFileMove();
	}
}
