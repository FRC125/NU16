package com.nutrons.lib.utils;

/**
 * 
 * @author Lydia Xing
 *
 */

public class Utils {

	public static double deadBand(double input, double deadband) {
		if (input > 1.0) {
			input = 1.0;
		} else if (input < -1.0) {
			input = -1.0;
		} else if (Math.abs(input) <= deadband) {
			input = 0.0;
		} else {
			input = 0.0;
		}

		return input;
	}
}