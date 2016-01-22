package com.nutrons.stronghold;

import com.nutrons.stronghold.commands.drivetrain.DeployPistonsCmd;
import com.nutrons.stronghold.commands.drivetrain.RetractPistonsCmd;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	private Joystick driverPad = new Joystick(RobotMap.DRIVER_PAD);
	
	private Button deploy = new JoystickButton(driverPad, 1);
	private Button retract = new JoystickButton(driverPad, 2);
	
	public OI() {
		deploy.whenPressed(new DeployPistonsCmd());
		retract.whenPressed(new RetractPistonsCmd());
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

