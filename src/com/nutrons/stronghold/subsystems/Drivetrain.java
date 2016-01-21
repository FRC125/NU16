
package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.RobotMap;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
    // Motors
	private Talon leftDrive = new Talon(RobotMap.LEFT_DRIVE);
    private Talon rightDrive = new Talon(RobotMap.RIGHT_DRIVE);
	
    // Sensors
    private Encoder leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, RobotMap.LEFT_DRIVE_ENCODER_B);
    private Encoder rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, RobotMap.RIGHT_DRIVE_ENCODER_B);
    
    // Constants
    public static final double wheelDiam = 0.5;
    
    public Drivetrain() {
    	this.leftDriveEncoder.setDistancePerPulse((wheelDiam * Math.PI)/256.0);
    	this.rightDriveEncoder.setDistancePerPulse((wheelDiam * Math.PI)/256.0);
    }
    
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
    
    /**
     * Returns the distance traveled by the left side of the drivetrain in feet
     * @return Distance in feet
     */
    public double getLeftDistanceInFeet() {
    	return this.leftDriveEncoder.getDistance();
    }
    
    /**
     * Returns the distance traveled by the right side of the drivetrain in feet
     * @return Distance in feet
     */
    public double getRightDistanceInFeet() {
    	return this.rightDriveEncoder.getDistance();
    }
}

