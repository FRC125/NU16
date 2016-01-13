package com.nutrons.stronghold;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	private Joystick driverPad = new Joystick(Robot.robotMap.DRIVER_PAD);
	
	public OI() {
		
	}
	
	public double getLeftJoystickY() {
		return -driverPad.getRawAxis(1);
	}
	
	public double getRightJoystickY() {
		return -driverPad.getRawAxis(5);
	}
	
}

