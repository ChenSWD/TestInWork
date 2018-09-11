package mtest.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileStream {

	public static void main(String[] args) {
		File file = new File("src\\main\\java\\mtest\\io\\package-info.java");
		FileInputStream in = null;
		InputStreamReader reader = null;
		BufferedReader bReader = null;
		try {
			in = new FileInputStream(file);
			reader = new InputStreamReader(in, "gbk");
			bReader = new BufferedReader(reader);
			String line;
			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
				reader.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
