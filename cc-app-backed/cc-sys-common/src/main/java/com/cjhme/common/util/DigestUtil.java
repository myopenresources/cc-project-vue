package com.cjhme.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 
 * <p>
 * Title: DigestUtil.java
 * </p>
 * Description: MD5加密
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class DigestUtil {
	/**
	 * 获得字符串的加密值
	 * 
	 * @param psw
	 * @return
	 */
	public static String md5(String psw) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] byts = md.digest(psw.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return toHex(base64.encode(byts).getBytes());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(md5("sysadmin"));
	}

}
