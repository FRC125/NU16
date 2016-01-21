package com.nutrons.stronghold;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
	// PWM
	public static int LEFT_DRIVE = 4;
	public static int RIGHT_DRIVE = 3;
	public static int LEFT_DRIVE_ENCODER_A = 5;
	public static int LEFT_DRIVE_ENCODER_B = 6;
	public static int RIGHT_DRIVE_ENCODER_A = 4;
	public static int RIGHT_DRIVE_ENCODER_B = 3;
	
	// OI
	public static int DRIVER_PAD = 0;
}
