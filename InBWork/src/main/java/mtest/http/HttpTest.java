package mtest.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//用来测试http请求相关
public class HttpTest {

	public static void main(String[] args) throws IOException   {
		//调用catchPic方法从网络上下载一张图片  
        catchPic();  
	}
	
	public static void catchPic() throws IOException   
    {  
              
            //创建URL对象，参数传递一个String类型的URL解析地址  
            URL url = new URL("http://img04.tooopen.com/images/20131202/sy_49706261893.jpg");  
            //HttpURLConnection是protected修饰的，无法直接new对象  
            //url的openConnection方法返回值是URLConnection类的对象，需要强转为HttpURLConnection  
            //所以直接通过url调用openConnection方法来实现创建HttpURLConnection的对象  
             HttpURLConnection huc =(HttpURLConnection) url.openConnection();  
            //设置请求方式  
            huc.setRequestMethod("GET");  
            int code=0;  
            // 从 HTTP 响应消息获取状态码  
            //200表示ok，500表示服务器错误，404表示找不到  
             code =huc.getResponseCode();  
            //如果状态码为200，表示连接ok，可以继续操作  
            if(code==200)  
            {  
                //获取输入流  
                InputStream ips = huc.getInputStream();  
                  
                //采用高效输入流获取网络上的资源  
                BufferedInputStream bis=new BufferedInputStream(ips);  
                BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream("e://meitu.jpg"));  
                byte[] b=new byte[1024];  
                int len=0;  
                //将数据写入文件  
                while((len=bis.read(b))!=-1)  
                {  
                	System.out.println("len = "+len);
                    bos.write(b,0,len);  
                    //刷新资源------write方法使用完需要刷新  
                    bos.flush();  
                }  
                //关闭流，释放资源  
                bos.close();  
                bis.close();  
                ips.close();  
            }  
    }  
}
