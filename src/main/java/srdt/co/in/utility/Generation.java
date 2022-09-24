package srdt.co.in.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class Generation {
	 private static String dtpatern = "yyyy-MM-dd HH:mm:ss.SSS";
	 public static String generatePassword(int length) {
		 
		    String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	        StringBuilder returnValue = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(new Random().nextInt(ALPHABET.length())));
	        }
	        return new String(returnValue);
	    }
	 
	 public static String generatePassword() 
	 {
		 int length = 10;
		 String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	     StringBuilder returnValue = new StringBuilder(length);

	     for (int i = 0; i < length; i++) {
	         returnValue.append(ALPHABET.charAt(new Random().nextInt(ALPHABET.length())));
	     }
	     return new String(returnValue);
	 }
	 
	 
	public static Date getCurrentDate()
	{
		String dtstr = LocalDateTime.now().format(DateTimeFormatter.ofPattern(dtpatern));
		DateFormat dateFormat = new SimpleDateFormat(dtpatern);
		Date date = null;
		try {
			date = dateFormat.parse(dtstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;		
	}
	 public static String getDefaultPwd()
	 {
		 return "Qwe@123";
	 }
	 public static String getSecretKey()
	 {
		 return "San@123456789012";
	 }
}
