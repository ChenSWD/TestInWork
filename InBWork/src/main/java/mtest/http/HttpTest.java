package mtest.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//��������http�������
public class HttpTest {

	public static void main(String[] args) throws IOException   {
		//����catchPic����������������һ��ͼƬ  
        catchPic();  
	}
	
	public static void catchPic() throws IOException   
    {  
              
            //����URL���󣬲�������һ��String���͵�URL������ַ  
            URL url = new URL("http://img04.tooopen.com/images/20131202/sy_49706261893.jpg");  
            //HttpURLConnection��protected���εģ��޷�ֱ��new����  
            //url��openConnection��������ֵ��URLConnection��Ķ�����ҪǿתΪHttpURLConnection  
            //����ֱ��ͨ��url����openConnection������ʵ�ִ���HttpURLConnection�Ķ���  
             HttpURLConnection huc =(HttpURLConnection) url.openConnection();  
            //��������ʽ  
            huc.setRequestMethod("GET");  
            int code=0;  
            // �� HTTP ��Ӧ��Ϣ��ȡ״̬��  
            //200��ʾok��500��ʾ����������404��ʾ�Ҳ���  
             code =huc.getResponseCode();  
            //���״̬��Ϊ200����ʾ����ok�����Լ�������  
            if(code==200)  
            {  
                //��ȡ������  
                InputStream ips = huc.getInputStream();  
                  
                //���ø�Ч��������ȡ�����ϵ���Դ  
                BufferedInputStream bis=new BufferedInputStream(ips);  
                BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream("e://meitu.jpg"));  
                byte[] b=new byte[1024];  
                int len=0;  
                //������д���ļ�  
                while((len=bis.read(b))!=-1)  
                {  
                	System.out.println("len = "+len);
                    bos.write(b,0,len);  
                    //ˢ����Դ------write����ʹ������Ҫˢ��  
                    bos.flush();  
                }  
                //�ر������ͷ���Դ  
                bos.close();  
                bis.close();  
                ips.close();  
            }  
    }  
}
