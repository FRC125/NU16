package com.nutrons.lib.utils;

/**
 * 
 * @author Lydia Xing, Camilo Gonzalez
 *
 */

public class Utils {

	public static double deadband(double value, double deadband, double center) {
        return (value < (center + deadband) && value > (center - deadband)) ? center : value;
    }
}