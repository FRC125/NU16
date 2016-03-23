package com.nutrons.stronghold;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class RobotMap {

	// CAN
	public static final int LEFT_DRIVE_MOTOR_A = 4;
	public static final int LEFT_DRIVE_MOTOR_B = 5;
	public static final int RIGHT_DRIVE_MOTOR_A = 2;
	public static final int RIGHT_DRIVE_MOTOR_B = 1;
	public static final int ARM_1_MOTOR = 7;
	public static final int ARM_2_MOTOR = 6;
	public static final int INTAKE_MOTOR = 3;
	
	// PWM
	public static final int CDF_ARM = 0;
	
	// Digital
	public static final int ZERO_BUTTON = 0;
	public static final int LEFT_DRIVE_LIGHT_BACK = 5;
	public static final int RIGHT_DRIVE_LIGHT_BACK = 2;
	public static final int LEFT_DRIVE_LIGHT_FRONT = 3;
	public static final int RIGHT_DRIVE_LIGHT_FRONT = 4;
	public static final int VISION_LIGHT = 9;
	
	// Solenoids
	public static final int SHOOTER1_A = 0;
	public static final int SHOOTER1_B = 6;
	public static final int SHOOTER2_A = 1;
	public static final int SHOOTER2_B = 7;
	public static final int SHOOTER3 = 2;
	public static final int SHOOTER4 = 3;
	public static final int JAW_A = 4;
	public static final int JAW_B = 5;
	
	// Analog Input
	public static final int ULTRASONIC_RX = 0;
	public static final int ULTRASONIC_TX = 1;
	
	// Relays
	public static final int LIGHT_RELAY = 3;

	// Robot and Field Dimensions
	public static final double TARGET_WIDTH = 14.0;
	// Camera and GRIP
	public static final double CAMERA_PIXEL_WIDTH = 640.0;
	public static final double CAMERA_PIXEL_HEIGHT = 480.0;
	public static final double CAMERA_FOV = 54.0;
	public static final double CAMERA_VERTICAL_FOV = CAMERA_FOV * CAMERA_PIXEL_HEIGHT / CAMERA_PIXEL_WIDTH;
	public static final double GRIP_IGNORE_VALUE = AngleCalculator.getHorizontalCameraAngle(0);
	
	public static final double CAMERA_X_OFFSET = -7.0;
	public static final double CAMERA_Y_OFFSET = 0;
}