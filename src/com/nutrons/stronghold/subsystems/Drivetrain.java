
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
	
	// Driving Trajectories
	public Trajectory trajectory;
	public TrajectoryFollower followerLeft = new TrajectoryFollower();
	public TrajectoryFollower followerRight = new TrajectoryFollower();
	
	private double direction;
	private double heading;
	
	// Constants
	public double wheelCircumference = 2.5;
	public double encoderTicksToFeet = wheelCircumference / (2 * Math.PI);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDriveCmd());
    	this.initTrajectory();
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
    
    /**
     * Initializes trajectories
     */
    private void initTrajectory() {
    	this.followerLeft.configure(1.5, 0, 0, 1.0/15.0, 1.0/34.0);
    	this.followerRight.configure(1.5, 0, 0, 1.0/15.0, 1.0/34.0);
    }
    
    /**
     * Reset trajectory values
     */
    private void reset() {
    	this.followerLeft.reset();
    	this.followerRight.reset();
    	this.resetEncoders();
    }
    
    /**
     * Checks if the drivetrain is following the trajectory correctly
     * @return Is the drivetrain following trajectory
     */
    public boolean onTarget() {
    	return followerLeft.isFinishedTrajectory();
    }
    
    /**
     * Load trajectory to trajectory follower
     * @param leftProfile Trajectory for left side of drivetrain
     * @param rightProfile Trajectory for right side of drivetrain
     * @param direction Direction of trajectory
     * @param heading Heading angle of trajectory
     */
    public void loadProfile(Trajectory leftProfile, Trajectory rightProfile, double direction, double heading) {
    	this.followerLeft.setTrajectory(leftProfile);
    	this.followerRight.setTrajectory(rightProfile);
    	this.direction = direction;
    	this.heading = heading;
    }
    
    /**
     * Load trajectory to trajectory follower without reseting config values
     * @param leftProfile Trajectory for left side of drivetrain
     * @param rightProfile Trajectory for right side of drivetrain
     */
    public void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile) {
    	this.followerLeft.setTrajectory(leftProfile);
    	this.followerRight.setTrajectory(rightProfile);
    }
    
    /**
     * Set follower's trajectory
     * @param trajectory Trajectory to follow
     */
    public void setTrajectory(Trajectory trajectory) {
    	this.trajectory = trajectory;
    }
}

