package mtest.work.mutlibuild;
//相关文件路径配置实体
public class FileConfigEntity {
	//从哪个文件夹下面copy出来
	private String inputDirPath = ""; 
	//统一copy到哪个文件夹下面，默认当前文件夹的release文件夹下面
	private String outputDirPath = "channel";
	//多渠道的配置文件
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
