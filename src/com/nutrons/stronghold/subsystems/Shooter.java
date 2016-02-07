package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;

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
	}
	
	public void initDefaultCommand() {
    
	}
	
	public void driveArm(double power) {
		this.arm.set(power);
	}
}

