package com.nutrons.stronghold;

import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
    
	private Joystick driverPad = new Joystick(RobotMap.DRIVER_PAD);
	
	public OI() {
		
	}
	
	/**
	 * Gets the y axis value of the left joystick
	 * @return Y axis value
	 */
	public double getLeftJoystickY() {
		return -driverPad.getRawAxis(1);
	}
	
	/**
	 * Gets the y axis value of the right joystick
	 * @return Y axis value
	 */
	public double getRightJoystickY() {
		return -driverPad.getRawAxis(5);
	}
}