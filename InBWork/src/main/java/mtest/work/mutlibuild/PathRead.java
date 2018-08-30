package mtest.work.mutlibuild;

import java.util.Scanner;

//读入相关配置路径
public class PathRead {
	FileConfigEntity entity = new FileConfigEntity();

	public void read() {
		Scanner in = new Scanner(System.in);
		System.out.print("渠道包路径：");
		String inputDirPath = in.nextLine();
		entity.setInputDirPath(inputDirPath.trim());
		System.out.print("输出路径(默认在当前路径)：");
		String outputDirPath = in.nextLine();
		if (!isEmpty(outputDirPath)) {
			entity.setOutputDirPath(outputDirPath.trim());
		}

		System.out.print("渠道配置文件路径(默认为当前路径下的release.txt文件)：");
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
