package mtest.work;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReleaseApkRename {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("合成 1", "shouhu");
		ReleaseApkRename.multiRename("F:\\文件名修改", replacementChain);
	}
	public static void multiRename(String path, ReplacementChain replacementChain) {
		File file = new File(path);
		boolean isDirectory = file.isDirectory();

		/** 如果不是文件夹，就返回* */
		if (!isDirectory) {
			System.out.println(path + "不是一个文件夹！");
			return;
		}
		
		String[] files = file.list();
		File f = null;
		String filename = "";
		String oldFileName = ""; // 之前的名字
		/** 循环遍历所有文件* */
		for (String fileName : files) {
			
			oldFileName = fileName;
			System.out.println(oldFileName);
			Map<String, String> map = replacementChain.getMap();
			for (Entry<String, String> entry : map.entrySet()) {
				fileName = fileName.replace(entry.getKey(), entry.getValue());
			}

			f = new File(path + "\\" + oldFileName); // 输出地址和原路径保持一致
			f.renameTo(new File(path + "\\" + fileName));
		}
		System.out.println("恭喜，批量重命名成功！");
	}

}