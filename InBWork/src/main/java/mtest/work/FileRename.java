package mtest.work;

// TODO Auto-generated method stub
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ��������
 * 
 * @author Jack
 */
public class FileRename {

	public static void main(String[] args) {
//		ReplacementChain replacementChain = new ReplacementChain();
//		replacementChain.addRegulation("_xx.", ".");
//		FileRename.multiRename("F:\\�ļ����޸�", replacementChain);
		//����orelease��������
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("_unsigned_signed", "");
		FileRename.reNameReleaseApk("F:\\�ļ����޸�", replacementChain,"3Dbobo_V5.12.3.");
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
	//�oapk������
	public static void reNameReleaseApk(String path,ReplacementChain replacementChain,String addString) {
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
			f.renameTo(new File(path + "\\" + addString + fileName));
		}
		System.out.println("��ϲ���������������ɹ���");
	}
}

/**
 * ������������
 * 
 * @author jack
 */
class ReplacementChain {
	private Map<String, String> map;

	public ReplacementChain() {
		this.map = new HashMap<String, String>();
	}

	public Map<String, String> getMap() {
		return map;
	}

	// ����µ��滻����(�ַ����滻)
	public ReplacementChain addRegulation(String oldStr, String newStr) {
		this.map.put(oldStr, newStr);
		return this;
	}

}