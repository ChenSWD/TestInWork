package mtest.work;

// TODO Auto-generated method stub
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 重命名类
 * 
 * @author Jack
 */
public class FileRename {

	public static void main(String[] args) {
//		ReplacementChain replacementChain = new ReplacementChain();
//		replacementChain.addRegulation("_xx.", ".");
//		FileRename.multiRename("F:\\文件名修改", replacementChain);
		//下面給release包重命名
		ReplacementChain replacementChain = new ReplacementChain();
		replacementChain.addRegulation("_unsigned_signed", "");
		FileRename.reNameReleaseApk("F:\\文件名修改", replacementChain,"3Dbobo_V5.12.3.");
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
	//給apk重命名
	public static void reNameReleaseApk(String path,ReplacementChain replacementChain,String addString) {
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
			f.renameTo(new File(path + "\\" + addString + fileName));
		}
		System.out.println("恭喜，批量增加命名成功！");
	}
}

/**
 * 重命名规则类
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

	// 添加新的替换规则(字符串替换)
	public ReplacementChain addRegulation(String oldStr, String newStr) {
		this.map.put(oldStr, newStr);
		return this;
	}

}