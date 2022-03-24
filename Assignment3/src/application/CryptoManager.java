package application;

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		for(int i = 0; i < plainText.length(); i++)
		{
			if ((int) plainText.charAt(i) < (int)LOWER_BOUND || (int) plainText.charAt(i) > (int)UPPER_BOUND)
			{
				return false;
				
			}
			
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryptedText = "";
		
		for (int i = 0; i < plainText.length(); i++)
		{
			int newKey = (int)plainText.charAt(i) + key;
			if(newKey > UPPER_BOUND)
			{
				while (newKey > UPPER_BOUND)
				{
					newKey -= RANGE;
				}
				char combinedString = (char)newKey;
				encryptedText += combinedString;
			}
			else 
			{
				encryptedText += (char)newKey;
			}
		}
		
		return encryptedText;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		int i = 0;
		String newKey = "";
		String encryptedKey = "";
		do
		{
		
			char newChar = bellasoStr.charAt(i);
			String combinedWord = newKey + newChar;
			newKey = combinedWord;
			i++;
			if( i == bellasoStr.length())
			{
				i = 0;
			}
		}
		
		while (newKey.length() < plainText.length());
		System.out.println(newKey);
		for(int k = 0; k < newKey.length(); k++)
		{
			int stringValue = ((int) plainText.charAt(k) + (int) newKey.charAt(k));
			while(stringValue > UPPER_BOUND)
			{
				stringValue -= RANGE;
			}
			String combinedWord = encryptedKey + Character.toString(stringValue);
			encryptedKey = combinedWord;
		}
		System.out.println(newKey);
		return encryptedKey;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	
	public static String decryptCaesar(String encryptedText, int key) {
		
		String decryptedString = "";
		
		for (int i = 0; i < encryptedText.length(); i++)
		{
			int currentKey = (int)encryptedText.charAt(i);
			if(encryptedText.charAt(i) == 71)
			{
				decryptedString += " ";
			}
			else if((encryptedText.charAt(i) - key) <= LOWER_BOUND)
			{
				while (((int)encryptedText.charAt(i) + key) > currentKey)
				{
					currentKey += RANGE;
				}
				currentKey -= key;
				decryptedString += (char)currentKey;
			}
			else {
				decryptedString += (char)(encryptedText.charAt(i) - key);
			}
		}
		return decryptedString;
	
	}


	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		int i = 0;
		String newKey = "";
		String decryptedKey = "";
		int newAscii;
		do {
			char newChar = bellasoStr.charAt(i);
			String combinedWord = newKey + newChar;
			newKey = combinedWord;
			i++;
			if (i == bellasoStr.length())
			{
				i = 0;
			}
		}
		while (newKey.length() < encryptedText.length());
		
		for (int k = 0; k < encryptedText.length(); k++)
		{
			
				
				 newAscii = ((int) encryptedText.charAt(k) + RANGE) - (int) newKey.charAt(k);
				
			String newChar = Character.toString(newAscii);
			String combinedWord = decryptedKey + newChar;
			decryptedKey = combinedWord;
		}
		return decryptedKey;
	}
}
