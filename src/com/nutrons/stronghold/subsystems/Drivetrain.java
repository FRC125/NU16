
package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Drivetrain extends Subsystem {
    
	private Talon leftDrive = new Talon(Robot.robotMap.LEFT_DRIVE);
	private Talon rightDrive = new Talon(Robot.robotMap.RIGHT_DRIVE);

	private Encoder leftDriveEncoder = new Encoder(Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_A, Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	private Encoder rightDriveEncoder = new Encoder(Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_A, Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	
	// Constants
	public double wheelCircumference = 2.5;
	public double encoderTicksToFeet = wheelCircumference / (2 * Math.PI);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Sets power to the left and right side of the drivetrain
     * @param leftPower Left drivetrain motor power
     * @param rightPower Right drivetrain motor power
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDrive.set(leftPower);
    	this.rightDrive.set(rightPower);
    }
    
    /**
     * Stops drivetrain from moving. Sets power to zero
     */
    public void stop() {
    	this.driveLR(0, 0);
    }
    
    /**
     * Gets total distance traveled by left side of drivetrain
     * @return Distance traveled - left 
     */
    public double getLeftEncoderDistance() {
    	return this.leftDriveEncoder.get() * this.encoderTicksToFeet;
    }
    
    /**
     * Gets total distance traveled by left side of drivetrain
     * @return Distance traveled - right
     */
    public double getRightEncoderDistance() {
    	return this.rightDriveEncoder.get() * this.encoderTicksToFeet;
    }
    
    /**
     * Resets the left and right drivetrain encoders
     */
    public void resetEncoders() {
    	this.leftDriveEncoder.reset();
    	this.rightDriveEncoder.reset();
    }
    
}

