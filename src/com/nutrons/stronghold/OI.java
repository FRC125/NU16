package com.nutrons.stronghold;

import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	private Joystick driverPad = new Joystick(Robot.robotMap.DRIVER_PAD);
	
	public OI() {
		
	}
	
	/**
	 * Gets value of left joystick and y axis.
	 * @return Y axis value
	 */
	public double getLeftJoystickY() {
		return -driverPad.getRawAxis(1);
	}
	
	/**
	 * Gets value of y axis of right joystick. 
	 * @return Y axis value.
	 */
	public double getRightJoystickY() {
		return -driverPad.getRawAxis(5);
	}
}

