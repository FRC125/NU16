package com.nutrons.stronghold;

import com.nutrons.stronghold.commands.drivetrain.ResetEncoderCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	private Joystick driverPad = new Joystick(Robot.robotMap.DRIVER_PAD);
	
	Button resetEncoder = new JoystickButton(driverPad, 1);
	
	public OI() {
		resetEncoder.whenPressed(new ResetEncoderCmd());
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

