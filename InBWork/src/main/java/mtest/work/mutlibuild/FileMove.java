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

//�ƶ��ļ�·��
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
		System.out.println("----------------------" + "���ƿ�ʼ" + "----------------------");
		for (String path : channelList) {
			// System.out.println(path);
			copyFile(entity.getInputDirPath() + "\\" + path.trim() + "\\release",
					entity.getOutputDirPath() + "\\release");
		}
		System.out.println("----------------------" + "���ƽ���" + "----------------------");
	}

	/**
	 * ��ȡ�����ı� һ��һ�ж�ȡ
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private List<String> getChannelListFromFile(String path) throws IOException {
		// ʹ��һ���ַ����������洢�ı��е�·�� ��Ҳ����String []����
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		// ��ֹ·������ ���utf-8 ���� ��GBK eclipse�ﴴ����txt ��UTF-8���ڵ������Լ�������txt ��GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// ��� t x t�ļ����·�� ������---�ַ��� �����Ƕ���������ݽ���һ��ɸѡ
			if (line.lastIndexOf("---") < 0) {
				list.add(line);
			}
		}
		br.close();
		isr.close();
		fis.close();
		return list;
	}

	// �����ļ��������apk�ļ�������ʼ����
	private void copyFile(String from, String to) {
		// �ж�release�ļ����Ƿ���ڣ������ڴ���
		File toDir = new File(to);
		if (!toDir.exists()) {
			toDir.mkdirs();
		}
		// �������һ���ļ���·��
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

	// �����и���
	private void copyFileUsingFileStreams(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;
		System.out.println("��ʼ�����ļ���" + source.getName());
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
			System.out.println("���������ļ���" + source.getName());
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
