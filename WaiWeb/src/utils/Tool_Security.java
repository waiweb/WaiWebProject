package utils;

import java.security.MessageDigest;

public class Tool_Security {
	
	static final String hashAlgorithm = "SHA-512";
	static final Integer hashLength = 64;
		
	
	public static byte[] hash(String data) {
		byte[] hash = null;
		
		try {
			MessageDigest md;
			md = MessageDigest.getInstance(hashAlgorithm);
			hash = new byte[hashLength];
			md.update(data.getBytes("UTF-8"), 0, data.length());
			hash = md.digest();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return hash;
	}

}
