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
    
	// Motors
	public CANTalon arm1 = new CANTalon(RobotMap.ARM_1_MOTOR);
	public CANTalon arm2 = new CANTalon(RobotMap.ARM_2_MOTOR);
	
	
	// Constants
	private static double P_ARM_POSITION = 0.001;
	private static double I_ARM_POSITION = 0.0;
	private static double D_ARM_POSITION = 0.0;
	private static double F_ARM_POSITION = 0.0;
		
    public void initDefaultCommand() {
    	
    }
    
    public void driveArm(double power) {
    	this.arm1.set(power);
    }
    
    public void zeroArm() {
    	this.arm1.setPosition(0);
    }
}