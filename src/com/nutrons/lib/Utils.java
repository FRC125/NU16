package com.nutrons.lib;

public class Utils {
	public static double getOffsetAngle(double centerX, double fieldOffView, double cameraWidth){
		double slope = fieldOffView / cameraWidth;
		double intercept = fieldOffView/ 2;
		return (centerX * slope) - intercept;
		
	}
	public static double getAngle(double aspectRatio){
		double intercept = -2.9628;
		double slope = 4.7953;
	    return Math.toDegrees(Math.atan(slope * aspectRatio + intercept));			
	}
	public static double getDistance(double centerHeight){
		return (0.0157 * centerHeight) + 4.2257;
	}
	public static double getOffSetDistance(double angle, double distance){
		return distance*Math.cos(angle);
	}
	 
	public static double deadband(double value, double deadband, double center) {
        return (value < (center + deadband) && value > (center - deadband)) ? center : value;
	}
}
