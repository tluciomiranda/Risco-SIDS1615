package utils;

public class Utils {
	
	public static String encrypt(String enter)
	{		
		return org.apache.commons.codec.digest.DigestUtils.sha256Hex(enter);
		
	}
	
	public static void So(Object obj){
		System.out.println(obj);
	}
}
