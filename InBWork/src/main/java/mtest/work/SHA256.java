package mtest.work;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-256");
			Integer number = 1;
			while (flag) {
				number++;
				byte[] digestByte = digest.digest(number.toString().getBytes());
				StringBuilder buf = new StringBuilder();
				for (int i = 0; i < digestByte.length; i++) {
					String tempStr = Integer.toHexString(digestByte[i] & 0xff);
					// System.out.println(tempStr + " byte = " +
					// Integer.toBinaryString(digestByte[i]) + " b = " +
					// digestByte[i]);
					if (tempStr.length() == 1) {
						buf.append("0").append(tempStr);
					} else {
						buf.append(tempStr);
					}
				}
				if (buf.toString().toLowerCase().substring(0,4).equals("a8c0")) {
					flag = false;
					System.out.println(buf.toString().toLowerCase() + " number = " + number + " ³É¹¦");
				} else {
					System.out.println(buf.toString().toLowerCase() + " number = " + number + " Ê§°Ü");
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
