package com.nutrons.stronghold;

import com.nutrons.lib.Utils;

import edu.wpi.first.wpilibj.Joystick;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	private Joystick driverPad = new Joystick(0);
	
	public OI() {
		
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.05, 0.0);
	}
}