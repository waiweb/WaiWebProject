package utils;

import java.security.MessageDigest;

import jndi.JndiFactory;

import org.apache.log4j.Logger;

public class Tool_Security {
	
	static final String hashAlgorithm = "SHA-512";
	static final Integer hashLength = 64;
	private static Logger log = Logger.getLogger(JndiFactory.class);   

		
	
	public static byte[] hashFromString(String data) {
		byte[] hash = null;
		
		try {
			MessageDigest md;
			md = MessageDigest.getInstance(hashAlgorithm);
			hash = new byte[hashLength];
			md.update(data.getBytes("UTF-8"), 0, data.length());
			hash = md.digest();
		} catch(Exception e) {
			log.error("Error: "+e.getMessage());

			e.printStackTrace();
		}
		
		return hash;
	}
	

}
