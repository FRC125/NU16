package com.nutrons.lib;

/**
 * 
 * @author John Zhang
 *
 */

public class Utils {
	
	public static double deadband(double value, double deadband, double center) {
        return (value < (center + deadband) && value > (center - deadband)) ? center : value;
	}
}
