package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CDF extends Subsystem {

	public Victor cdfArmMotor = new Victor(RobotMap.CDF_ARM);
	
    public void initDefaultCommand() {
        
    }
    
    /**
     * Moves CDF down
     */
    public void MoveCDFArmDown() {
    	this.cdfArmMotor.set(-0.5);
    }
    
    /**
     * Moves CDF up
     */
    public void MoveCDFArmUp() {
    	this.cdfArmMotor.set(0.5);
    }
    
    /**
     * Stops CDF arm
     */
    public void StopCDF() {
    	this.cdfArmMotor.set(0);
    }
}

