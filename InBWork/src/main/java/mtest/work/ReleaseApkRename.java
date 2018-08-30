package mtest.work;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReleaseApkRename {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("�ϳ� 1", "shouhu");
		ReleaseApkRename.multiRename("F:\\�ļ����޸�", replacementChain);
	}
	public static void multiRename(String path, ReplacementChain replacementChain) {
		File file = new File(path);
		boolean isDirectory = file.isDirectory();

		/** ��������ļ��У��ͷ���* */
		if (!isDirectory) {
			System.out.println(path + "����һ���ļ��У�");
			return;
		}
		
		String[] files = file.list();
		File f = null;
		String filename = "";
		String oldFileName = ""; // ֮ǰ������
		/** ѭ�����������ļ�* */
		for (String fileName : files) {
			
			oldFileName = fileName;
			System.out.println(oldFileName);
			Map<String, String> map = replacementChain.getMap();
			for (Entry<String, String> entry : map.entrySet()) {
				fileName = fileName.replace(entry.getKey(), entry.getValue());
			}

			f = new File(path + "\\" + oldFileName); // �����ַ��ԭ·������һ��
			f.renameTo(new File(path + "\\" + fileName));
		}
		System.out.println("��ϲ�������������ɹ���");
	}

}