
package com.nutrons.stronghold.subsystems;

import com.nutrons.stronghold.Robot;
import com.nutrons.stronghold.commands.drivetrain.TankDriveCmd;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
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
	private Talon leftDrive = new Talon(Robot.robotMap.LEFT_DRIVE);
	private Talon rightDrive = new Talon(Robot.robotMap.RIGHT_DRIVE);

	// Sensors
	private Encoder leftDriveEncoder = new Encoder(Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_A, Robot.robotMap.ENCODER_LEFT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	private Encoder rightDriveEncoder = new Encoder(Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_A, Robot.robotMap.ENCODER_RIGHT_DRIVETRAIN_B, false, Encoder.EncodingType.k4X);
	
	// Constants
	public double WHEEL_DIAM = 6;
	
	public Drivetrain() {
		leftDriveEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
		rightDriveEncoder.setDistancePerPulse(WHEEL_DIAM*Math.PI/128.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDriveCmd());
    }
    
    /**
     * Sets power to the left and right side of the drivetrain
     * @param leftPower Left drivetrain motor power
     * @param rightPower Right drivetrain motor power
     */
    public void driveLR(double leftPower, double rightPower) {
    	this.leftDrive.set(leftPower);
    	this.rightDrive.set(-rightPower);
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
    	return this.leftDriveEncoder.getDistance()/25.0;
    }
    
    /**
     * Gets total distance traveled by left side of drivetrain
     * @return Distance traveled - right
     */
    public double getRightEncoderDistance() {
    	return this.rightDriveEncoder.getDistance()/25.0;
    }
    
    /**
     * Resets the left and right drivetrain encoders
     */
    public void resetEncoders() {
    	this.leftDriveEncoder.reset();
    	this.rightDriveEncoder.reset();
    }   
    
    public double getGyroAngleInRadians() {
    	return 0;
    }
}

