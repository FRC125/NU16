package com.nutrons.stronghold;

import com.nutrons.lib.Utils;
import com.nutrons.stronghold.commands.arm.MoveArmToIntakePositionCmd;
import com.nutrons.stronghold.commands.arm.MoveArmToPositionCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOffCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnLightOnCmd;
import com.nutrons.stronghold.commands.drivetrain.TurnToAngleCmd;
import com.nutrons.stronghold.commands.drivetrain.ZeroGyroCmd;

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
	private Button quickTurnButton = new JoystickButton(this.driverPad, 6);
	private Button fastDrivingModeButton = new JoystickButton(driverPad, 5);
	private Button holdHeadingModeButton = new JoystickButton(driverPad, 1);
	private Button turnToAngleButton = new JoystickButton(driverPad, 3);
	
	public OI() {
		holdHeadingModeButton.whenPressed(new ZeroGyroCmd());
		//turnToAngleButton.whenPressed(new TurnToAngleCmd(180.0));
	}
	
	public double getLeftJoystickY() {
		return Utils.deadband(this.driverPad.getRawAxis(1), 0.005, 0.0);
	}
	
	public double getRightJoystickY() {
	return Utils.deadband(this.driverPad.getRawAxis(5), 0.005, 0.0);
	}
	
	public double getrightJoystickX() {
		return Utils.deadband(this.driverPad.getRawAxis(4), 0.05, 0.0);
	}
	
	public boolean getQuickTurn() {
		return this.quickTurnButton.get();
	}
	
	public boolean getSlowDrivingMode() {
		return this.fastDrivingModeButton.get();
	}
	
	public boolean getHoldHeadingMode() {
		return this.holdHeadingModeButton.get();
	}
}