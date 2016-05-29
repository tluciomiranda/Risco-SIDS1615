package utils;

public class Utils {
	
	public static String encrypt(String enter){
		
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(enter);
		
	}

}
