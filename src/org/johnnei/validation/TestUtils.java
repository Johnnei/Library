package org.johnnei.validation;

public class TestUtils {
	
	/**
	 * Tests if the string is a valid 32-bit integer
	 * @param string
	 * @return
	 */
	public static boolean isInt(String string) {
		return isInt(string, 10);
	}
	
	/**
	 * Tests if the string is a valid 32-bit integer
	 * @param string
	 * @param radix the base of the number (default 10)
	 * @return
	 */
	public static boolean isInt(String string, int radix) {
		try {
			Integer.parseInt(string, radix);
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

}
