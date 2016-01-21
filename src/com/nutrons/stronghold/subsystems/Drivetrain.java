
package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
    private Talon leftDrive = new Talon(RobotMap.LEFT_DRIVE);
    private Talon rightDrive = new Talon(RobotMap.RIGHT_DRIVE);
	
    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveCmd());
    }
    
    /**
     * Drives left and right side of the drivetrain
     * @param leftPower Power for the left side of the dt
     * @param rightPower Power for the right side of the dt 
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDrive.set(leftPower);
    	this.rightDrive.set(-rightPower);
    }
    
    /**
     * Stops drivetrain from moving
     */
    public void stop() {
    	this.driveLR(0, 0);
    }
}

