package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Arm extends Subsystem {
    
	public CANTalon arm = new CANTalon(RobotMap.ARM_MOTOR);
	
    public void initDefaultCommand() {
    	
    }
    
    public void driveArm(double power) {
    	this.arm.set(power);
    }
}