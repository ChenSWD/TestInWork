package mtest.work.mutlibuild;
//����ļ�·������ʵ��
public class FileConfigEntity {
	//���ĸ��ļ�������copy����
	private String inputDirPath = ""; 
	//ͳһcopy���ĸ��ļ������棬Ĭ�ϵ�ǰ�ļ��е�release�ļ�������
	private String outputDirPath = "channel";
	//�������������ļ�
	private String intputFilePath = "release.txt";
	
	public String getInputDirPath() {
		return inputDirPath;
	}
	public void setInputDirPath(String inputDirPath) {
		this.inputDirPath = inputDirPath;
	}
	public String getOutputDirPath() {
		return outputDirPath;
	}
	public void setOutputDirPath(String outputDirPath) {
		this.outputDirPath = outputDirPath;
	}
	public String getIntputFilePath() {
		return intputFilePath;
	}
	public void setIntputFilePath(String intputFilePath) {
		this.intputFilePath = intputFilePath;
	}
}
