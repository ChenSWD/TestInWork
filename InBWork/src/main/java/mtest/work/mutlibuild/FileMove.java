package mtest.work.mutlibuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

//移动文件路径
public class FileMove {

	FileConfigEntity entity;

	public FileMove(FileConfigEntity entity) {
		this.entity = entity;
	}

	public void startFileMove() {
		if (!checkFileLegal(entity.getIntputFilePath())) {
			return;
		}
		List<String> channelList = new ArrayList<>();
		try {
			channelList = getChannelListFromFile(entity.getIntputFilePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------" + "复制开始" + "----------------------");
		for (String path : channelList) {
			// System.out.println(path);
			copyFile(entity.getInputDirPath() + "\\" + path.trim() + "\\release",
					entity.getOutputDirPath() + "\\release");
		}
		System.out.println("----------------------" + "复制结束" + "----------------------");
	}

	/**
	 * 读取配置文本 一行一行读取
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private List<String> getChannelListFromFile(String path) throws IOException {
		// 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		// 防止路径乱码 如果utf-8 乱码 改GBK eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// 如果 t x t文件里的路径 不包含---字符串 这里是对里面的内容进行一个筛选
			if (line.lastIndexOf("---") < 0) {
				list.add(line);
			}
		}
		br.close();
		isr.close();
		fis.close();
		return list;
	}

	// 遍历文件夹里面的apk文件，并开始复制
	private void copyFile(String from, String to) {
		// 判断release文件夹是否存在，不存在创建
		File toDir = new File(to);
		if (!toDir.exists()) {
			toDir.mkdirs();
		}
		// 输入的是一个文件夹路径
		if (checkDirectoryLegal(from)) {
			File fFile = new File(from);
			for (File in : fFile.listFiles()) {
				// System.out.println(
				// "fPath = " + in.getAbsolutePath() + " name = " + in.getName() + " p = " +
				// in.getPath());
				if (in.getPath().endsWith(".apk") && checkFileLegal(in)) {
					File out = new File(to + "\\" + in.getName());
					try {
						copyFileUsingFileStreams(in, out);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 从流中复制
	private void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		System.out.println("开始复制文件：" + source.getName());
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
			System.out.println("结束复制文件：" + source.getName());
		}
	}

	private boolean checkFileLegal(String path) {
		File iFile = new File(path);
		if (iFile != null && iFile.isFile()) {
			return true;
		}
		return false;
	}

	private boolean checkDirectoryLegal(String path) {
		File iDir = new File(path);
		if (iDir != null && iDir.isDirectory()) {
			return true;
		}
		return false;
	}

	private boolean checkFileLegal(File iFile) {
		if (iFile != null && iFile.isFile()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean checkDirectoryLegal(File iDir) {
		if (iDir != null && iDir.isDirectory()) {
			return true;
		}
		return false;
	}
}
