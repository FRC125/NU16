package com.nutrons.stronghold;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOffCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class OI {
	
	// Joysticks
	private Joystick driverPad = new Joystick(0);
	
	// Buttons
	private Button testButton = new JoystickButton(driverPad, 1);
	
	public OI() {
		testButton.whenPressed(new TurnLightOffCmd());
		testButton.whenReleased(new TurnLightOnCmd());
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.05, 0.0);
	}
}