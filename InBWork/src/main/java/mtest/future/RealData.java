package mtest.future;

public class RealData  implements IData{
	 private String result;
	    RealData(String str){
	        StringBuilder sb = new StringBuilder();
	        //����һ���൱��ʱ��Զ�̷���
	        for (int i = 0; i < 20; i++) {
	            sb.append("i").append(i);
	            try {
	                Thread.sleep(200);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        result = sb.append(str).toString();
	    }

	    @Override
	    public String getResult() {
	        return result;
	    }
}
