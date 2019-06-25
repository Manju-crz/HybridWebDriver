package com.mks.utilizer;

import java.util.Random;

/**
 * A utility to generate random data
 * 
 * @author Manjunath KS
 *
 */
public class Randomization {

	/**
	 * Get a Random Bounded Integer between -X and X where X is the upper bound.
	 * 
	 * @param bound
	 *            - positive upper bound
	 * @author Manjunath KS
	 * @return
	 */
	public static int getRandomBoundedInteger(int bound) {
		return getRandomBoundedInteger(bound, false);
	}

	/**
	 * Get a Random Bounded Integer between -X and X where X is the upper bound.
	 * 
	 * @param bound
	 *            - positive upper bound
	 * @param negatives
	 *            - true returns negative/zero/positive values, false returns
	 *            zero/positive values
	 * @author Manjunath KS
	 * @return
	 */
	public static int getRandomBoundedInteger(int bound, boolean negatives) {
		Random ranInt = new Random();
		int value = ranInt.nextInt(bound);
		return negatives ? value : value & Integer.MAX_VALUE; // zero out the sign bit
	}

	/**
	 * Get a Random Integer
	 * 
	 * @return
	 */
	public static int getRandomInteger() {
		return getRandomInteger(true);
	}

	/**
	 * Get a Random Integer
	 * 
	 * @param negatives
	 *            - true returns negative/zero/positive values, false returns
	 *            zero/positive values
	 * @author Manjunath KS
	 * @return
	 */
	public static int getRandomInteger(boolean negatives) {
		Random ranInt = new Random();
		int value = ranInt.nextInt();
		return negatives ? value : value & Integer.MAX_VALUE; // zero out the sign bit
	}

	/**
	 * This method helps to generate random string of alphabets, of required length.
	 * 
	 * @param length
	 *            - total required characters to generate a random string.
	 * @return Returns the random generated alphabetical string
	 * @author Manjunath KS
	 */
	public static String getRandomAlphabets(final int length) {
		String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = getRandomBoundedInteger(51, false);
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
}
