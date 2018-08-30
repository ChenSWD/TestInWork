package mtest.work.mutlibuild;

import java.util.Scanner;

//�����������·��
public class PathRead {
	FileConfigEntity entity = new FileConfigEntity();

	public void read() {
		Scanner in = new Scanner(System.in);
		System.out.print("������·����");
		String inputDirPath = in.nextLine();
		entity.setInputDirPath(inputDirPath.trim());
		System.out.print("���·��(Ĭ���ڵ�ǰ·��)��");
		String outputDirPath = in.nextLine();
		if (!isEmpty(outputDirPath)) {
			entity.setOutputDirPath(outputDirPath.trim());
		}

		System.out.print("���������ļ�·��(Ĭ��Ϊ��ǰ·���µ�release.txt�ļ�)��");
		String inputFilePath = in.nextLine();
		if (!isEmpty(inputFilePath)) {
			entity.setIntputFilePath(inputFilePath.trim());
		}
	}

	private boolean isEmpty(String path) {
		return path == null || path.length() <= 0;
	}

	public FileConfigEntity getConfigInfo() {
		return entity;
	}
}
