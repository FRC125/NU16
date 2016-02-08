package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.shooter.MoveArmToPositionCmd;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Shooter extends Subsystem {
	
	private CANTalon arm = new CANTalon(RobotMap.ARM_MOTOR);

	public Shooter() {
		this.arm.changeControlMode(TalonControlMode.PercentVbus);
		this.arm.configNominalOutputVoltage(+0.0f, -0.0f); 
		this.arm.configPeakOutputVoltage(+12.0f, 0.0f);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new MoveArmToPositionCmd());
	}
	
	public void driveArm(double power) {
		this.arm.set(power);
	}
}