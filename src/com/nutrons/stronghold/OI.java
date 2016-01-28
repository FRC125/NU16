package com.nutrons.stronghold;

import com.nutrons.stronghold.commands.intake.IntakeBallCmd;
import com.nutrons.stronghold.commands.intake.OutputBallCmd;
import com.nutrons.stronghold.commands.intake.PushBallCmd;
import com.nutrons.stronghold.commands.intake.StopIntakeMotorCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 */

public class OI {
    
	private Joystick driverPad = new Joystick(RobotMap.DRIVER_PAD);
	
	//Intake buttons
	private Button intakeBallButton = new JoystickButton(driverPad, RobotMap.INTAKE_BALL_BUTTON);
	private Button outputBallButton = new JoystickButton(driverPad, RobotMap.OUTPUT_BALL_BUTTON);
	private Button pushBallButton = new JoystickButton(driverPad, RobotMap.BALL_PUSHER_PISTON);
	
	public OI() {
		intakeBallButton.whileHeld(new IntakeBallCmd());
		intakeBallButton.whenReleased(new StopIntakeMotorCmd());
		outputBallButton.whileHeld(new OutputBallCmd());
		outputBallButton.whenReleased(new StopIntakeMotorCmd());
		pushBallButton.whenPressed(new PushBallCmd());
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

