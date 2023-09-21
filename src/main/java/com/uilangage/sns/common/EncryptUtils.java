package com.uilangage.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	public static String md5(String message) {
		
		
		String resultString = "";
		try {
			
			MessageDigest md = MessageDigest.getInstance("md5");

			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			
			for(int i = 0; i < digest.length; i++) {
				// 비트연산
				resultString += Integer.toHexString(digest[i] & 0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
		return resultString;
		
	}
	
	
}
