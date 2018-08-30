package mtest.work.mutlibuild;

//统一化多渠道打包后的包的工具：统一名称，将多渠道apk文件复制到统一目录下面
public class ChannelMain {

	public static void main(String[] args) {
		PathRead read = new PathRead();
		read.read();
		FileMove move = new FileMove(read.getConfigInfo());
		move.startFileMove();
	}
}
