package com.nutrons.stronghold;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOffCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import com.nutrons.stronghold.commands.shooter.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.shooter.MoveArmToPositionCmd;

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
	public Joystick driverPad = new Joystick(0);
	
	// Buttons
	private Button testButton = new JoystickButton(this.driverPad, 1);
	private Button quickTurnButton = new JoystickButton(this.driverPad, 5);
	
	public OI() {
		testButton.whenPressed(new MoveArmToPositionCmd());
		
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.05, 0.0);
	}
	
	public boolean getQuickTurn() {
		return this.getQuickTurn();
	}
	
	public double getrightJoystickX() {
		return Utils.deadband(this.driverPad.getRawAxis(4), 0.05, 0.0);
	}
}