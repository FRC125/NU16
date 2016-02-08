package com.nutrons.stronghold.subsystems;

import com.nutrons.lib.DebouncedBoolean;
import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.shooter.MoveArmToPositionCmd;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Shooter extends Subsystem {
	
	// Motors
	private CANTalon arm = new CANTalon(RobotMap.ARM_MOTOR);
	
	// Sensors
	private DigitalInput zeroButton = new DigitalInput(RobotMap.ZERO_BUTTON);
	
	// Utils
	private DebouncedBoolean zeroButtonDebouncedBoolean = new DebouncedBoolean(5);

	public Shooter() {
		this.arm.changeControlMode(TalonControlMode.PercentVbus);
		this.arm.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.arm.configPeakOutputVoltage(+12.0f, 0.0f);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArmToPositionCmd());
	}
	
	/**
	 * drives arm with a power from -1 to 1
	 * @param power Arm power
	 */
	public void driveArm(double power) {
		this.arm.set(power);
	}
	
	/**
	 * Returns the status of zero button
	 * @return Is the button pressed?
	 */
	public boolean isZeroButtonPressed() {
		this.zeroButtonDebouncedBoolean.feed(this.zeroButton.get());
		return this.zeroButtonDebouncedBoolean.get();
	}
}