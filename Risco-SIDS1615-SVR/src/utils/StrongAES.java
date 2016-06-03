package utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class StrongAES {

    public static String encrypt(String text){
    String result = "NULL";
      try {
         String key = "Javali1234doMont"; // 128 bit key

         // Create key and cipher
         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
         Cipher cipher = Cipher.getInstance("AES");

         // encrypt the text
         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
         byte[] encrypted = cipher.doFinal(text.getBytes());
         
         return new String(encrypted);
         
      }catch(Exception e) {
         e.printStackTrace();
      }
      return result;
    }
    
    
    public static String decrypt(String todecrypt){
	    String result = "NULL";
	      try {
	         String key = "Javali1234doMont"; // 128 bit key
	
	         // Create key and cipher
	         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	         Cipher cipher = Cipher.getInstance("AES");
	         
	         byte[] encrypted = todecrypt.getBytes();
	         // decrypt the text
	         cipher.init(Cipher.DECRYPT_MODE, aesKey);
	         String decrypted = new String(cipher.doFinal(encrypted));
	         
	         return decrypted;
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	    }

}